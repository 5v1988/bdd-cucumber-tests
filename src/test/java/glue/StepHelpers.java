package glue;

import clients.TestApiClient;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import model.TestConfig;
import model.TestContext;
import util.Formatters;
import util.Mappers;

public class StepHelpers implements Mappers, Formatters {
    @Inject
    protected TestApiClient testApiClient;
    @Inject
    protected TestConfig testConfig;
    @Inject
    protected TestContext<String, Object> testContext;
    @Inject
    protected Faker faker;
}
