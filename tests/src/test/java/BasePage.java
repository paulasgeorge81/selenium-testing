
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;



public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected By bodyLocator = By.tagName("body");
 
    public BasePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getBodyText() {
        WebElement bodyElement = waitAndReturnElement(bodyLocator);
        return bodyElement.getText();
    }

 

}
