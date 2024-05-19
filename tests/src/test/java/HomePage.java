import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private By homeTextLocator = By
            .xpath("//h1[contains(@class, 'chakra-heading') and contains(text(), 'My Programs')]");

    private By settingsMenuLocator = By.id("menu-button-13");

    private By logoutButtonLocator = By.id("menu-list-13-menuitem-11");
    private By profileButtonLocator = By.id("menu-list-13-menuitem-5");

    private By coursePageLocator = By.xpath("//div[@class='css-ozl42r']//a[@href='/courses/ud065/resume-learning' and contains(@class, 'chakra-button')]");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getHomePageTextAfterLogin() {
        WebElement homeTextElement = waitAndReturnElement(homeTextLocator);
        highlightElement(homeTextElement);
        return homeTextElement.getText();
    }

    public MainPage logout() {

        // Click on the menu button
        WebElement menuButton = waitAndReturnElement(settingsMenuLocator);
        menuButton.click();

        // Click on the logout button
        WebElement logoutButton = waitAndReturnElement(logoutButtonLocator);
        logoutButton.click();
        return new MainPage(this.driver);
    }
    public ProfilePage goToProfilePage() {

        // Click on the menu button
        WebElement menuButton = waitAndReturnElement(settingsMenuLocator);
        menuButton.click();

        // Click on the logout button
        WebElement profileButton = waitAndReturnElement(profileButtonLocator);
        profileButton.click();
        return new ProfilePage(this.driver);
    }

    public CoursePage goToCoursePage() {
        WebElement coursePage = waitAndReturnElement(coursePageLocator);
        coursePage.click();
        return new CoursePage(this.driver);
    }

 

    public void displaySettingsMenu() {
        WebElement menuListElement = waitAndReturnElement(settingsMenuLocator);

        // Find all the menu items within the menu list
        List<WebElement> menuItems = menuListElement.findElements(By.tagName("a"));

        // Iterate through each menu item and print its text
        for (WebElement menuItem : menuItems) {
            System.out.println("Menu Item Text: " + menuItem.getText());
            System.out.println("Menu Item URL: " + menuItem.getAttribute("href"));
        }
    }
}
