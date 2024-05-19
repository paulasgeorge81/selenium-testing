import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    private ConfigReader configReader;
    private String baseUrl;

    public MainPage(WebDriver driver) {
        super(driver);

        configReader = new ConfigReader();
        baseUrl = configReader.getProperty("baseUrl");
        this.driver.get(baseUrl);
    }

     public LoginPage goToLoginPage() {
        WebElement loginButton = waitAndReturnElement(loginButtonLocator);
        loginButton.click();

        return new LoginPage(this.driver);
    }

   

}
