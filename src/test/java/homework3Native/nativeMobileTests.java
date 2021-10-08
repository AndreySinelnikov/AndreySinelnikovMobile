package homework3Native;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.CloudNativeTest;

public class nativeMobileTests extends CloudNativeTest {

    @Parameters({"email", "username", "password"})
    @Test(groups = {"native"}, description = "After registering as a new user and signing in"
        + " user gets to Budget Activity page")
    public void registerAndSignInShouldLeadToBudgetActivityPage(String email, String username, String password)
        throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getLoginPo()
            .openRegistrationPage()
            .registerUser(email, username, password)
            .signIn(email, password)
            .assertExpensesListExists();
    }
}
