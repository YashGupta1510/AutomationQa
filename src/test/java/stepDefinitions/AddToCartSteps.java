package stepDefinitions;


import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.BasePage;
import page.HomePage;



public class AddToCartSteps extends BasePage{
	
	HomePage hp;

		@Given("user is on homepage")
		public void user_is_on_homepage() {
			hp = new HomePage(driver);
		    WebElement e = hp.getLogo();
		    Assert.assertTrue(e.isDisplayed());
		}

		@Given("user searched for {string}")
		public void user_searched_for(String product) {
			hp.navigateToSearchPage(product);
		}
		@When("user clicked on add to cart button")
		public void user_clicked_on_add_to_cart_button() {
			 hp.user_clicked_on_add_to_cart_button();
		}
		@Then("cart item count should increase")
		public void cart_item_count_should_increase() {
			hp.cart_item_count_should_increase();
			driver.quit();
		}



}
