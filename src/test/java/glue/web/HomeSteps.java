package glue.web;

import glue.StepHelpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import pages.HomePage;
import enabler.RetryUtilityStep;

public class HomeSteps extends StepHelpers implements RetryUtilityStep {

  public static Logger log = Logger.getLogger(HomeSteps.class);

  @Inject
  private HomePage homePage;

  @Given("User opens home page: url")
  public void userOpensHomePage() {
    log.info(sf("attempting to open the url %s", testConfig.getWeb().getUrl()));
    homePage.openUrl(testConfig.getWeb().getUrl());
  }

  @When("User navigates to sign-in page")
  public void userNavigatesToSignInPage() {
    retryStep(() -> homePage.navigateToSignInPage(),
        MAX_RETRIES_DEFAULT,
        MAX_DELAY_SECONDS_DEFAULT);
  }

  @When("User navigates to create new customer account")
  public void userNavigatesToCreateNewCustomerAccount() {
    retryStep(() -> homePage.navigateToNewAccountPage(),
        MAX_RETRIES_DEFAULT,
        MAX_DELAY_SECONDS_DEFAULT);
  }
}