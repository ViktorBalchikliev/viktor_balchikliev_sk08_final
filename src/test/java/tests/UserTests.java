package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UserTests {

    ///Pre-registered user already exists with the following data:
    // username: tester_viktor
    ///password: test1234

    protected WebDriver driver;
    protected final String URL = "http://training.skillo-bg.com/posts/all";
    public static final String RESOURCES_DIR = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    public static final String SCREENSHOT_DIR = RESOURCES_DIR.concat("screenshots" + File.separator);
    public static final String REPORT_DIR = "target" + File.separator + "surefire-reports" + File.separator;

    protected String username = "tester_viktor";
    protected String password = "test1234";

    @BeforeMethod
    public void setupDriver() throws IOException {
        WebDriverManager.chromedriver().setup();
        cleanDirectory(SCREENSHOT_DIR);
        cleanDirectory(REPORT_DIR);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        System.out.println("Navigate to the login page and login with valid user data");

        driver.get(URL);

        Header header = new Header(driver);
        header.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Test
    public void commentPost() {

        System.out.println("1. Click on the first user's post to open his post.");
        HomePage homePage = new HomePage(driver);
        homePage.clickPost();

        System.out.println("2. Wait for the post modal to appear.");
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogAppear();

        System.out.println("3. Comment on the post.");
        postModal.commentPost();

        System.out.println("4. Confirm that the comment is displayed");
        WebElement newComment = postModal.getComment();
        Assert.assertTrue(newComment.isDisplayed(), "The comment is not displayed");
    }
    @Test
    public void likePostPopup() {

        System.out.println("1. Click on the first user's post to open his post.");
        HomePage homePage = new HomePage(driver);
        homePage.clickPost();

        System.out.println("2. Wait for the post modal to appear.");
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogAppear();

        System.out.println("3. Like the post");
        postModal.likePost();

        System.out.println("4. Check if the pop-up confirmation has appeared");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By popupLocator = By.id("toast-container");
        WebElement popupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
        Assert.assertTrue(popupElement.isDisplayed(), "The pop-up confirmation does not appear.");
    }

    @Test
    public void followUser() {

        System.out.println("1. Click on the first user's nickname to access his profile.");
        HomePage homePage = new HomePage(driver);
        homePage.clickUsername();

        System.out.println("2. Follow the user.");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.followUser();

        System.out.println("3. Confirm the unfollow button is present.");
        WebElement unfollowButton = driver.findElement(By.xpath(".//button[contains(text(), 'Unfollow')]"));
        Assert.assertTrue(unfollowButton.isDisplayed());
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

    private void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All files are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory:%s%n", directoryPath);
        }
    }
}
