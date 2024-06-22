package glue;

import clients.PathIdentifier;
import clients.TestApiClient;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.request.CreateBeneficiaryRequest;
import model.response.CreateBeneficiaryErrorResponse;
import model.response.CreateBeneficiaryResponse;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import util.StepHelpers;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class BeneficiarySteps extends StepHelpers {
    private final Logger log = Logger.getLogger(BeneficiarySteps.class);
    @Inject
    private TestApiClient testApiClient;

    @When("API User — Create beneficiary using the following data:")
    public void apiUserCreateBeneficiaryUsingTheFollowingData(String data) {
        String authToken = (String) testContext.get("access_token");
        log.info(sf("retrieved access token: %s", authToken));
        CreateBeneficiaryRequest payload = fromJsonString(data, CreateBeneficiaryRequest.class);
        String name = faker.name().fullName();
        payload.setName(name);
        testContext.put("name", name);
        RequestSpecification spec = RestAssured.given().when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("authorization", sf("Bearer %s", authToken))
                .body(payload);
        Response r = testApiClient.doPost("Create Beneficiary", PathIdentifier.CREATE_BENEFICIARIES, spec);
        Assertions.assertThat(r.statusCode()).
                as("Assert that status code is '200'")
                .isEqualTo(200);
        testContext.put("actualCreateBeneficiaryResponse", fromJsonString(r.asString(),
                CreateBeneficiaryResponse.class));
    }

    @When("API User — Create beneficiary using the invalid data:")
    public void apiUserCreateBeneficiaryUsingTheInvalidData(String data) {
        String authToken = (String) testContext.get("access_token");
        CreateBeneficiaryRequest payload = fromJsonString(data, CreateBeneficiaryRequest.class);
        RequestSpecification spec = RestAssured.given().when()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("authorization", sf("Bearer %s", authToken))
                .body(payload);
        Response r = testApiClient.doPost("Create Beneficiary with invalid data",
                PathIdentifier.CREATE_BENEFICIARIES, spec);
        Assertions.assertThat(r.statusCode()).
                as("Assert that status code is '400'")
                .isEqualTo(400);
        testContext.put("actualCreateBeneficiaryErrorResponse", fromJsonString(r.asString(),
                CreateBeneficiaryErrorResponse.class));
    }

    @Then("API User — Verify that beneficiary is created")
    public void apiUserVerifyThatBeneficiaryIsCreated() {
        SoftAssertions asserts = new SoftAssertions();
        CreateBeneficiaryResponse actualResponse = (CreateBeneficiaryResponse)
                testContext.get("actualCreateBeneficiaryResponse");
        asserts.assertThat(actualResponse.getData().getName())
                .as(sf("Assert that Name: %s matches", testContext.get("name")))
                .isEqualTo(testContext.get("name"));
        asserts.assertThat(actualResponse.getCode())
                .as(sf("Assert that Code: is OK"))
                .isEqualTo("OK");
        asserts.assertThat(actualResponse.getData().getId())
                .as(sf("Assert that Id: is not null"))
                .isNotNull();
        asserts.assertThat(actualResponse.getData().getZipCode())
                .as(sf("Assert that ZipCode: is null"))
                .isNull();
        asserts.assertAll();
    }

    @Then("API User — Verify that beneficiary is not created")
    public void apiUserVerifyThatBeneficiaryIsNotCreated() {
        SoftAssertions asserts = new SoftAssertions();
        CreateBeneficiaryErrorResponse actualErrorResponse = (CreateBeneficiaryErrorResponse)
                testContext.get("actualCreateBeneficiaryErrorResponse");
        asserts.assertThat(actualErrorResponse.getCode())
                .as(sf("Assert that Code: is 'MODEL_VALIDATION_ERR'"))
                .isEqualTo("MODEL_VALIDATION_ERR");
        asserts.assertThat(actualErrorResponse.getMsg())
                .as(sf("Assert that msg: is 'Invalid arguments to the API.'"))
                .isEqualTo("Invalid arguments to the API.");
        asserts.assertThat(actualErrorResponse.getData())
                .as(sf("Assert that Data: is null"))
                .isNull();
        asserts.assertAll();
    }

    @Then("API User — Verify the error message for the following field:")
    public void apiUserVerifyTheErrorMessageForTheFollowingField(Map<String, String> expected) {
        SoftAssertions asserts = new SoftAssertions();
        CreateBeneficiaryErrorResponse actualErrorResponse = (CreateBeneficiaryErrorResponse)
                testContext.get("actualCreateBeneficiaryErrorResponse");
        actualErrorResponse.getErrors().forEach(err -> {
            if (err.getField().equals(expected.get("field"))) {
                asserts.assertThat(err.getMessage())
                        .as(sf("Assert that message: is '%s'", expected.get("message")))
                        .isEqualTo(expected.get("message"));
            }
        });
        asserts.assertAll();
    }


    @Then("API User — Verify the following errors for the respective fields:")
    public void apiUserVerifyTheFollowingErrorsForTheRespectiveFields(Map<String, String> expectedError) {
        SoftAssertions asserts = new SoftAssertions();
        CreateBeneficiaryErrorResponse actualErrorResponse = (CreateBeneficiaryErrorResponse)
                testContext.get("actualCreateBeneficiaryErrorResponse");
////        expectedErrors.forEach(error -> {
////            asserts.assertThat(error.get("field"))
////            error.getField()
//        });
    }

}
