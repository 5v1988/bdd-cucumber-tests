package glue.injector;

import com.github.javafaker.Faker;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.InputStream;
import model.TestConfig;
import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;

public final class CucumberModule extends AbstractModule {

  @Provides @Singleton
  WebDriver providesBrowserInstance(){
    return WebDriverManager.chromedriver().create();
  }

  @Provides @Singleton
  Faker providesFakerInstance(){ return new Faker(); }

  @Provides @Singleton
  TestConfig providesTestConfiguration(){
    Yaml yaml = new Yaml();
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config/test-config.yml");
    TestConfig testConfig = yaml.loadAs(inputStream, TestConfig.class);
    return testConfig;
  }
}





