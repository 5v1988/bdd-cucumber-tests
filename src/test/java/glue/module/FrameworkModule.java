package glue.module;

import clients.TestApiClient;
import com.github.javafaker.Faker;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import enabler.TestContext;
import enabler.driver.MobileDriverFactory;
import enabler.driver.WebDriverFactory;
import enabler.util.TestConfig;
import enabler.util.config.TargetName;
import io.cucumber.guice.ScenarioScoped;
import java.io.InputStream;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;

public final class FrameworkModule extends AbstractModule {

  @Provides
  @Singleton
  Faker providesFakerInstance() {
    return new Faker();
  }

  @Provides
  @ScenarioScoped
  TestContext<String, Object> providesTextContext() {
    return new TestContext<>();
  }

  @Provides
  @Singleton
  TestConfig providesTestConfiguration() {
    Yaml yaml = new Yaml();
    InputStream inputStream = this.getClass().getClassLoader()
        .getResourceAsStream("config/test-config.yml");
    return yaml.loadAs(inputStream, TestConfig.class);
  }

  @Provides
  @ScenarioScoped
  @Inject
  TestApiClient providesTestApiClient(TestConfig testConfig) {
    return new TestApiClient(testConfig.getApiBaseUri());
  }

  @Provides
  @ScenarioScoped
  @Inject
  WebDriver providesBrowserInstance(TestConfig testConfig) {
    WebDriver driver = null;
    String targetName =
        !Objects.isNull(System.getProperty("targetName")) ? System.getProperty("targetName")
            : testConfig.getTargetName();
    if (TargetName.WEB == TargetName.fromString(targetName)) {
      if (!Objects.isNull(System.getProperty("browserName"))) {
        testConfig.setBrowserName(System.getProperty("browserName"));
      }
      driver = new WebDriverFactory().getWebDriver(testConfig);
    } else if (TargetName.MOBILE == TargetName.fromString(targetName)) {
      driver = new MobileDriverFactory().getWebDriver(testConfig);
    }
    return driver;
  }
}