package enabler.driver;

import enabler.exception.TestRuntimeException;
import enabler.util.config.BrowserName;
import enabler.util.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory extends DriverFactory {

  @Override
  public WebDriver getWebDriver(TestConfig config) {
    BrowserName browserName = BrowserName.fromString(config.getBrowserName());
    switch (browserName) {
      case CHROME:
        ChromeOptions chromeOptions;
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        return new ChromeDriver(chromeOptions);
      case FIREFOX:
        return new FirefoxDriver(new FirefoxOptions());
      case EDGE:
        return new EdgeDriver(EdgeDriverService.createDefaultService());
    }
    throw new TestRuntimeException(sf("Browser: %s is not a valid selection",
        browserName.toString()));
  }
}
