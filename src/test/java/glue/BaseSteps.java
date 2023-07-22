package glue;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseSteps {
  private Logger log = LoggerFactory.getLogger(BaseSteps.class);
  @Inject
  WebDriver driver;

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
