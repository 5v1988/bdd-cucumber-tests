package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractUiScreen {
  final static Duration DEFAULT_TIME_OUT = Duration.ofSeconds(15);
  protected WebDriver driver;

  public void pause(long seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException ignored) {
    }
  }

  abstract public void openUrl(String url);

  abstract public void reloads();

  abstract public String getText(WebElement element);

  abstract public void scrollToView(WebElement element);

  abstract public void waitUntilPageLoad();

  abstract boolean waitUntil(By locator);

  abstract boolean waitUntil(WebElement element);

  abstract boolean waitUntil(WebElement element, Duration timeOut);

  abstract boolean waitUntil(List<WebElement> elements);

  boolean waitUntilText(WebElement element, Duration timeOut) {
    WebDriverWait wait = new WebDriverWait(driver, timeOut);
    return wait.until((driver) -> !element.getText().trim().isEmpty());
  }

}
