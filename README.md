# BDD CucumberJVM Workshop

**Introduction**

This workshop intends to provide guidance on building test automation framework, mainly using Guice,
a dependency injection framework from Google. It should take from 1 to 2 hours, depending on how deep you want to
go into each topic.

**Workshop Outline**

- [Step 0: **Define scenarios to be automated in Gherkin**](#step-0-define-scenarios-to-be-automated-in-bdd-format) : Create a couple of scenarios that
  are used to explain the concepts.

- [Step 1: **Set up a module & injector source**](#step-1-create-a-custom-module-and-an-injector-source) : Create a custom module and injector classes that 
provide and inject objects where required respectively.

- [Step 2: **Add a configuration provider**](#step-2-create-a-method-that-provides-test-configurations) : Add a method in the module class to provide test 
  configurations

- [Step 3: **Define a provider method for TestContext**](#step-3-create-a-method-that-provides-testcontext) : Create a method inside the module class to 
  provide text context container

- [Step 4: **Add a method to provide randomly generated data**](#step-4-create-a-provider-that-returns-a-standard-faker-object) :  Create a method that provides 
  randomly generated data to be used in the tests

- [Step 5: **Create a WebDriver instance provider**](#step-5-create-a-provider-that-returns-a-webdriver) : Add a method that provides a webdriver
  instance for the given browser type

- [Step 6: **Implement page class and inject WebDriver instance**](#step-6-create-pages-with-required-elements-and-methods) : Create a page class by injecting 
  driver instance with a PageFactory design pattern

- [Step 7: **Create step definition class and inject required dependencies**](#step-7-define-glue-codes-for-the-steps-in-feature-files) : Define steps in the 
  glue codes, and inject all required dependencies

- [Step 8: **Create a simple run configuration**](#step-8-create-a-simple-run-configuration) : Although there are various ways to run these tests, let's run
  using a standard gradle command to run tests (Junit test runner)

**Requirements**

[Java](https://www.java.com/en/) + [Gradle](https://gradle.org/install/)

[Selenium](https://www.selenium.dev/)

[Cucumber](https://cucumber.io/docs/installation/java/)

[Guice](https://github.com/google/guice)

**Scenarios**

The following are two scenarios that we automate, mainly to understand the aforementioned steps
- Create a new account
- Search products and place an order

## Step 0: Define scenarios to be automated in BDD format

Let's begin by writing steps for above two scenarios in Gherkin format. We need them as we use these
to learn the concepts required in building a robust and scalable automation framework. For instance,
the following steps are to be created for the first scenario: `Create a new account` in a feature file

```gherkin
@Smoke @NewAccount
Feature: Account Creation related scenarios

  Scenario Outline: Create a new account
    Given User opens home page: url
    When User navigates to create new customer account
    And User enters auto-generated personal info
    And User checks the checkbox 'Sign Up for Newsletter'
    And User enters following sign-in info:
      | email           |  |
      | password        |  |
      | confirmPassword |  |
    Then User creates an account and verifies the message: "<message>"

    Examples:
      | message                                            |
      | Thank you for registering with Main Website Store. |
```
All the features being used in this project can be viewed from this folder: [features](https://github.com/5v1988/bdd-cucumber-tests/tree/master/src/test/resources/features)
For further reference on as to how to write scenarios on Gherkin, you can follow my blog post on [Gherkin](https://medium.com/@5v1988/gherkin-for-qa-a-primer-to-write-scenarios-part-a-729ffb4a6212) on medium

## Step 1: Create a custom module and an injector source

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

## Step 2: Create a method that provides test configurations

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

## Step 3: Create a method that provides TestContext

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
## Step 4: Create a provider that returns a standard Faker object

In this, one of the providers in `FrameworkModule` returns `Faker` object which will be injected into
the class, preferably in the step definition classes where we need randomly generated data, which
will be used in our tests. Faker object will be in `@Singleton` scope. 

```java
  @Provides
  @Singleton
  Faker providesFakerInstance() {
    return new Faker();
  }
```

## Step 5: Create a provider that returns a WebDriver

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

## Step 6: Create pages with required elements and methods:

There are several page classes that we may have to create in reality, however for the sake of simplicity,
let's consider this class `ReviewAndPaymentsPage` that is implemented for Review & Payments page in 
our test app. If you notice that this class is scenario scoped, as mentioned previously, here we are
instantiating a page object, keeping it throughout the scenario, and discarding it once the scenario
is done, irrespective of the test status.

The next thing to note here is that, we are injecting `WebDriver` object in the page constructor 
parameter which is required to initialize the page elements using PageFactory design.

Lastly, we declared a page element `placeOrderBtn` and implemented a method `placeOrder` which is to
click and place an order.

The same pattern can be followed while implementing other pages on the testing app.

```java
@ScenarioScoped
public class ReviewAndPaymentsPage extends BasePage {

  @Inject
  public ReviewAndPaymentsPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }
  
  @FindBy(how = How.CSS, using = "button[title='Place Order']")
  private WebElement placeOrderBtn;

  public void placeOrder() {
    waitUntil(placeOrderBtn);
    pause(Duration.ofSeconds(3));
    placeOrderBtn.click();
  }

}
```

For the testing [app](https://magento.softwaretestingboard.com/), I used for this workshop, all the 
pages are defined within this package here:[pages](https://github.com/5v1988/bdd-cucumber-tests/tree/master/src/test/java/pages)

## Step 7: Define glue codes for the steps in feature files:

This is simple step definition class that contains an implementation for a step `User reviews payments and places order`. 
Since it's required `ReviewAndPaymentsPage` object to place an order, we inject it using `Guice` through
the module we defined on the first step. This way, we are allowed to inject other objects like Faker,
TestContext, TestConfig etc. in similar way.

```java
public class ReviewAndPaymentsSteps {

  @Inject
  ReviewAndPaymentsPage reviewAndPaymentsPage;

  @Then("User reviews payments and places order")
  public void userReviewsPaymentsAndPlacesOrder() {
    reviewAndPaymentsPage.placeOrder();
  }
  
}
```

Likewise, all the glue codes are defined within this package here: [glue](https://github.com/5v1988/bdd-cucumber-tests/tree/master/src/test/java/glue)

## Step 8: Create a simple run configuration:

```
 gradle test -Dcucumber.filter.tags="@NewAccount"
```
Godspeed!

Veera.

