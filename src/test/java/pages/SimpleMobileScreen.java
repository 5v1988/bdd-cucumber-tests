package pages;

import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;

public class SimpleMobileScreen extends AbstractUiScreen {

  private final Duration NO_TIME = Duration.ofMillis(0);
  private final Duration STEP_DURATION = Duration.ofMillis(20);
  private final Origin VIEW = Origin.viewport();

  public SimpleMobileScreen() {}

  @Override
  public void waitUntilPageLoad() {

  }

  @Override
  public void openUrl(String url) {

  }

  @Override
  public void reloads() {

  }

  @Override
  public void moveAndClick(WebElement el) {

  }

  @Override
  public String getText(WebElement element) {
    return element.getText();
  }

  @Override
  public void scrollToView(WebElement element) {
    int pointX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
    int pointY = element.getLocation().getY();
    PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
    Sequence scrollSequence = new Sequence(finger, 0);
    scrollSequence.addAction(finger.createPointerMove(NO_TIME, VIEW, pointX, 0));
    scrollSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    scrollSequence.addAction(finger.createPointerMove(STEP_DURATION, VIEW, pointX, pointY));
    scrollSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    ((AppiumDriver) driver).perform(List.of(scrollSequence));
  }

  @Override
  boolean waitUntil(By locator) {
    return false;
  }

  @Override
  boolean waitUntil(WebElement element) {
    return false;
  }

  @Override
  boolean waitUntil(WebElement element, Duration timeOut) {
    return false;
  }

  @Override
  boolean waitUntil(List<WebElement> elements) {
    return false;
  }

}
