package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Header;
import pages.HomePage;
import pages.LoginPage;

import java.io.File;
import java.time.Duration;

public class ProfileTests {

    ///Pre-registered user already exists with the following data:
    // username: user_viktor
    ///password: pass_viktor

    /// post likes - click modal and count?
    /// Test 5 - check number of posts against text message: 2 vs 1
    protected WebDriver driver;
    protected final String URL = "http://training.skillo-bg.com/posts/all";
    public static final String RESOURCES_DIR = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    public static final String SCREENSHOT_DIR = RESOURCES_DIR.concat("screenshots" + File.separator);

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{{"user_viktor", "pass_viktor"}};
    }
    @BeforeMethod
    public void setupDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test (dataProvider = "loginData")
    public void profilePage(String username, String password) {
        System.out.println("1. Navigate to the login page");
        driver.get(URL);

        System.out.println("2. Login with the user data provided");
        Header header = new Header(driver);
        header.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }
}
