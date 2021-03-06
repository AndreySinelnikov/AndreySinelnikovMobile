package pageObjects.nativeApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import pageObjects.AbstractPageObject;

public class LoginPageObject extends AbstractPageObject {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='user@example.com']")
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    private WebElement loginField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value='Required']")
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    private WebElement passwordField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Sign In']")
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    private WebElement signInButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Register new account']")
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    private WebElement registerButton;

    public LoginPageObject(AppiumDriver appiumDriver) { super(appiumDriver); }

    public RegistrationPageObject openRegistrationPage() {
        System.out.println("Opening registration page");
        waitUntilElementIsClickable(registerButton);
        registerButton.click();

        return new RegistrationPageObject(appiumDriver);
    }

    public BudgetActivityPageObject signIn(String email, String password) {
        System.out.println("Signing in");
        waitUntilElementIsVisible(loginField);
        loginField.sendKeys(email);
        waitUntilElementIsVisible(passwordField);
        passwordField.sendKeys(password);
        waitUntilElementIsClickable(signInButton);
        signInButton.click();

        return new BudgetActivityPageObject(appiumDriver);
    }
}
