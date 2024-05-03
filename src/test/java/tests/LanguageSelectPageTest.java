package tests;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import page.BasePage;
import page.HomePage;
import page.LanguageSelectPage;
import utils.FileReader;

public class LanguageSelectPageTest extends BasePage {
	LanguageSelectPageTest(){
		super();
	}
	Properties data = FileReader.data;
  	
	@Test(groups = {"Functionality"},priority = 7)
  public void selectLanguage() {
	  new HomePage(driver).navigateToLanguagePage();
	  LanguageSelectPage lp = new LanguageSelectPage(driver);
	  extentTest().info("In Language Test");
	  Assert.assertEquals( lp.language(data.getProperty("language")) , "भाषा सेटिंग");  
  }
	
	@Test(groups = {"Functionality"},priority = 8)
	  public void selectLanguageFail() throws IOException {
		  new HomePage(driver).navigateToLanguagePage();
		  LanguageSelectPage lp = new LanguageSelectPage(driver);
		  extentTest().info("In Language TestFailt");
		  Assert.assertEquals( lp.language(data.getProperty("language")) , "भाषा सेटिंग");
		  
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			FileHandler.copy(source,
					new File("screenshots\\TC3_LanguageFail.png"));

			extentTest().info(MediaEntityBuilder.createScreenCaptureFromPath(
					"screenshots\\TC3_LanguageFail.png",
					"No Logo Tag").build());
			Assert.assertTrue(false);
	  }
}
