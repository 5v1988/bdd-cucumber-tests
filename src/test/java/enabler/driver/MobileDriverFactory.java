package enabler.driver;

import enabler.exception.TestRuntimeException;
import enabler.util.config.AutomationName;
import enabler.util.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;

public class MobileDriverFactory extends DriverFactory {

  @Override
  public WebDriver getWebDriver(TestConfig config) {
    AutomationName automationName = AutomationName.fromString(config.getAutomationName());
    try {
      switch (automationName) {
        case UIAUTOMATOR2:
          UiAutomator2Options ui2Opts = new UiAutomator2Options();
          ui2Opts.setPlatformName(config.getPlatformName())
              .setAutomationName(config.getAutomationName());
          if (Objects.nonNull(config.getBrowserName())) {
            ui2Opts.withBrowserName(config.getBrowserName());
          } else {
            ui2Opts.setApp(config.getApp());
          }
          return new AndroidDriver(new URL(config.getAppiumUrl()), ui2Opts);
        case XCUITEST:
          XCUITestOptions xcuiOpts = new XCUITestOptions();
          xcuiOpts.setPlatformName(config.getPlatformName())
              .setAutomationName(config.getAutomationName());
          if (Objects.nonNull(config.getBrowserName())) {
            xcuiOpts.withBrowserName(config.getBrowserName());
          } else {
            xcuiOpts.setApp(config.getApp());
          }
          return new IOSDriver(xcuiOpts);
        default:
          throw new TestRuntimeException("Not a valid automation name: " + automationName);
      }
    } catch (MalformedURLException me) {
      throw new TestRuntimeException("Remote url doesn't seem to be working", me);
    } catch (SessionNotCreatedException se) {
      throw new TestRuntimeException("Failure creating the session", se);
    }
  }
}
