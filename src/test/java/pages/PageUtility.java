package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageUtility {

  final static Duration DEFAULT_TIME_OUT = Duration.ofSeconds(15);
  WebDriver driver;

  public PageUtility(WebDriver driver) {
    this.driver = driver;
  }

  public void pause(Duration seconds) {
    try { Thread.sleep(seconds.toMillis());}
    catch (InterruptedException ie) {
    }
  }

  public void waitUntilPageLoad() {
    new WebDriverWait(driver, DEFAULT_TIME_OUT)
        .until(driver -> ((JavascriptExecutor) driver)
            .executeScript("return document.readyState").equals("complete"));
  }

  boolean waitUntil(By locator, Duration timeOut) {
    WebDriverWait wait = new WebDriverWait(driver, timeOut);
    return wait.until((driver) -> driver.findElements(locator).size() > 0);
  }

  boolean waitUntil(List<WebElement> elements) {
    WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
    return wait.until(ExpectedConditions.visibilityOfAllElements(elements))
        .size() > 0;
  }

  boolean waitUntil(List<WebElement> elements, Duration timeOut) {
    WebDriverWait wait = new WebDriverWait(driver, timeOut);
    return wait.until((driver) -> elements.size() > 0);
  }

  boolean waitUntil(WebElement element, Duration timeOut) {
    WebDriverWait wait = new WebDriverWait(driver, timeOut);
    return wait.until(ExpectedConditions.elementToBeClickable(element))
        .isDisplayed();
  }

  boolean waitUntil(WebElement element) {
    WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
    return wait.until(ExpectedConditions.elementToBeClickable(element))
        .isDisplayed();
  }

  boolean waitUntilText(WebElement element, Duration timeOut) {
    WebDriverWait wait = new WebDriverWait(driver, timeOut);
    return wait.until((driver) -> element.getText().trim().length() > 0);
  }
}
