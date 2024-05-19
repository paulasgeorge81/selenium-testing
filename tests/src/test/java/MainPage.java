import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    
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
