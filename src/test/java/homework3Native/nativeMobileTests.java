package homework3Native;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;
import setup.CloudNativeTest;

public class nativeMobileTests extends CloudNativeTest {

    @Parameters({"username", "email", "password"})
    @Test(groups = {"native"}, description = "After registering as a new user and signing in"
        + " user gets to Budget Activity page")
    public void registerAndSignInShouldLeadToBudgetActivityPage(String username, String email, String password)
        throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        System.out.println("Registering user");
        getPo().getWelement("registerButton").click();
        getPo().getWelement("registrationEmailField").sendKeys(email);
        getPo().getWelement("registrationUsernameField").sendKeys(username);
        getPo().getWelement("registrationPassword").sendKeys(password);
        getPo().getWelement("registrationConfirmPassword").sendKeys(password);
        getDriver().hideKeyboard(); // screen keyboard blocks the next action on some models
        getPo().getWelement("registerNewAccountButton").click();

        System.out.println("Logging in");
        getPo().getWelement("loginField").sendKeys(email);
        getPo().getWelement("passwordField").sendKeys(password);
        getPo().getWelement("signInButton").click();

        WebElement expensesList = getPo().getWelement("expensesList");
        assertThat(expensesList)
            .as("There should be an 'Expenses list' element on the page")
            .isNotNull();
    }
}
