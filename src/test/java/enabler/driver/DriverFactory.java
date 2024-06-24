package enabler.driver;

import enabler.util.AutomationName;
import enabler.util.Formatters;
import org.openqa.selenium.WebDriver;

public abstract class DriverFactory implements Formatters {

  WebDriver driver;

  public abstract WebDriver getWebDriver(AutomationName automationName);
}
