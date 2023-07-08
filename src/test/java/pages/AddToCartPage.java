package pages;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@ScenarioScoped
public class AddToCartPage extends BasePage {

  @Inject
  public AddToCartPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(how= How.CSS,using="button[title='Add to Cart']")
  private WebElement addToCart;

  @FindBy(how= How.CSS,using="#qty")
  private WebElement quantity;

  @FindBy(how= How.CSS,using="a.showcart span.counter")
  private WebElement cartCounter;

  @FindBy(how= How.XPATH,using="//a[.//span[text()='View and Edit Cart']]")
  private WebElement viewAndEditCart;

  @FindBy(how= How.CSS,using="[title='Proceed to Checkout']")
  private WebElement proceedToCheckout;

  public void addProductToCart(){
    waitUntil(addToCart, DEFAULT_TIME_OUT);
    addToCart.click();
    waitUntilText(cartCounter, DEFAULT_TIME_OUT);
  }

  public void proceedCheckout(){
    cartCounter.click();
    waitUntil(proceedToCheckout);
    proceedToCheckout.click();
  }
}
