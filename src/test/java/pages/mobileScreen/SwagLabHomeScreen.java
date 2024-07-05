package pages.mobileScreen;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import javax.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.SimpleMobileScreen;

public class SwagLabHomeScreen extends SimpleMobileScreen {

  @Inject
  public SwagLabHomeScreen(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PRODUCTS']")
  private WebElement title;

  public boolean isHomeDisplayed() {
    pause(3);
    return title.isDisplayed();
  }
}
