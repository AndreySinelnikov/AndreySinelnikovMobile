package pageObjects.webApp;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.AbstractPageObject;

public class GoogleSearchResultPageObject extends AbstractPageObject {

    @FindBy(css = "div h3")
    private List<WebElement> linkHeaders;

    public GoogleSearchResultPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public long getNumberOfLinkHeadersContainingSearchItem(String searchItem) {
        return linkHeaders.stream()
                   .map(WebElement::getText)
                   .map(text -> text.contains(searchItem))
                   .count();
    }
}
