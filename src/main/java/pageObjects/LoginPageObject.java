package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageObject extends BasePageObject {

    // login page
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


    public LoginPageObject(AppiumDriver driver) {
        PageFactory.initElements( new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 23);
    }

    public RegistrationPageObject openRegistrationPage() {
        System.out.println("Opening registration page");
        waitUntilElementIsClickable(registerButton);
        registerButton.click();

        return new RegistrationPageObject(driver);
    }

    public BudgetActivityPageObject signIn(String email, String password) {
        System.out.println("Signing in");
        waitUntilElementIsVisible(loginField);
        loginField.sendKeys(email);
        waitUntilElementIsVisible(passwordField);
        passwordField.sendKeys(password);
        waitUntilElementIsClickable(signInButton);
        signInButton.click();

        return new BudgetActivityPageObject(driver);
    }
}
