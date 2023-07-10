package glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.util.Map;
import javax.inject.Inject;
import model.TestConfig;
import model.TestContext;
import pages.HomePage;

public class HomeSteps {

  @Inject
  private HomePage homePage;

  @Inject
  private TestConfig testConfig;

  @Inject
  private TestContext<String, Object> testContext;

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
