import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    private By loginButtonLocator = By.xpath("//a[contains(@class, 'chakra-button') and contains(@href, 'sign-in')]");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.udacity.com/");
    }

     public LoginPage goToLoginPage() {
        WebElement loginButton = waitAndReturnElement(loginButtonLocator);
        loginButton.click();

        return new LoginPage(this.driver);
    }

}
