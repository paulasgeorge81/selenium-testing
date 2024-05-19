import static org.junit.Assert.assertTrue;
import java.net.MalformedURLException;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Ignore;
// import org.junit.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class SeleniumTest {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private ConfigReader configReader;
    private String baseUrl;

    @BeforeClass
    public void setup() throws MalformedURLException {

        configReader = new ConfigReader();
        baseUrl = configReader.getProperty("baseUrl");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize the browser window on startup
        options.addArguments("--disable-extensions"); // Disable browser extensions
        options.addArguments("--disable-popup-blocking"); // Disable popup blocking
        options.addArguments("--disable-infobars"); // Disable the info bar
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @BeforeMethod
    public void initializePages() {
        mainPage = new MainPage(this.driver);
    }

    @Test
    public void testNavigateToLoginPage() {
        // Test if the page title contains "Udacity"
        loginPage = mainPage.goToLoginPage();
        assertTrue(loginPage.getPageTitle().contains("Udacity"));
    }

    @Test(dependsOnMethods = "testNavigateToLoginPage")
    public void testInvalidLogin() {
        loginPage = mainPage.goToLoginPage();
        LoginPage invalidBasePage = loginPage.loginWithInvalidCredentials(configReader.getProperty("invalidUsername"), configReader.getProperty("invalidPassword"));
        assertTrue(invalidBasePage.getLoginErrorMessage().contains("The email or password you entered is invalid"));
    }


  
    @Test(dependsOnMethods = "testInvalidLogin")
    public void testHomeNavigationAndLogout() {

        // Test if the page title contains "Udacity"
        loginPage = mainPage.goToLoginPage();
        assertTrue(loginPage.getPageTitle().contains("Udacity"));

        // Test successful login
        homePage = loginPage.login(configReader.getProperty("validUsername"), configReader.getProperty("validPassword"));
        assertTrue(homePage.getHomePageTextAfterLogin().contains("My Programs"));

        homePage.displaySettingsMenu();


        // Open course page and navigate back to home page
        CoursePage coursePage = homePage.goToCoursePage();
        coursePage.navigateBack();
        assertTrue(homePage.getHomePageTextAfterLogin().contains("My Programs"));

        // Test successful logout
        MainPage loggedOutPage = homePage.logout();
        assertTrue(loggedOutPage.getBodyText().contains("Log In"));

    }

    @Test(dependsOnMethods = "testHomeNavigationAndLogout")
    public void testMultipleStaticPages() {
        // Multiple static pages tests
        List<String> urls = Arrays.asList(
                baseUrl+"/catalog",
                baseUrl+"/government/overview",
                baseUrl+"/resource-center",
                baseUrl+"/enterprise/plans");

        for (String url : urls) {
            driver.get(url);
            String bodyText = driver.findElement(By.tagName("body")).getText();
            assertTrue("Page " + url + " contains '404 Not Found'",
                    !bodyText.contains("404 Not Found"));
        }
    }

    @AfterClass
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
