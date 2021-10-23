package setup;

import static java.lang.String.format;

import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pageObjects.nativeApp.BudgetActivityPageObject;
import pageObjects.nativeApp.LoginPageObject;
import pageObjects.nativeApp.RegistrationPageObject;

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
    public void setUp(String deviceName, String platformName, String app) {
        System.out.println("Before suite: native app, platform is " + platformName);

        setAppiumDriver(deviceName, platformName, app);
        System.out.println("Appium Driver: " + appiumDriver.toString());

        loginPo = new LoginPageObject(getDriver());
        registrationPo = new RegistrationPageObject(getDriver());
        budgetActivityPo = new BudgetActivityPageObject(getDriver());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("After suite: native app");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String deviceName, String platformName, String app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("app", new File(app).getAbsolutePath());

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
