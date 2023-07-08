package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage{

    @FindBy(css = ".profile-edit-btn")
    WebElement followButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void followUser(){
        clickElement(followButton);
    }
}
