package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.Helper;

public class SignInPage extends BasePage{
	

	protected static Logger log = LogManager.getLogger();
	
	public SignInPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	Helper h;
	
	By emailInput = By.cssSelector("input[type=\"email\"]");
	By pwdInput = By.cssSelector("input[type=\"password\"]");
	By solveMessage = By.cssSelector("span[class=\"a-size-large\"]");
	By invalidEmail = By.cssSelector("h4[class=\"a-alert-heading\"]");
	
	
	public void inputEmail(String email) {
		 h = new Helper();
		 log.info("Enetering Email "+email);
		 h.waitForElement(driver, driver.findElement(emailInput));
		 driver.findElement(emailInput).sendKeys(email);
		 driver.findElement(emailInput).sendKeys(Keys.ENTER);
	}
	public void inputPassword(String pwd) {
		 log.info("Enetering password "+pwd);
		 h.waitForElement(driver, driver.findElement(pwdInput));
		 driver.findElement(pwdInput).sendKeys(pwd);
		 driver.findElement(pwdInput).sendKeys(Keys.ENTER);
	}
	public String solveThisMessage() {
		 h.waitForElement(driver, driver.findElement(solveMessage));
		 return driver.findElement(solveMessage).getText();
	}
	public String invalidEmailMessage() {
		 h.waitForElement(driver, driver.findElement(invalidEmail));
		 return driver.findElement(invalidEmail).getText();
	}
	
	
	
}
