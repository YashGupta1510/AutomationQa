package tests;


import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import page.BasePage;
import page.HomePage;
import utils.FileReader;

public class HomeTest extends BasePage{
	

	protected static Logger log = LogManager.getLogger();
	
	HomeTest() {
		super();
	}
	Properties data = FileReader.data;

	@Test(priority = 1, groups = {"UI"})
	public void homePageLogo() {		
		extentTest().info("Test case for site logo");

		log.info("Checking page Logo visibility");
		
		WebElement e = driver.findElement(By.id("nav-logo-sprites"));
		Assert.assertEquals(e.isDisplayed(), true, "Logo not displayed");
	}
	
	@Test(priority = 5, groups = {"UI"})
	public void homePageLogoFail() throws IOException {		
		extentTest().info("Test case for site logo");

		log.info("Checking page Logo visibility");
		
		
		WebElement e = driver.findElement(By.id("nav-logo-sprites"));
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		FileHandler.copy(source,
				new File("screenshots\\TC1_HomePageLogoFail.png"));

		extentTest().info(MediaEntityBuilder.createScreenCaptureFromPath(
				"screenshots\\TC1_HomePageLogoFail.png",
				"No Logo Tag").build());
		Assert.assertTrue(false);
	}
	
	
	@Test(priority = 2, groups = {"Functionality"})
	public void searchProduct() {
		extentTest().info("Test case for Search bar functionality");

		log.info("Checking Search bar functionality");
		
		HomePage page = new HomePage(driver);
		page.navigateToSearchPage(data.getProperty("searchProd"));
		Assert.assertEquals(page.getResultText(), "Results");
	}
	
	@Test(priority = 3, groups = {"Functionality"})
	public void addToCartTest() {
		
		extentTest().info("Test case for AddToCart functionality");
		HomePage page = new HomePage(driver);
		page.addToCart(data.getProperty("searchProd"));
		
		Assert.assertEquals(page.getFinalCount(), page.getInitialCount() +1);
	}
	
	@Test(priority = 4, groups = {"Functionality"})
	public void addToCartTestFail() throws IOException {
		
		extentTest().info("Test case for AddToCart functionality to Fail");
		
		HomePage page = new HomePage(driver);
		page.addToCartFail(data.getProperty("searchProd"));
		
		if(page.getFinalCount() == page.getInitialCount()) {
			extentTest().info("Counts equal, Taking ss");
			WebElement e = driver.findElement(page.cartItemsCount);
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			FileHandler.copy(source,
					new File("screenshots\\TC2_AddToCartTestFail.png"));

			extentTest().info(MediaEntityBuilder.createScreenCaptureFromPath(
					"screenshots\\TC2_AddToCartTestFail.png",
					"No change In Count").build());

		}
		Assert.assertTrue(false);
		
	}
	
	
}
