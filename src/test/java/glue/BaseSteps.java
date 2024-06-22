package glue;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class BaseSteps {
  private final Logger log = Logger.getLogger(BaseSteps.class);
  @Inject
  protected WebDriver driver;

  @Before
  public void beforeTest() {
    log.info("Tests are starting up!");
  }

  @After
  public void afterTest() {
    driver.quit();
    log.info("Tests are finished running!");
  }

  
}
