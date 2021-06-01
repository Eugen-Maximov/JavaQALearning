package lib.UI;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
            LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
            LOGIN_INPUT = "ccs:input[name='wpName']",
            PASSWORD_INPUT = "ccs:input[name='wpPassword']",
            SUBMIT_BUTTON = "ccs:button#wpLoginAttemtp";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Start auth session")
    public void clickAuthButton()
    {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find Login button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click Login button", 5);
    }

    @Step("Enter login data: \n login: '{login}'; \n password: '{password}';")
    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and a login to a login input field", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and a pass to a password input field", 5);
    }

    @Step("Submit auth")
    public void submitForm(){
        this.waitForElementPresent(SUBMIT_BUTTON, "Cannot find submit button", 5);
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
    }
}
