package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import stepDefinitions.Helper;


public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	protected static Logger log = LogManager.getLogger();
	
	public SignInPage navigateToSignInPage() {
		log.info("Moving to Sign IN Page");
		driver.findElement(By.id("nav-link-accountList")).click();
		return new SignInPage(driver);
	}
	Helper h;
	public WebElement getLogo() {
		h = new Helper();
		h.waitForElement(driver, driver.findElement(By.id("nav-logo-sprites")));
		WebElement e = driver.findElement(By.id("nav-logo-sprites"));
		return e;
	}
	
	
	
	By searchTab = By.cssSelector("input[id=\"twotabsearchtextbox\"]");
	By searchResults = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/span/div/div/span");
	By addToCartButton = By.cssSelector("button[id =\"a-autoid-1-announce\"]");
	public By cartItemsCount = By.cssSelector("span[id =\"nav-cart-count\"]");
	By selectLanguage = By.cssSelector("a[id=\"icp-nav-flyout\"]");
	
	
	int initialCount, finalCount; 
	
	
	public int getInitialCount() {
		return initialCount;
	}

	public int getFinalCount() {
		return finalCount;
	}

	public void navigateToSearchPage(String prod) {
		log.info("Moving to Search Page for product "+ prod);
		driver.findElement(searchTab).sendKeys(prod);
		driver.findElement(searchTab).sendKeys(Keys.ENTER);
	}
	
	public void navigateToLanguagePage() {
		log.info("Moving to Language Select Page");
		driver.findElement(selectLanguage).click();
	}
	
	public String getResultText() {
		return driver.findElement(searchResults).getText();
	}
	
	public void addToCart(String prod) {
		navigateToSearchPage(prod);
		
		initialCount = Integer.parseInt(driver.findElement(cartItemsCount).getText());
		
		log.info("Adding Product to Cart");
		driver.findElement(addToCartButton).click();
		
		 h.waitForElementText(driver, driver.findElement(addToCartButton), "Add to cart");
		
		finalCount = Integer.parseInt(driver.findElement(cartItemsCount).getText());
	}
	
	public void addToCartFail(String prod) {
		navigateToSearchPage(prod);
		initialCount = Integer.parseInt(driver.findElement(cartItemsCount).getText());
		
		log.info("Adding no Product to Cart");
		
		finalCount = Integer.parseInt(driver.findElement(cartItemsCount).getText());
	}
	
	public void user_clicked_on_add_to_cart_button() {
	    initialCount = Integer.parseInt((driver.findElement(cartItemsCount)).getText());
	    h.waitForElement(driver, driver.findElement(addToCartButton));
	    driver.findElement(addToCartButton).click();
	    h.waitForElementText(driver, driver.findElement(addToCartButton), "Add to cart");

	    finalCount = Integer.parseInt((driver.findElement(cartItemsCount)).getText());
	    
	}
	
	public void cart_item_count_should_increase() {
		
		Assert.assertEquals(finalCount, initialCount + 1);
	}
	
	
}