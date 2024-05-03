package stepDefinitions;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.BasePage;
import page.HomePage;
import page.LanguageSelectPage;
import utils.FileReader;

public class HomePageFeature extends BasePage{

	HomePage hp;
	String s;
	@Given("Site is opened")
	public void site_is_opened() {
		hp = new HomePage(driver);
	    WebElement e = hp.getLogo();
	    Assert.assertTrue(e.isDisplayed());
	}
	
	@Then("Logo is Visible")
	public void logo_is_visible() {
		WebElement e =hp.getLogo();
		Assert.assertTrue(e.isDisplayed());
	}
	
	@Given("user clicks on language page")
	public void user_clicks_on_language_page() {
		hp.navigateToLanguagePage();
	}

	@When("user selects hindi")
	public void user_selects_hindi() {
		Properties data = FileReader.data;
	    LanguageSelectPage lp = new LanguageSelectPage(driver);
	    s = lp.language(data.getProperty("language"));
	}

	@Then("Langauge should change")
	public void langauge_should_change() {
		Assert.assertEquals( s , "भाषा सेटिंग");	
	}
}
