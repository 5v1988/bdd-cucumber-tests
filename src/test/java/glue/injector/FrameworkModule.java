package glue.injector;

import com.github.javafaker.Faker;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.cucumber.guice.ScenarioScoped;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import model.TestConfig;
import model.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
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
    TestConfig testConfig = yaml.loadAs(inputStream, TestConfig.class);
    return testConfig;
  }

  @Provides
  @ScenarioScoped
  @Inject
  WebDriver providesBrowserInstance(TestConfig testConfig) throws Exception {
    ChromeOptions chromeOptions;
    String browser = Optional.ofNullable(
        !Objects.isNull(System.getProperty("browser")) ? System.getProperty("browser")
            : testConfig.getBrowser()).get();
    switch (browser) {
      case "firefox":
        return new FirefoxDriver(new FirefoxOptions());
      case "edge":
        return new EdgeDriver(EdgeDriverService.createDefaultService());
      case "remote-chrome":
        chromeOptions = new ChromeOptions();
        return new RemoteWebDriver(new URL(testConfig.getGridUrl()), chromeOptions);
      case "remote-firefox":
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new RemoteWebDriver(new URL(testConfig.getGridUrl()), firefoxOptions);
      default:
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        return new ChromeDriver(chromeOptions);
    }
  }

}






