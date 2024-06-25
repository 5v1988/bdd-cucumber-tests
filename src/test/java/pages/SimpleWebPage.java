package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleWebPage extends AbstractUiScreen {
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
    driver.get(url);
  }

  @Override
  public void reloads() {
    driver.navigate().refresh();
    pause(2);
  }

  @Override
  public void moveAndClick(WebElement el) {
    final Actions actions = new Actions(driver);
    actions.moveToElement(el).pause(Duration.ofMillis(250))
        .click().build().perform();
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

}
