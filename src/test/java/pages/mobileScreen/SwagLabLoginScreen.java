package pages.mobileScreen;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import javax.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.SimpleMobileScreen;

public class SwagLabLoginScreen extends SimpleMobileScreen {
  @Inject
  public SwagLabLoginScreen(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  @AndroidFindBy(accessibility = "test-Username")
  @iOSXCUITFindBy(accessibility = "test-Username")
  private WebElement username;


  @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@name=\"test-Password\"]")
  private WebElement password;

  @AndroidFindBy(accessibility = "test-LOGIN")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'test-LOGIN'")
  private WebElement loginBtn;

  public void login(String username1, String password1){
    username.sendKeys(username1);
    password.sendKeys(password1);
    loginBtn.click();
  }
}
