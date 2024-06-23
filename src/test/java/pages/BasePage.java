package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends PageUtility {

  WebDriver driver;
  private final static String ALERT_ELEMENT_XPATH = "//div[@role='alert'][.//div[contains(normalize-space(),'%s')]]";

  public BasePage(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void openUrl(String url) {
    driver.get(url);
  }

  public boolean IsAlertMessageDisplayed(String message) {
    return waitUntil(By.xpath(String.format(ALERT_ELEMENT_XPATH, message)));
  }

}
