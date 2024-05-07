package tests;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.BasePage;
import page.HomePage;
import page.SignInPage;
import utils.FileReader;

public class SignInPageTest extends BasePage{
	

	protected static Logger log = LogManager.getLogger();
	
	public SignInPageTest() {
		super();
	}
	
	Properties data = FileReader.data;
	
	@Test(priority = 1, groups = {"UI"})
	public void signInPageTitle() {

		log.info("Checking page title");
		
		HomePage page = new HomePage(driver);
		page.navigateToSignInPage();
		extentTest().info("On login page");
		extentTest().info("Check title");

		String title = driver.getTitle();
		Assert.assertEquals(title, "Amazon Sign In", "Sign in page Title assertion");
	}
	
	@Test(priority = 2, groups = {"Functionality"})
	public void tryInvalidEmailSignIn() {
		SignInPage page = new HomePage(driver).navigateToSignInPage();

		log.warn("Testing with Invalid Email");
		
		extentTest().info("Testing with Invalid Email");
		page.inputEmail(data.getProperty("email2"));
		Assert.assertEquals(page.invalidEmailMessage(), "There was a problem");
	}
	
	@Test(priority = 3, groups = {"Functionality"})
	public void trySignIn() {
		SignInPage page = new HomePage(driver).navigateToSignInPage();

		log.warn("Testing with Wrong Credentials");
		
		extentTest().info("Testing with Wrong values");
		page.inputEmail(data.getProperty("email1"));
		page.inputPassword(data.getProperty("pwd1"));
		Assert.assertEquals(page.solveThisMessage(), "Solve this puzzle to protect your account");
	}
	
	
}
