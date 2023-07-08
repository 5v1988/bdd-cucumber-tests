package pages;

import io.cucumber.guice.ScenarioScoped;
import javax.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@ScenarioScoped
public class CreateNewAccountPage extends BasePage{

  @Inject
  public CreateNewAccountPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(how = How.CSS, using = "#firstname")
  private WebElement firstName;

  @FindBy(how = How.CSS, using = "#lastname")
  private WebElement lastName;

  @FindBy(how = How.CSS, using = "#is_subscribed")
  private WebElement isSubscribed;

  @FindBy(how = How.CSS, using = "#email_address")
  private WebElement email;

  @FindBy(how = How.CSS, using = "#password")
  private WebElement password;

  @FindBy(how = How.CSS, using = "#password-confirmation")
  private WebElement confirmPassword;

  @FindBy(how = How.XPATH, using = "//button[.//span[text()='Create an Account']]")
  private WebElement createAnAccount;

  public void enterPersonalInfo(String firstName, String lastName) {
    this.firstName.sendKeys(firstName);
    this.lastName.sendKeys(lastName);
  }

  public void signUpNewsletter(String subscribe) {
    boolean state = isSubscribed.isSelected();
    if (subscribe.equalsIgnoreCase("checks") && !state) {
      isSubscribed.click();
    }
  }

  public void enterSignInInfo(String email,String password,String confirmPassword) {
    this.email.sendKeys(email);
    this.password.sendKeys(password);
    this.confirmPassword.sendKeys(confirmPassword);
  }

  public void createAnAccount(){
    createAnAccount.click();
  }
}
