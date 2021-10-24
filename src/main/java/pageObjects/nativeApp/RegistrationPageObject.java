package pageObjects.nativeApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import pageObjects.AbstractPageObject;

public class RegistrationPageObject extends AbstractPageObject {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='user@example.com']")
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    private WebElement emailField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='TimApple']")
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    private WebElement usernameField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value='Required']")
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    private WebElement passwordField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@value='Repeat please']")
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    private WebElement confirmPasswordField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Register new account']")
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    private WebElement registerNewAccountButton;

    public RegistrationPageObject(AppiumDriver appiumDriver) { super(appiumDriver); }

    public LoginPageObject registerUser(String email, String username, String password) {
        System.out.println("Registering user");
        waitUntilElementIsVisible(emailField);
        emailField.sendKeys(email);
        waitUntilElementIsVisible(usernameField);
        usernameField.sendKeys(username);
        waitUntilElementIsVisible(passwordField);
        passwordField.sendKeys(password);
        waitUntilElementIsVisible(confirmPasswordField);
        confirmPasswordField.sendKeys(password);

        appiumDriver.hideKeyboard(); // screen keyboard might obscure 'Register new account' button on some models

        waitUntilElementIsClickable(registerNewAccountButton);
        registerNewAccountButton.click();

        return new LoginPageObject(appiumDriver);
    }
}
