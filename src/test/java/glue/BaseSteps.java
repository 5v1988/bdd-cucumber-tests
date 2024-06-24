package glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;

public class BaseSteps extends StepHelpers {

  private final Logger log = Logger.getLogger(BaseSteps.class);

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
