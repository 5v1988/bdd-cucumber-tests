package glue;

import clients.TestApiClient;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import enabler.util.TestConfig;
import enabler.TestContext;
import enabler.util.Formatters;
import enabler.Mappers;
import org.openqa.selenium.WebDriver;

public class StepHelpers implements Mappers, Formatters {
    @Inject
    protected WebDriver driver;
    @Inject
    protected TestApiClient testApiClient;
    @Inject
    protected TestConfig testConfig;
    @Inject
    protected TestContext<String, Object> testContext;
    @Inject
    protected Faker faker;
}
