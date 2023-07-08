package glue;

import com.google.inject.Inject;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.util.List;
import org.assertj.core.api.Assertions;
import pages.HomePage;
import pages.SearchProductPage;

public class SearchProductSteps {

  @Inject
  HomePage homePage;

  @Inject
  SearchProductPage searchProductPage;

  @When("User searches for the product: {string}")
  public void userSearchesForTheProduct(String productName) {
    homePage.searchProducts(productName);
  }

  @When("User verifies that the product: {string} displays in the results")
  public void userVerifiesThatTheProductDisplaysInTheResults(String productName) {
    searchProductPage.pause(Duration.ofSeconds(5));
    List<String> products = searchProductPage.getAllProductName();
    Assertions.assertThat(products).as(String.format("Assert that the product %s displays", productName))
        .contains(productName);
  }

  @When("User chooses the product: {string} displays in the results")
  public void userChoosesTheProductDisplaysInTheResults(String productName) {
    searchProductPage.chooseProduct(productName);
  }
}
