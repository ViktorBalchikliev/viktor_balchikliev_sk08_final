package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(name = "usernameOrEmail")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(css= ".remember-me-button")
    WebElement rememberButton;

    @FindBy(id = "sign-in-button")
    WebElement signInBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        inputText(usernameField, username);
    }
    public void enterPassword(String password) {
        inputText(passwordField, password);
    }

    public void clickRememberMe(){
        clickElement(rememberButton);
    }
    public void clickSignIn() {
        clickElement(signInBtn);
    }
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        wait.until(ExpectedConditions.elementToBeClickable(rememberButton));
        clickRememberMe();
        clickSignIn();
    }
}
