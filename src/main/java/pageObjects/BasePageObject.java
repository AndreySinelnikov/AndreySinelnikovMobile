package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.util.function.Function;
import java.util.function.Predicate;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageObject {
    protected static int DEFAULT_WAIT_TIMEOUT = 23; // in seconds

    protected AppiumDriver appiumDriver;
    protected WebDriverWait wait;

    // to be utilized in child classes
    protected BasePageObject(AppiumDriver appiumDriver) {
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
