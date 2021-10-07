package setup;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

import io.appium.java_client.AppiumDriver;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageObject;
import utils.PropertyReader;

public class CloudWebTest implements IDriver {
    protected static AppiumDriver appiumDriver;
    protected static String API_KEY;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    @Parameters({"platformName", "browserName"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String browserName) throws Exception {
        System.out.println("Before");

        Properties props = new PropertyReader().readPropertiesFromFile("test.properties");
        // Eliminate error caused by '/' symbols in API key
        API_KEY = URLEncoder.encode(props.getProperty("apiKey"), StandardCharsets.UTF_8.name());

        // Device UDID parameter has high variability, so it gets passed as Maven command argument
        String udid = System.getProperty("udid");

        setAppiumDriver(platformName, udid, browserName);
        System.out.println("Appium Driver: " + appiumDriver.toString());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName,
                                 String udid,
                                 String browserName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("udid", udid);
        // deviceName commented out since it's currently possible to address Android devices by "UDID" (serial number)
        //capabilities.setCapability("deviceName",deviceName);

        capabilities.setCapability("browserName", browserName);
        // avoid Chrome driver version errors
        capabilities.setCapability("chromedriverDisableBuildCheck","true");

        try {
            appiumDriver = new AppiumDriver(
                new URL(format("https://EPM-TSTF:%s@mobilecloud.epam.com/wd/hub", API_KEY)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts()
                    .pageLoadTimeout(20, TimeUnit.MINUTES)
                    .implicitlyWait(60, TimeUnit.SECONDS);
    }
}
