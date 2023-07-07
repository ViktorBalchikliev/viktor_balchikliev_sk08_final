package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends BasePage {

    @FindBy(id = "nav-link-home")
    WebElement homeButton;

    @FindBy(id = "nav-link-login")
    WebElement loginButton;

    @FindBy(id = "nav-link-profile")
    WebElement profileButton;

    @FindBy(id = "nav-link-new-post")
    WebElement newPostButton;

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void clickLogin() {
        clickElement(loginButton);
    }
    public void clickHome() {
        clickElement(homeButton);
    }
    public void clickProfile() {
        clickElement(profileButton);
    }
    public void clickNewPost() {
        clickElement(newPostButton);
    }
}
