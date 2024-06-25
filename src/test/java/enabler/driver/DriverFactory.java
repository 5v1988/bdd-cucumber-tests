package enabler.driver;

import enabler.util.Formatters;
import enabler.util.TestConfig;
import org.openqa.selenium.WebDriver;

public abstract class DriverFactory implements Formatters {

  WebDriver driver;

  public abstract WebDriver getWebDriver(TestConfig config);
}
