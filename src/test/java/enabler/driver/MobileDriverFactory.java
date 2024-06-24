package enabler.driver;

import enabler.exception.TestRuntimeException;
import enabler.util.AutomationName;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;

public class MobileDriverFactory extends DriverFactory {

  @Override
  public WebDriver getWebDriver(AutomationName automationName) {
    switch (automationName) {
      case CHROME:
        ChromeOptions chromeOptions;
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        return new ChromeDriver(chromeOptions);
      case UIAUTOMATOR2:
        return new AndroidDriver(new FirefoxOptions());
      case XCUITEST:
        return new EdgeDriver(EdgeDriverService.createDefaultService());
    }
    throw new TestRuntimeException(sf("Browser: %s is not a valid selection",
        automationName.toString()));
  }
}
