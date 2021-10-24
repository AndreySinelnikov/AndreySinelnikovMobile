package setup;

import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pageObjects.webApp.GoogleSearchPageObject;
import pageObjects.webApp.GoogleSearchResultPageObject;

public class WebTest {
    protected static AppiumDriver appiumDriver;
    protected GoogleSearchPageObject searchPo;
    protected GoogleSearchResultPageObject searchResultPo;

    public AppiumDriver getDriver() {
        return appiumDriver;
    }
    public GoogleSearchPageObject getSearchPo() { return searchPo; }
    public GoogleSearchResultPageObject getSearchResultPo() { return searchResultPo; }

    @Parameters({"deviceName", "platformName", "browserName", "googleSearchPage"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String deviceName, String platformName, String browserName, String googleSearchPage) {
        System.out.println("Before suite: web app tests");

        setAppiumDriver(deviceName, platformName, browserName);
        System.out.println("Appium Driver: " + appiumDriver.toString());

        searchPo = new GoogleSearchPageObject(appiumDriver,googleSearchPage);
        searchResultPo = new GoogleSearchResultPageObject(appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("After suite: web app tests");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String deviceName,
                                 String platformName,
                                 String browserName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName",deviceName);
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("browserName", browserName);
        // avoid Chrome driver version errors
        capabilities.setCapability("chromedriverDisableBuildCheck","true");

        try {
            appiumDriver = new AppiumDriver(
                new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts()
                    .pageLoadTimeout(1, TimeUnit.MINUTES)
                    .implicitlyWait(30, TimeUnit.SECONDS);
    }
}
