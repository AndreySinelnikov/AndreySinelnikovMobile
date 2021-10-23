package homework_2;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.NativeTest;

public class NativeMobileTests extends NativeTest {

//    @Parameters({"username", "email", "password"})
//    @Test(groups = {"native"}, description = "After registering as a new user and signing in"
//        + " user gets to Budget Activity page", enabled = false)
//    public void registerAndSignInShouldLeadToBudgetActivityPage(String username, String email, String password)
//        throws IllegalAccessException, NoSuchFieldException, InstantiationException {
//        System.out.println("Registering user");
//        getPo().getWebElement("registerButton").click();
//        getPo().getWebElement("registrationEmailField").sendKeys(email);
//        getPo().getWebElement("registrationUsernameField").sendKeys(username);
//        getPo().getWebElement("registrationPassword").sendKeys(password);
//        getPo().getWebElement("registrationConfirmPassword").sendKeys(password);
//        getDriver().hideKeyboard(); // screen keyboard blocks the next action on some models
//        getPo().getWebElement("registerNewAccountButton").click();
//
//        System.out.println("Logging in");
//        getPo().getWebElement("loginField").sendKeys(email);
//        getPo().getWebElement("passwordField").sendKeys(password);
//        getPo().getWebElement("signInButton").click();
//
//        WebElement expensesList = getPo().getWebElement("expensesList");
//        assertThat(expensesList)
//            .as("There should be an 'Expenses list' element on the page")
//            .isNotNull();
//    }

    @Parameters({"username", "email", "password"})
    @Test(description = "After registering as a new user and signing in"
        + " user gets to Budget Activity page ZA SECONDO")
    public void registerAndSignInShouldLeadToBudgetActivityPage2(String username, String email, String password) {
        System.out.println("Registering user");
        getLoginPo().openRegistrationPage();
        getRegistrationPo().registerUser(username, email, password);

        System.out.println("Logging in");
        getLoginPo().signIn(email, password);

        getBudgetActivityPo().assertExpensesListExists();
    }
}
