import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CoursePage  extends BasePage{

    private By coursePageTextLocator = By.xpath("//div[contains(@class ,'chakra-stack')]//section//h1[contains(@class,'chakra-heading')]");
    
    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public String getCoursePageText() {
         WebElement coursePageTextElement = waitAndReturnElement(coursePageTextLocator);
        return coursePageTextElement.getText();
    }
    
}
