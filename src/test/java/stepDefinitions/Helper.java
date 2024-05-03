package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import page.BasePage;
import utils.FileReader;

public class Helper extends BasePage{

	
	@Before
	public void setup() {
		driver = new ChromeDriver();
		int seconds = Integer.parseInt(FileReader.props.getProperty("Wait-Time"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		String url = FileReader.props.getProperty("URL");
		driver.get(url);
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public void waitForElement(WebDriver driver, WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementText(WebDriver driver, WebElement element, String text) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
}
