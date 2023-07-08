package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends BasePage {

    public final String loginURL = "http://training.skillo-bg.com:4200/users/login";

    @FindBy(name = "usernameOrEmail")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(name= "rememberMe")
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
    public String getSignInHeaderText() {
        return returnText(signInHeader);
    }
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }

}
