package glue.injector;

import com.github.javafaker.Faker;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.cucumber.guice.ScenarioScoped;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import model.TestConfig;
import model.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
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
    String browser = Optional.ofNullable(testConfig.getBrowser()).get();
    switch (browser) {
      case "firefox":
        return new FirefoxDriver(new FirefoxOptions());
      case "edge":
        return new EdgeDriver(EdgeDriverService.createDefaultService());
      case "remote-chrome":
        ChromeOptions chromeOptions = new ChromeOptions();
        return new RemoteWebDriver(new URL("http://127.0.0.1:4444"), chromeOptions);
      case "remote-firefox":
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new RemoteWebDriver(new URL("http://127.0.0.1:4444"), firefoxOptions);
      default:
        return new ChromeDriver(ChromeDriverService.createDefaultService());
    }
  }

}






