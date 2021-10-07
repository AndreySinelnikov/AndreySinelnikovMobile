package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.lang.reflect.Field;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import setup.IPageObject;

public class NativeHomeworkPageObject implements IPageObject {

    // login page
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    WebElement loginField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    WebElement passwordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    WebElement signInButton;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    WebElement registerButton;

    // register page
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    WebElement registrationEmailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    WebElement registrationUsernameField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    WebElement registrationPassword;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    WebElement registrationConfirmPassword;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    WebElement registerNewAccountButton;

    // budget page
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/expenses_list")
    WebElement expensesList;


    public NativeHomeworkPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }

    @Override
    public WebElement getWelement(String weName) throws NoSuchFieldException, IllegalAccessException {
        // use reflection technique
        Field field = this.getClass().getDeclaredField(weName);
        field.setAccessible(true);
        return (WebElement) field.get(this);

    }
}
