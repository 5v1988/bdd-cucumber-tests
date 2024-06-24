package glue;

import io.cucumber.java.en.When;
import javax.inject.Inject;
import pages.SignInPage;

public class SignInSteps extends StepHelpers {

  @Inject
  private SignInPage signInPage;

  @When("User logons using credentials: {string} and {string}")
  public void userLogonsUsingCredentials(String email, String password) {
    signInPage.signIn(email, password);
  }

}
