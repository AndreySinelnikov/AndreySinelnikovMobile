package homework;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

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
        getPo().getWelement("registerNewAccountButton").click();

        System.out.println("Logging in");
        getPo().getWelement("loginField").sendKeys(email);
        getPo().getWelement("passwordField").sendKeys(password);
        getPo().getWelement("signInButton").click();

        // finds all elements with text 'BudgetActivity' on the page
        List<WebElement> budgetActivityElems = getDriver().findElements(By.xpath("//*[@text = 'BudgetActivity']"));
        assertThat(budgetActivityElems.size())
            .as("There should be an element with text 'BudgetActivity' on the page")
            .isEqualTo(1);
    }
}
