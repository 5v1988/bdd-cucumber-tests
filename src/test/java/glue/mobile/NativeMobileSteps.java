package glue.mobile;

import enabler.RetryUtilityStep;
import glue.StepHelpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import pages.mobileScreen.ListViewScreen;

public class NativeMobileSteps extends StepHelpers implements RetryUtilityStep {

  @Inject
  private ListViewScreen mobileList;

  public static Logger log = Logger.getLogger(NativeMobileSteps.class);

  @Given("Mobile User taps 'Views' on the list")
  public void mobileUserTapsViewsOnTheList() {
    mobileList.tapViews();
  }

  @Given("Mobile User taps 'Controls' on the list")
  public void mobileUserTapsControlsOnTheList() {
    mobileList.tapControls();
  }

  @Given("Mobile User selects the theme: {string}")
  public void mobileUserSelectsTheTheme(String theme) {
    mobileList.selectTheme(theme);
  }

  @When("Mobile User enters the text: {string}")
  public void mobileUserEntersTheText(String text) {
    mobileList.enterOnText(text);
  }

  @Then("Mobile User verifies the text: {string} is entered")
  public void mobileUserVerifiesTheTextIsEntered(String text) {
    Assertions.assertThat(mobileList.getTextFieldText())
        .as(sf("Assert that the text: %s is entered!", text))
        .isEqualTo(text);
  }

}