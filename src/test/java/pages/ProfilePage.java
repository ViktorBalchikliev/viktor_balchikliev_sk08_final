package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProfilePage extends BasePage{

    @FindBy(css = ".profile-user-settings > h2")
    WebElement usernameHeader;

    @FindBy(css = ".btn-all")
    WebElement allPostsButton;

    @FindBy(css="app-post")
    List<WebElement> allPosts;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public String getUsernameHeaderText() {
        return returnText(usernameHeader);
    }
    public int getExistingPostCount(){
        clickElement(allPostsButton);
        wait.until(ExpectedConditions.visibilityOf((WebElement) allPosts));
        return allPosts.size();
    }
}
