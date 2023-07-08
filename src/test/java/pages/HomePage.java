package pages;

import io.cucumber.guice.ScenarioScoped;
import javax.inject.Inject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@ScenarioScoped
public class HomePage extends BasePage {

  @Inject
  public HomePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(how = How.CSS, using = "#search")
  private WebElement searchTextBox;

  @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Sign In")
  private WebElement signIn;

  @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Create an Account")
  private WebElement createAnAccount;

  public void navigateToSignInPage() {
    signIn.click();
  }

  public void navigateToNewAccountPage() {
    createAnAccount.click();
  }

  public void searchProducts(String productName) {
    searchTextBox.sendKeys(productName);
    searchTextBox.sendKeys(Keys.ENTER);
  }

}
