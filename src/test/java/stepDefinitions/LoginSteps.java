package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.BasePage;
import page.HomePage;
import page.SignInPage;



public class LoginSteps extends BasePage {
	
	
	private SignInPage signinPg;
	
	@Given("user is on Login Page")
	public void user_is_on_login_page() {
		Helper h = new Helper();
		driver = h.getDriver();
		HomePage hp = new HomePage(driver);
		signinPg =hp.navigateToSignInPage();
		Assert.assertEquals(driver.getTitle(), "Amazon Sign In", "Sign in page Title assertion");
	}

	@When("user enter username {string}")
	public void user_enter_username(String email) {
		signinPg.inputEmail(email);
	}

	@When("user enter password {string}")
	public void user_enter_password(String pwd) {
		signinPg.inputPassword(pwd);
	}

	@Then("user moves to solve page")
	public void user_moves_to_solve_page() {
		String s = signinPg.solveThisMessage();
		Assert.assertEquals(s, "Solve this puzzle to protect your account");
	    driver.quit();
	}
	
	@Then("user gets error")
	public void user_gets_error() {
		String s = signinPg.invalidEmailMessage();
		Assert.assertEquals(s, "There was a problem");
	    driver.quit();
	}
}
