package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LanguageSelectPage extends BasePage{
	

	protected static Logger log = LogManager.getLogger();
	
	public LanguageSelectPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	By languageTitle = By.id("icp-language-heading");
	
	public String language(String lang) {
		
		WebElement e = driver.findElement(By.cssSelector("input[value=\""+lang+"_IN\"]"));
		
		log.info("Selecting Language "+lang);
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", e);
		
		log.info("Language Changed to "+lang);
		
		return driver.findElement(languageTitle).getText();
	}
}