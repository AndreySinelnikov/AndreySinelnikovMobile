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
import org.testng.annotations.Parameters;
import pageObjects.NativeHomeworkPageObject;
import pageObjects.PageObject;
import utils.PropertyReader;

public class CloudNativeTest implements IDriver {
    protected static AppiumDriver appiumDriver;
    protected static String API_KEY;
    IPageObject po;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {return po; }

    @Parameters({"platformName", "appPackage", "appActivity"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appPackage, String appActivity) throws Exception {
        System.out.println("Before");

        Properties props = new PropertyReader().readPropertiesFromFile("test.properties");
        // Eliminate error caused by '/' symbols in API key
        API_KEY = URLEncoder.encode(props.getProperty("apiKey"), StandardCharsets.UTF_8.name());

        // Device UDID parameter has high variability, so it gets passed as Maven command argument
        String udid = System.getProperty("udid");

        setAppiumDriver(platformName, udid, appPackage, appActivity);
        System.out.println("Appium Driver: " + appiumDriver.toString());

        po = new NativeHomeworkPageObject(getDriver());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName,
                                 String udid,
                                 String appPackage,
                                 String appActivity
                                 ) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("udid", udid);
        // deviceName commented out since it's currently possible to address Android devices by "UDID" (serial number)
        //capabilities.setCapability("deviceName",deviceName);

        capabilities.setCapability("appPackage", appPackage);
        // avoid Chrome driver version errors
        capabilities.setCapability("appActivity",appActivity);

        try {
            appiumDriver = new AppiumDriver(
                new URL(format("https://EPM-TSTF:%s@mobilecloud.epam.com/wd/hub", API_KEY)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts()
                    .implicitlyWait(60, TimeUnit.SECONDS);
    }
}
