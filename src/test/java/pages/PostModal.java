package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostModal extends BasePage{

    @FindBy(tagName = "app-post-modal")
    WebElement modalDialog;

    @FindBy(css = ".like")
    WebElement likeButton;

    @FindBy(css = ".ml-4")
    WebElement dislikeButton;

    public PostModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void waitForDialogAppear(){
        wait.until(ExpectedConditions.visibilityOf(modalDialog));
    }
    public void likePost(){
        clickElement(likeButton);
    }

    public void dislikePost(){
        clickElement(dislikeButton);
    }
}