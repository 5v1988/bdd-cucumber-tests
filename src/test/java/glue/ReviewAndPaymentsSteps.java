package glue;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import pages.ReviewAndPaymentsPage;

public class ReviewAndPaymentsSteps {

  @Inject
  ReviewAndPaymentsPage reviewAndPaymentsPage;

  @Then("User reviews payments and places order")
  public void userReviewsPaymentsAndPlacesOrder() {
    reviewAndPaymentsPage.placeOrder();
  }


}
