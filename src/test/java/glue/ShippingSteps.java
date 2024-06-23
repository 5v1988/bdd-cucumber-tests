package glue;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;
import pages.ShippingPage;

public class ShippingSteps {

  @Inject
  private ShippingPage shippingPage;
  @Inject
  Faker faker;

  @When("User enters the following personal shipping info:")
  public void userEntersTheFollowingPersonalShippingInfo(Map<String, String> shippingInfo) {
    String email = Optional.ofNullable(shippingInfo.get("email"))
        .orElse(faker.internet().emailAddress());
    String firstName = Optional.ofNullable(shippingInfo.get("firstName"))
        .orElse(faker.name().firstName());
    String lastName = Optional.ofNullable(shippingInfo.get("lastName"))
        .orElse(faker.name().lastName());
    shippingPage.enterPersonalInfo(email, firstName, lastName);
  }


  @When("User enters the following shipping address info:")
  public void userEntersTheFollowingShippingAddressInfo(
      List<Map<String, String>> list) {
    Address address = faker.address();
    String phoneNumber = faker.phoneNumber().phoneNumber();
    Map<String, String> shippingAddressInfo = new HashMap<>(list.get(0));
    shippingAddressInfo.put("street1", Optional.ofNullable(shippingAddressInfo.get("street1"))
        .orElse(address.streetAddress()));
    shippingAddressInfo.put("city", Optional.ofNullable(shippingAddressInfo.get("city"))
        .orElse(address.city()));
    shippingAddressInfo.put("state", Optional.ofNullable(shippingAddressInfo.get("state"))
        .orElse(address.state()));
    shippingAddressInfo.put("zip", Optional.ofNullable(shippingAddressInfo.get("zip"))
        .orElse(address.zipCode()));
    shippingAddressInfo.put("country", Optional.ofNullable(shippingAddressInfo.get("country"))
        .orElse(address.country()));
    shippingAddressInfo.put("phoneNumber",
        Optional.ofNullable(shippingAddressInfo.get("phoneNumber"))
            .orElse(phoneNumber));
    shippingPage.enterShippingAddress(shippingAddressInfo);
  }


  @When("User chooses shipping method: {string} and proceeds next")
  public void userChoosesShippingMethodAndProceedsNext(String shippingMethod) {
    shippingPage.selectShippingMethodsAndProceedNext(shippingMethod);
  }


}
