import static org.junit.Assert.assertTrue;
import java.net.MalformedURLException;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Ignore;
// import org.junit.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    @BeforeClass
    public void setup() throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize the browser window on startup
        options.addArguments("--disable-extensions"); // Disable browser extensions
        options.addArguments("--disable-popup-blocking"); // Disable popup blocking
        options.addArguments("--disable-infobars"); // Disable the info bar
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testNavigateToLoginPage() {
        // Open the main page
        mainPage = new MainPage(this.driver);

        // Test if the page title contains "Udacity"
        LoginPage loginPage = mainPage.goToLoginPage();
        assertTrue(loginPage.getPageTitle().contains("Udacity"));
    }

    @Test(dependsOnMethods = "testNavigateToLoginPage")
    public void testSuccessfulLogin() {
        // Open the main page
        MainPage mainPage = new MainPage(this.driver);

        LoginPage loginPage = mainPage.goToLoginPage();

        // Test successful login
        HomePage homePage = loginPage.login("paulasgeorge@gmail.com", "8itDdks2!%!Yb8f");
        assertTrue(homePage.getHomePageTextAfterLogin().contains("My Programs"));
    }

    @Test(dependsOnMethods = "testSuccessfulLogin")
    public void testHomePage() {
        // Open the main page
        MainPage mainPage = new MainPage(this.driver);

        LoginPage loginPage = mainPage.goToLoginPage();
        HomePage homePage = loginPage.login("paulasgeorge@gmail.com", "8itDdks2!%!Yb8f");

        homePage.displaySettingsMenu();
        assertTrue(homePage.getHomePageTextAfterLogin().contains("My Programs"));
    }

    @Test(dependsOnMethods = "testHomePage")
    public void testCoursePage() {
        // Open the main page
        MainPage mainPage = new MainPage(this.driver);

        LoginPage loginPage = mainPage.goToLoginPage();
        HomePage homePage = loginPage.login("paulasgeorge@gmail.com", "8itDdks2!%!Yb8f");
        CoursePage coursePage = homePage.goToCoursePage();
        coursePage.navigateBack();
        assertTrue(homePage.getHomePageTextAfterLogin().contains("My Programs"));
    }

    @Test(dependsOnMethods = "testHomePage")
    public void testLogout() {
        // Open the main page
        MainPage mainPage = new MainPage(this.driver);

        LoginPage loginPage = mainPage.goToLoginPage();
        HomePage homePage = loginPage.login("paulasgeorge@gmail.com", "8itDdks2!%!Yb8f");
        MainPage loggedOutPage = homePage.logout();
        assertTrue(loggedOutPage.getBodyText().contains("Log In"));
    }

    @Test(dependsOnMethods = "testLogout")
    public void testInvalidLogin() {
        // Open the main page
        MainPage mainPage = new MainPage(this.driver);

        LoginPage loginPageAgain = mainPage.goToLoginPage();
        LoginPage invalidBasePage = loginPageAgain.loginWithInvalidCredentials("invalid@email.com", "invalid!");
        assertTrue(invalidBasePage.getLoginErrorMessage().contains("The email or password you entered is invalid"));
    }

    @Test(dependsOnMethods = "testInvalidLogin")
    public void testMultiplePages() {
        // Multiple static pages tests
        List<String> urls = Arrays.asList(
                "https://www.udacity.com/catalog",
                "https://www.udacity.com/government/overview",
                "https://www.udacity.com/resource-center",
                "https://www.udacity.com/enterprise/plans");

        for (String url : urls) {
            driver.get(url);
            // assertTrue("Page is not accessible: " + url, driver.getTitle().contains("Udacity"));
            String bodyText = driver.findElement(By.tagName("body")).getText();
            assertTrue("Page " + url + " contains '404 Not Found'",
                    !bodyText.contains("404 Not Found"));
        }
    }

    
 
    // @Test
    // public void testSelenium() {
    //     // Open the main page
    //     MainPage mainPage = new MainPage(this.driver);

    //     // Test if the page title contains "Udacity"
    //     LoginPage loginPage = mainPage.goToLoginPage();
    //     assertTrue(loginPage.getPageTitle().contains("Udacity"));

    //     // Test successful login
    //     HomePage homePage = loginPage.login("paulasgeorge@gmail.com", "8itDdks2!%!Yb8f");
    //     assertTrue(homePage.getHomePageTextAfterLogin().contains("My Programs"));

    //     homePage.displaySettingsMenu();

    //     // Open course page and navigate back to home page
    //     CoursePage coursePage = homePage.goToCoursePage();
    //     coursePage.navigateBack();
    //     assertTrue(homePage.getHomePageTextAfterLogin().contains("My Programs"));

    //     // Test successful logout
    //     MainPage loggedOutPage = homePage.logout();
    //     assertTrue(loggedOutPage.getBodyText().contains("Log In"));

    //     // Test unsuccessful login
    //     LoginPage loginPageAgain = mainPage.goToLoginPage();
    //     LoginPage invalidBasePage = loginPageAgain.loginWithInvalidCredentials("invalid@email.com", "invalid!");
    //     assertTrue(invalidBasePage.getLoginErrorMessage().contains("The email or password you entered is invalid"));

    //     // Multiple static pages tests
    //     List<String> urls = Arrays.asList(
    //             "https://www.udacity.com/catalog",
    //             "https://www.udacity.com/government/overview",
    //             "https://www.udacity.com/resource-center",
    //             "https://www.udacity.com/enterprise/plans");

    //     for (String url : urls) {
    //          driver.get(url);
    //         // assertTrue("Page is not accessible: " + url, driver.getTitle().contains("Udacity"));
    //         String bodyText = driver.findElement(By.tagName("body")).getText();
    //         assertTrue("Page " + url + " contains '404 Not Found'",
    //                 !bodyText.contains("404 Not Found"));

    //     }

    // }

    @AfterClass
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
