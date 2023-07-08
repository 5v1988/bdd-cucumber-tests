package pages;

import io.cucumber.guice.ScenarioScoped;
import javax.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@ScenarioScoped
public class SignInPage extends BasePage {

  @Inject
  public SignInPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(how = How.CSS, using = "#email")
  private WebElement email;

  @FindBy(how = How.CSS, using = "#pass")
  private WebElement password;

  @FindBy(how = How.CSS, using = "button.login")
  private WebElement loginBtn;

  public void signIn(String email, String password) {
      this.email.sendKeys(email);
      this.password.sendKeys(password);
      loginBtn.click();
  }

}
