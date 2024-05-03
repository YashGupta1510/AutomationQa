package page;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.FileReader;

public class BasePage {
	
	public static WebDriver driver;
	protected static Logger log = LogManager.getLogger();;
	protected static WebDriverListener eventListener;
	protected static ExtentReports extent;
	static ExtentTest extentTest;
	
	protected BasePage() {
		try {
			FileReader.readConfig();
			FileReader.readData();
		} catch (IOException e) {
			System.out.println("File not Found");
		}
		
	}
	
	@BeforeSuite
	public void setReport() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\yashgupta02\\eclipse-qa\\automationqa\\report/testReport.html");
		extent = new ExtentReports();
		sparkReporter.config().setDocumentTitle("Simple Automation Report");
		sparkReporter.config().setReportName("Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setCss(".badge{font-size:100%}");
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent.attachReporter(sparkReporter);
	}
	
	public ExtentTest extentTest() {
		return extentTest;
	}
	
	@AfterSuite
	public void finishReport() {
		extent.flush();
	}
	
	@BeforeMethod
	public void setup(Method method) throws IOException {
		
		try {
			extentTest = extent.createTest(method.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String browser = FileReader.props.getProperty("Browser");
		
		driver = getDriver(browser);
		extentTest().info("Driver Created as per Properties file");
		
		int seconds = Integer.parseInt(FileReader.props.getProperty("Wait-Time"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		
		String url = FileReader.props.getProperty("URL");
		driver.get(url);
		
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest().fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest().pass(result.getTestName());
		} else {
			extentTest().skip(result.getTestName());
		}
		driver.quit();
		extent.removeTest(result.getTestName());
	}

	
	WebDriver getDriver(String browser) {
		log.info("Brower has been set to " + browser);
		boolean headless = Boolean.parseBoolean(FileReader.props.getProperty("Headless-mode"));
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("start-maximized");
		log.info("Brower has been maximized");
		if(headless) {
			opt.addArguments("--headless");
		}
		switch(browser) {
			case "Chrome":
				driver =  new ChromeDriver(opt);
				break;
			case "Firefox":
				driver =  new ChromeDriver(opt);
				break;
			case "Edge":
				driver =  new ChromeDriver(opt);
				break;
			default:
				driver =  new ChromeDriver(opt);
				break;
		}
		return driver;
	}
		
}
