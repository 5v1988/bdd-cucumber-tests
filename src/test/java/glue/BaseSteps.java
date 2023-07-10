package glue;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class BaseSteps {

  @Inject
  WebDriver driver;

  @Before
  public void beforeTest() {
  }

  @After
  public void afterTest() {
    driver.quit();
  }

  
}
