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
public class OrderConfirmationPage extends BasePage {

  @Inject
  public OrderConfirmationPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(how = How.CSS, using = "h1.page-title")
  private WebElement pageTitle;

  @FindBy(how = How.CSS, using = ".checkout-success")
  private WebElement orderSuccess;

  public String getConfirmationMessage() {
    waitUntilText(orderSuccess, Duration.ofSeconds(10));
    return orderSuccess.getText().trim();
  }

}
