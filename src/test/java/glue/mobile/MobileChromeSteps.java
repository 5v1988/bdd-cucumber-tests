package glue.mobile;

import enabler.RetryUtilityStep;
import glue.StepHelpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import pages.KlipboardPage;

public class MobileChromeSteps extends StepHelpers implements RetryUtilityStep {

  @Inject
  private KlipboardPage klipboardPage;

  public static Logger log = Logger.getLogger(MobileChromeSteps.class);

  @Given("User opens the url: {string} in the browser")
  public void userOpensTheUrlInTheBrowser(String url) {
    klipboardPage.openUrl(url);
  }

  @Given("User clears Klipboard content")
  public void userClearsKlipboardContent() {
    klipboardPage.clear();
  }

  @Given("User releoads the current page")
  public void userReleoadsTheCurrentPage() {
    klipboardPage.reloads();
  }

  @When("User adds and saves the text: {string} to the klipboard")
  public void userAddsAndSavesTheTextToTheKlipboard(String data) {
    klipboardPage.add(data);
  }

  @Then("User verifies that the text: {string} is displayed")
  public void userVerifiesThatTheTextIsDisplayed(String data) {
    Assertions.assertThat(klipboardPage.getKlipboardData())
        .isEqualTo(data);
  }

}