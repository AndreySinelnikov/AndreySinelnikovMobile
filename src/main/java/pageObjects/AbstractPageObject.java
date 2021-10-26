package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPageObject {
    protected final static int DEFAULT_WAIT_TIMEOUT = 23; // in seconds

    protected final AppiumDriver appiumDriver;
    protected final WebDriverWait wait;

    // constructor to be utilized in child classes
    protected AbstractPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
        this.appiumDriver = appiumDriver;
        wait = new WebDriverWait(this.appiumDriver, DEFAULT_WAIT_TIMEOUT);
    }

    public void waitUntilElementIsVisible(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitUntilElementIsClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitUntilDocumentFullyLoads() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}
