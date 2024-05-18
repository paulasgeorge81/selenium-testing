import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private By homeTextLocator = By
            .xpath("//h1[contains(@class, 'chakra-heading') and contains(text(), 'My Programs')]");

    private By menuButtonLocator = By.id("menu-button-13");

    private By logoutButtonLocator = By.id("menu-list-13-menuitem-11");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getHomePageTextAfterLogin() {
        WebElement homeTextElement = waitAndReturnElement(homeTextLocator);
        return homeTextElement.getText();
    }

    public MainPage logout() {

        // Click on the menu button
        WebElement menuButton = waitAndReturnElement(menuButtonLocator);
        menuButton.click();

        // Click on the logout button
        WebElement logoutButton = waitAndReturnElement(logoutButtonLocator);
        logoutButton.click();
        return new MainPage(this.driver);
    }
}
