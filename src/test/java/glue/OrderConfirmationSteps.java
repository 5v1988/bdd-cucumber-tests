package glue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javax.inject.Inject;
import org.assertj.core.api.Assertions;
import pages.OrderConfirmationPage;
import pages.SignInPage;

public class OrderConfirmationSteps {

  @Inject
  private OrderConfirmationPage orderConfirmationPage;

  @Then("User verifies that order confirmation: {string} is displayed")
  public void userVerifiesThatOrderConfirmationIsDisplayed(String message) {
    Assertions.assertThat(orderConfirmationPage.getConfirmationMessage())
        .as(String.format("Assert that confirmation %s is displayed!", message))
        .contains(message);
  }

}
