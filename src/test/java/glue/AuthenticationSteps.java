package glue;

import clients.PathIdentifier;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.response.AccessTokenResponse;
import org.assertj.core.api.Assertions;
import util.StepHelpers;

public class AuthenticationSteps extends StepHelpers {

    @Given("API User — Get and set valid access token")
    public void apiUserGetAndSetAccessToken() {
        RequestSpecification spec = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.URLENC)
                .formParam("grant_type", testConfig.getGrantType())
                .formParam("client_id", testConfig.getClientId())
                .formParam("client_secret", testConfig.getClientSecret());
        Response r = testApiClient.doPost("authenticate", PathIdentifier.ACCESS_TOKEN, spec);
        Assertions.assertThat(r.statusCode()).
                as("Assert that status code is '200'")
                .isEqualTo(200);
        AccessTokenResponse response = fromJsonString(r.asString(), AccessTokenResponse.class);
        testContext.put("access_token", response.getAccess_token());
    }

}
