package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    private final String URL = "http://training.skillo-bg.com:4200/posts/all";

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".post-user")
    WebElement userName;

    @FindBy(css = ".post-feed-img")
    WebElement userPost;

    public void getURL() {
        driver.get(URL);
    }
    public void clickUsername() {
        clickElement(userName);
    }
    public void clickPost() {
        clickElement(userPost);
    }
}
