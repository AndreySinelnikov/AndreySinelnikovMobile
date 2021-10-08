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

public class CloudNativeTest {
    protected static AppiumDriver appiumDriver;
    protected static String API_KEY;
    protected LoginPageObject loginPo;
    protected RegistrationPageObject registrationPo;
    protected BudgetActivityPageObject budgetActivityPo;

    public AppiumDriver getDriver() {return appiumDriver; }
    public LoginPageObject getLoginPo() { return loginPo; }
    public RegistrationPageObject getRegistrationPo() { return registrationPo; }
    public BudgetActivityPageObject getBudgetActivityPo() { return budgetActivityPo; }

    @Parameters({"platformName", "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        System.out.println("Before suite: platform is " + platformName);

        Properties props = new PropertyReader().readPropertiesFromFile("test.properties");
        // Eliminate error caused by '/' symbols in API key
        API_KEY = URLEncoder.encode(props.getProperty("apiKey"), StandardCharsets.UTF_8.name());

        // Device UDID parameter has high variability, so it gets passed as Maven command argument
        String udid = System.getProperty("udid");

        setAppiumDriver(platformName, udid, appPackage, appActivity, bundleId);
        System.out.println("Appium Driver: " + appiumDriver.toString());

        loginPo = new LoginPageObject(getDriver());
        // uncomment the following lines if you want to rewrite tests without chaining fluent page objects
        //registrationPo = new RegistrationPageObject(getDriver());
        //budgetActivityPo = new BudgetActivityPageObject(getDriver());

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After suite");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName,
                                 String udid,
                                 String appPackage,
                                 String appActivity,
                                 String bundleId
                                 ) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("udid", udid);
        // deviceName commented out since it's currently possible to address Android devices by "UDID" (serial number)
        //capabilities.setCapability("deviceName",deviceName);

        capabilities.setCapability("appPackage", appPackage);
        // avoid Chrome driver version errors
        capabilities.setCapability("appActivity",appActivity);

        capabilities.setCapability("bundleId", bundleId);

        try {
            appiumDriver = new AppiumDriver(
                new URL(format("https://EPM-TSTF:%s@mobilecloud.epam.com/wd/hub", API_KEY)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts()
                    .implicitlyWait(30, TimeUnit.SECONDS);
    }
}
