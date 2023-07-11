# bdd-cucumber-tests

**Introduction**

This workshop intends to provide guidance on building test automation framework, mainly using Guice,
a dependency injection framework.It should take from 1 to 2 hours, depending on how deep you want to
go into each topic.

**Set-up Outline**

- [Step 1: **Set up a module & injector source**](#step-1-create-a-custom-module-and-an-injector-source) : Create a custom module and injector classes that 
provide and inject objects where required respectively.

- [Step 2: **Add a configuration provider**] : Add a method in the module class to provide test 
  configurations

- [Step 3: **Add a method to provide test context**] : Create a method inside the module class to 
  provide text context container

- [Step 4: **Add a method to provide randomly generated data**] :  Create a method that provides 
  randomly generated data to be used in the tests

- [Step 5: **Add a method to provide a WebDriver instance**] : Add a method that provides a webdriver
  instance for the given browser type

- [Step 6: **Add page class and inject WebDriver instance**] : Create a page class by injecting 
  driver instance with a PageFactory design pattern

- [Step 7: **Create step definition class and inject required dependencies**] : Define steps in the 
  glue codes, and inject all required dependencies

**Requirements**

[Java](https://www.java.com/en/) 

[Selenium](https://www.selenium.dev/)

[Cucumber](https://cucumber.io/docs/installation/java/)

**Scenarios**

The following are two scenarios that we automate, mainly to understand the aforementioned steps
- Create a new account
- Search products and place an order

# Step 1: Create a custom module and an injector source

```java
import com.google.inject.AbstractModule;
public final class FrameworkModule extends AbstractModule {
  
}
```

```java
public class CucumberInjectorSource implements InjectorSource {

  @Override
  public Injector getInjector() {
    return Guice.createInjector(Stage.PRODUCTION, CucumberModules.createScenarioModule(),
        new FrameworkModule());
  }
}
```


```java
  @Provides
  @Singleton
  TestConfig providesTestConfiguration() {
    Yaml yaml = new Yaml();
    InputStream inputStream = this.getClass().getClassLoader()
        .getResourceAsStream("config/test-config.yml");
    TestConfig testConfig = yaml.loadAs(inputStream, TestConfig.class);
    return testConfig;
  }
```

```java
  @Provides
  @ScenarioScoped
  TestContext<String, Object> providesTextContext() {
    return new TestContext<>();
  }

```

```java
  @Provides
  @Singleton
  Faker providesFakerInstance() {
    return new Faker();
  }
```

```java
  @Provides
  @ScenarioScoped
  @Inject
  WebDriver providesBrowserInstance(TestConfig testConfig) {
    String browser = Optional.ofNullable(testConfig.getBrowser()).get();
    switch (browser) {
      case "firefox":
        return WebDriverManager.firefoxdriver().create();
      case "edge":
        return WebDriverManager.edgedriver().create();
      default:
        return WebDriverManager.chromedriver().create();
    }
  }
```




