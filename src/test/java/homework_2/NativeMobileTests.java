package homework_2;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.NativeTest;

public class NativeMobileTests extends NativeTest {

    @Parameters({"email", "username", "password"})
    @Test(description = "After registering as a new user and signing in"
        + " user gets to Budget Activity page with an expenses list")
    public void registerAndSignInShouldLeadToBudgetActivityPage(String email, String username, String password) {
        System.out.println("Registering user");
        getLoginPo().openRegistrationPage();
        getRegistrationPo().registerUser(email, username, password);

        System.out.println("Logging in");
        getLoginPo().signIn(email, password);

        getBudgetActivityPo().assertExpensesListExists();
    }
}
