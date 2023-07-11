package pages;

import io.cucumber.guice.ScenarioScoped;
import java.time.Duration;
import javax.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@ScenarioScoped
public class ReviewAndPaymentsPage extends BasePage {

  @Inject
  public ReviewAndPaymentsPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(how = How.CSS, using = "button[title='Place Order']")
  private WebElement placeOrderBtn;

  public void placeOrder() {
    waitUntil(placeOrderBtn);
    pause(Duration.ofSeconds(3));
    placeOrderBtn.click();
  }

}
