import static org.junit.Assert.assertTrue;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class SeleniumTest {
    private WebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testSelenium() {
        // Open the main page
        MainPage mainPage = new MainPage(this.driver);
        // System.out.println(mainPage.getBodyText());

        // Test successful login
        LoginPage loginPage = mainPage.goToLoginPage();
        HomePage homePage = loginPage.login("paulasgeorge@gmail.com", "8itDdks2!%!Yb8f");
        assertTrue(homePage.getHomePageTextAfterLogin().contains("My Programs"));

        // Test successful logout
         MainPage loggedOutPage = homePage.logout();
        assertTrue(loggedOutPage.getBodyText().contains("Log In"));

        // Test unsuccessful login
        LoginPage loginPageAgain = mainPage.goToLoginPage();
        LoginPage invalidBasePage = loginPageAgain.loginWithInvalidCredentials("invalid@email.com","invalid!");
        assertTrue(invalidBasePage.getLoginErrorMessage().contains("The email or password you entered is invalid"));

    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
