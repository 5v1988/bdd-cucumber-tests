package pages.mobileScreen;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.guice.ScenarioScoped;
import javax.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.SimpleMobileScreen;

@ScenarioScoped
public class ListViewScreen extends SimpleMobileScreen {

  @Inject
  public ListViewScreen(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  @AndroidFindBy(accessibility = "Views")
  private WebElement views;

  @AndroidFindBy(xpath = "//*[@content-desc='Controls']")
  private WebElement controls;

  @AndroidFindBy(accessibility = "2. Dark Theme")
  private WebElement darkTheme;

  @FindBy(id = "io.appium.android.apis:id/edit")
  private WebElement text;

  public void tapViews() {
    views.click();
  }

  public void tapControls() {
    pause(3);
    controls.click();
  }

  public void selectTheme(String theme) {
    WebElement el = driver.findElement(AppiumBy.accessibilityId(theme));
    el.click();
  }

  public void enterOnText(String str) {
    text.click();
    text.sendKeys(str);
  }

  public String getTextFieldText() {
    return text.getText();
  }
}
