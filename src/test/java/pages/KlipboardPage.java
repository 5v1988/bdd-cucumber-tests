package pages;

import io.cucumber.guice.ScenarioScoped;
import javax.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@ScenarioScoped
public class KlipboardPage extends SimpleWebPage {

  @Inject
  public KlipboardPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(how = How.ID, using = "data")
  private WebElement textArea;

  @FindBy(how = How.CSS, using = "#clear-btn")
  private WebElement clearBtn;

  @FindBy(how = How.CSS, using = "#save-btn")
  private WebElement saveBtn;

  public void clear() {
    moveAndClick(clearBtn);
    moveAndClick(saveBtn);
  }

  public void add(String data) {
    textArea.sendKeys(data);
    moveAndClick(saveBtn);
  }

  public String getKlipboardData(){
    return getText(textArea);
  }
}
