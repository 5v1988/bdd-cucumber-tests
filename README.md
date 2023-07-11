# bdd-cucumber-tests

**Introduction**

This workshop intends to provide guidance on building test automation framework, mainly using Guice,
a dependency injection framework.It should take from 1 to 2 hours, depending on how deep you want to
go into each topic.

**Set-up Outline**

- [Step 1: **Set up a module & injector source**](#step-1-create-a-custom-module-and-an-injector-source) : Create a custom module and injector classes that 
provide and inject objects where required respectively.

- [Step 2: **Add a configuration provider**](#step-2-create-a-method-that-provides-test-configurations) : Add a method in the module class to provide test 
  configurations

- [Step 3: **Add a method to provide test context**](#step-3-create-a-method-that-provides-testcontext) : Create a method inside the module class to 
  provide text context container

- [Step 4: **Add a method to provide randomly generated data**](#step-4-create-a-provider-that-returns-a-standard-faker-object) :  Create a method that provides 
  randomly generated data to be used in the tests

- [Step 5: **Add a method to provide a WebDriver instance**](#step-5-create-a-provider-that-returns-a-webdriver) : Add a method that provides a webdriver
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

Firstly, create a class that extends Guice's AbstractModule, where in we define methods that provide
all necessary objects while injecting in step definition classes, and also page classes.

```java
import com.google.inject.AbstractModule;
public final class FrameworkModule extends AbstractModule {
  
}
```

In `CucumberInjectorSource` class, there are two modules that are created via injectors — the first 
one is Cucumber related which comes from `cucumber-guice` library. This mainly ensures that all the 
page objects created are all on scenario scope, and are destroyed once the scenario ends.

In addition to Cucumber module, we also pass our own module `FrameworkModule` that we defined in the
previous step to the injector source that takes care of creating all dependent objects and injecting
them where needed, mostly on step definition and page classes.

```java
public class CucumberInjectorSource implements InjectorSource {

  @Override
  public Injector getInjector() {
    return Guice.createInjector(Stage.PRODUCTION, CucumberModules.createScenarioModule(),
        new FrameworkModule());
  }
}
```
[CucumberInjectorSource](https://github.com/5v1988/bdd-cucumber-tests/blob/master/src/test/java/glue/injector/CucumberInjectorSource.java)

# Step 2: Create a method that provides test configurations

Now that module is defined , let's begin by adding a provider within a module that create and 
returns an object to hold all test configuration like application url, browser to be used etc. This
is a simple POJO class, created using the values from a file in yaml format. Note that scope here,
which is `@Singleton`, meaning we will use the same singleton object throughout a test run.

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

# Step 3: Create a method that provides TestContext

In this step, we are just adding another provider `TestContext` which is plainly to share states
between steps within the same class and between the step definition classes. It stores values using
key and value. Also note that scope is `@ScenarioScoped` which will ensure that `TestContext` object
is be recreated for every scenario.

```java
  @Provides
  @ScenarioScoped
  TestContext<String, Object> providesTextContext() {
    return new TestContext<>();
  }

```
# Step 4: Create a provider that returns a standard Faker object

In this, one of the providers in `FrameworkModule` returns `Faker` object which will be injected into
the class, preferably in the step definition classes where we need randomly generated data, which
will be used in tests. Faker object will be in `@Singleton` scope. 

```java
  @Provides
  @Singleton
  Faker providesFakerInstance() {
    return new Faker();
  }
```

# Step 5: Create a provider that returns a WebDriver

This is a provider that returns `WebDriver` instance, for a given browser type. It's also worth to 
note that we also inject `TestConfig` object in the parameter,created in the previous step that takes
care of providing the value for browser type. In this case, it is designated as `@ScenarioScoped` as
I intend to use a new browser instance for each scenario in the suite.

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

Putting all these providers together, this is the view of `FrameworkModule` class: [FrameworkModule](https://github.com/5v1988/bdd-cucumber-tests/blob/master/src/test/java/glue/injector/FrameworkModule.java)



