import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private By usernameLocator = By.id("email");
    private By passwordLocator = By.id("revealable-password");
    private By loginButtonLocator = By.xpath("//button[contains(@class, 'vds-button') and contains(@class, 'vds-button--primary') and normalize-space(.)='Sign in']"); // Login button
    private By unsuccessfullLoginMessageLocator = By.xpath("//div[contains(@class, 'alert_error__3rDC3')]"); 
    
     

     public LoginPage(WebDriver driver) {
        super(driver);
    }


    public HomePage login(String username, String password) {

        WebElement usernameField = waitAndReturnElement(usernameLocator);
        usernameField.sendKeys(username);

        WebElement passwordField = waitAndReturnElement(passwordLocator);
        passwordField.sendKeys(password);

        WebElement loginButton = waitAndReturnElement(loginButtonLocator);
        loginButton.click();

        return new HomePage(this.driver);
    }

    public LoginPage loginWithInvalidCredentials(String username, String password) {

        WebElement usernameField = waitAndReturnElement(usernameLocator);
        usernameField.sendKeys(username);

        WebElement passwordField = waitAndReturnElement(passwordLocator);
        passwordField.sendKeys(password);

        WebElement loginButton = waitAndReturnElement(loginButtonLocator);
        loginButton.click();

        return new LoginPage(this.driver);
    }

    public String getLoginErrorMessage() {
        WebElement messageElement = waitAndReturnElement(unsuccessfullLoginMessageLocator);
        return messageElement.getText();
    }
    
}
