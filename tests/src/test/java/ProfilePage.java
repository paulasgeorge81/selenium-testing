import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage  extends BasePage{

    private By firstNameLocator = By.id("field-18");
    private By lastNameLocator = By.id("field-19");
    private By saveButtonLocator = By.xpath("//button[contains(@class, 'chakra-button') and contains(@class, 'css-d9cnjd') and text()='Save']");
    
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void changeBasicInfo(String firstName, String lastName) {
        WebElement firstNameField = waitAndReturnElement(firstNameLocator);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = waitAndReturnElement(lastNameLocator);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);


        WebElement saveButton = waitAndReturnElement(saveButtonLocator);
        saveButton.click();
    }

  
    
}
