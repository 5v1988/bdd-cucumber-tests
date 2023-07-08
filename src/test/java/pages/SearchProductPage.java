package pages;

import io.cucumber.guice.ScenarioScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@ScenarioScoped
public class SearchProductPage extends BasePage {

  @Inject
  public SearchProductPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindAll(@FindBy(how = How.CSS, using = "div.product-item-info"))
  private List<WebElement> productGrid;

  @FindAll(@FindBy(how = How.CSS, using = "a.product-item-link"))
  private List<WebElement> productElements;

  public List<String> getAllProductName() {
   // waitUntil(productElements, DEFAULT_TIME_OUT);
    List<String> products = productElements.stream().map(WebElement::getText)
        .collect(Collectors.toList());
    return products;
  }

  public void chooseProduct(String productName){
    waitUntil(productElements);
    Optional<WebElement> product = productElements.stream()
        .filter(productElement -> productElement.getText().equalsIgnoreCase(productName))
        .findFirst();
    Assertions.assertThat(product.isPresent()).isTrue();
    product.get().click();
  }

}
