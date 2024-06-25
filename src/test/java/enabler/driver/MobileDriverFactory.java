package enabler.driver;

import enabler.exception.TestRuntimeException;
import enabler.util.config.AutomationName;
import enabler.util.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;

public class MobileDriverFactory extends DriverFactory {

  @Override
  public WebDriver getWebDriver(TestConfig config) {
    AutomationName automationName = AutomationName.fromString(config.getAutomationName());
    switch (automationName) {
      case UIAUTOMATOR2:
        UiAutomator2Options opts = new UiAutomator2Options();
        opts.setPlatformName(config.getPlatformName())
            .setAutomationName(config.getAutomationName())
            .setApp(config.getApp());
//        UiAutomator2Options uiAutomator2Options = Objects.nonNull(config.getBrowserName()) ?
//            opts.withBrowserName(config.getBrowserName()) :
//            opts.setApp(config.getApp());
        try {
          return new AndroidDriver(new URL(config.getAppiumUrl()), opts);
        } catch (MalformedURLException e) {
          throw new TestRuntimeException("Check the remote url", e);
        }
      case XCUITEST:
        return new EdgeDriver(EdgeDriverService.createDefaultService());
    }
    throw new TestRuntimeException(sf("Browser: %s is not a valid selection",
        automationName.toString()));
  }
}
