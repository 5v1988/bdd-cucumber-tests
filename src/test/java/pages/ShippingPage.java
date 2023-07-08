package pages;

import io.cucumber.guice.ScenarioScoped;
import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@ScenarioScoped
public class ShippingPage extends BasePage {

  @Inject
  public ShippingPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  private final static String SHIPPING_METHOD_XPATH = "//tr[contains(normalize-space(),'Flat Rate')]//input";

  @FindBy(how = How.CSS, using = "input[data-bind*='email']")
  private WebElement emailAddress;

  @FindBy(how = How.CSS, using = "[name='firstname']")
  private WebElement firstName;

  @FindBy(how = How.CSS, using = "[name='lastname']")
  private WebElement lastName;

  @FindBy(how = How.CSS, using = "[name='company']")
  private WebElement company;

  @FindBy(how = How.CSS, using = "input[name*='street']")
  private WebElement streetLines;

  @FindBy(how = How.CSS, using = "[name='city']")
  private WebElement city;

  @FindBy(how = How.CSS, using = "[name='region_id']")
  private WebElement state;

  @FindBy(how = How.CSS, using = "[name='postcode']")
  private WebElement zip;

  @FindBy(how = How.CSS, using = "[name='country_id']")
  private WebElement country;

  @FindBy(how = How.CSS, using = "[name='telephone']")
  private WebElement phoneNumber;

  @FindBy(how = How.XPATH, using = "//tr[contains(normalize-space(),'Flat Rate')]//input")
  private WebElement flatRate;

  @FindBy(how = How.XPATH, using = "//tr[contains(normalize-space(),'Best Way')]//input")
  private WebElement bestWay;

  @FindBy(how = How.XPATH, using = "//button[.//span[text()='Next']]")
  private WebElement nextBtn;

  public void enterPersonalInfo(String email, String firstName, String lastName) {
    waitUntil(this.lastName);
    this.emailAddress.sendKeys(email);
    this.firstName.sendKeys(firstName);
    this.lastName.sendKeys(lastName);
  }

  public void enterShippingAddress(Map<String, String> shippingAddress) {
    waitUntil(emailAddress);
    streetLines.sendKeys(shippingAddress.get("street1"));
    city.sendKeys(shippingAddress.get("city"));
    state.sendKeys(shippingAddress.get("state"));
    zip.sendKeys(shippingAddress.get("zip"));
    country.sendKeys(shippingAddress.get("country"));
    phoneNumber.sendKeys(shippingAddress.get("phoneNumber"));
  }


  public void selectShippingMethodsAndProceedNext(String shippingMethod) {
    String shippingMethodXpath = String.format(SHIPPING_METHOD_XPATH, shippingMethod);
    Optional<WebElement> shippingMethods = Optional.ofNullable(
        driver.findElement(By.xpath(shippingMethodXpath)));
    Assertions.assertThat(shippingMethods.isPresent()).isTrue();
    shippingMethods.get().click();
    nextBtn.click();
  }

}
