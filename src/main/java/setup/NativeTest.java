package setup;

import static java.lang.String.format;

import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.BudgetActivityPageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegistrationPageObject;
import utils.PropertyReader;

public class NativeTest {
    protected static AppiumDriver appiumDriver;
    protected LoginPageObject loginPo;
    protected RegistrationPageObject registrationPo;
    protected BudgetActivityPageObject budgetActivityPo;

    public AppiumDriver getDriver() {return appiumDriver; }
    public LoginPageObject getLoginPo() { return loginPo; }
    public RegistrationPageObject getRegistrationPo() { return registrationPo; }
    public BudgetActivityPageObject getBudgetActivityPo() { return budgetActivityPo; }

    @Parameters({"deviceName", "platformName", "app"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String deviceName,
                      String platformName,
                      String app) throws Exception {
        System.out.println("Before suite: platform is " + platformName);

        setAppiumDriver(deviceName, platformName, app);
        System.out.println("Appium Driver: " + appiumDriver.toString());

        loginPo = new LoginPageObject(getDriver());
        registrationPo = new RegistrationPageObject(getDriver());
        budgetActivityPo = new BudgetActivityPageObject(getDriver());

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After suite");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String deviceName,
                                 String platformName,
                                 String app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("app", app);

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts()
                    .implicitlyWait(23, TimeUnit.SECONDS);
    }
}
