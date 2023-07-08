package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.testng.Assert;
import pages.BasePage;
import pages.HomePage;
import pages.PostModal;

public class GuestTests {

    protected WebDriver driver;
    protected final String URL = "http://training.skillo-bg.com/posts/all";
    public static final String RESOURCES_DIR = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    public static final String SCREENSHOT_DIR = RESOURCES_DIR.concat("screenshots" + File.separator);

    @BeforeMethod
    public void setupDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void clickUser() {
        System.out.println("1. Navigate to main page as a guest user.");
        driver.get(URL);

        System.out.println("2. Click on the first user's nickname to access his profile.");
        HomePage homePage = new HomePage(driver);
        homePage.clickUsername();

        System.out.println("3. Confirm that you are redirected to a 404 Page Not Found");
        BasePage basePage = new BasePage(driver);
        basePage.verifyURL("http://training.skillo-bg.com/not-found");
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://training.skillo-bg.com/not-found";
        Assert.assertEquals(currentURL, expectedURL, "As a guest, you should be redirected to a 404 page when trying to access other users.");

    }

    @Test
    public void likePost() {
        System.out.println("1. Navigate to main page as a guest user.");
        driver.get(URL);

        System.out.println("2. Click on the first user's post to open his post.");
        HomePage homePage = new HomePage(driver);
        homePage.clickPost();

        System.out.println("3. Wait for the post modal to appear");
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogAppear();

        System.out.println("4. Like the post");
        postModal.likePost();

        System.out.println("5. Confirm that you are redirected to the login page");
        BasePage basePage = new BasePage(driver);
        basePage.verifyURL("http://training.skillo-bg.com/users/login");
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://training.skillo-bg.com/users/login";
        Assert.assertEquals(currentURL, expectedURL, "As a guest, you should be redirected to the Login Page when trying to like a post.");
    }

    @Test
    public void dislikePost() {
        System.out.println("1. Navigate to main page as a guest user.");
        driver.get(URL);

        System.out.println("2. Click on the first user's post to open his post.");
        HomePage homePage = new HomePage(driver);
        homePage.clickPost();

        System.out.println("3. Wait for the post modal to appear");
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogAppear();

        System.out.println("4. Dislike the post");
        postModal.dislikePost();

        System.out.println("5. Confirm that you are redirected to the login page");
        BasePage basePage = new BasePage(driver);
        basePage.verifyURL("http://training.skillo-bg.com/users/login");
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "http://training.skillo-bg.com/users/login";
        Assert.assertEquals(currentURL, expectedURL, "As a guest, you should be redirected to the Login Page when trying to dislike a post.");
    }

    @AfterMethod
    public void cleanup(ITestResult testResult) {
        takeScreenShot(testResult);
        driver.close();
    }

    private void takeScreenShot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();
            try {
                FileUtils.copyFile(screenshot, new File(SCREENSHOT_DIR.concat(testName).concat(".jpg")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
