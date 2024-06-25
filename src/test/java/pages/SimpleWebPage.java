package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleWebPage extends AbstractUiScreen {
  private final static String ALERT_ELEMENT_XPATH = "//div[@role='alert'][.//div[contains(normalize-space(),'%s')]]";
  public SimpleWebPage() {

  }
  @Override
  public void waitUntilPageLoad() {
    new WebDriverWait(driver, DEFAULT_TIME_OUT)
        .until(driver -> ((JavascriptExecutor) driver)
            .executeScript("return document.readyState").equals("complete"));
  }

  @Override
  public String getText(WebElement element) {
    return element.getText();
  }

  @Override
  public void scrollToView(WebElement element) {

  }

  @Override
  public void openUrl(String url) {
    System.out.println(driver);
    System.out.println("driver");
    driver.get(url);
  }

  @Override
  public void reloads() {
    driver.navigate().refresh();
  }

  @Override
  boolean waitUntil(By locator) {
    WebDriverWait wait = new WebDriverWait(driver, AbstractUiScreen.DEFAULT_TIME_OUT);
    return wait.until((driver) -> !driver.findElements(locator).isEmpty());
  }

  @Override
  boolean waitUntil(WebElement element) {
    WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
    return wait.until(ExpectedConditions.elementToBeClickable(element))
        .isDisplayed();
  }

  @Override
  boolean waitUntil(WebElement element, Duration timeOut) {
    WebDriverWait wait = new WebDriverWait(driver, timeOut);
    return wait.until(ExpectedConditions.elementToBeClickable(element))
        .isDisplayed();
  }

  @Override
  boolean waitUntil(List<WebElement> elements) {
    WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
    return !wait.until(ExpectedConditions.visibilityOfAllElements(elements)).isEmpty();
  }

  public boolean IsAlertMessageDisplayed(String message) {
    return waitUntil(By.xpath(String.format(ALERT_ELEMENT_XPATH, message)));
  }

}
