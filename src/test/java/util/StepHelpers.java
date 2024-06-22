package util;

import clients.TestApiClient;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import model.TestConfig;
import model.TestContext;

public class StepHelpers implements Mappers, Formatters{
    @Inject
    protected TestApiClient testApiClient;

    @Inject
    protected TestConfig testConfig;

    @Inject
    protected TestContext<String, Object> testContext;

    @Inject
    protected Faker faker;
}
