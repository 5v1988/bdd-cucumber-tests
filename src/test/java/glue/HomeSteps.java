package glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import javax.inject.Inject;
import model.TestConfig;
import pages.HomePage;

public class HomeSteps {

  @Inject
  private HomePage homePage;

  @Inject
  private TestConfig testConfig;

  @Given("User opens home page: url")
  public void userOpensHomePage() {
    homePage.openUrl(testConfig.getUrl());
  }

  @When("User navigates to sign-in page")
  public void userNavigatesToSignInPage() {
    homePage.navigateToSignInPage();
  };

  @When("User navigates to create new customer account")
  public void userNavigatesToCreateNewCustomerAccount() {
    homePage.navigateToNewAccountPage();
  }
}
