package glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import javax.inject.Inject;
import pages.AddToCartPage;
import pages.HomePage;

public class AddToCardSteps {

  @Inject
  private AddToCartPage addToCartPage;

  @When("User adds a chosen product to the cart")
  public void userAddsAChosenProductToTheCart() {
    addToCartPage.addProductToCart();
  }

  @When("User proceeds to checkout from the cart")
  public void userProceedsToCheckoutFromTheCart() {
    addToCartPage.proceedCheckout();
  }

}
