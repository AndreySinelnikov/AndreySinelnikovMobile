package homework2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.examples.BaseTest;

public class webMobileTests extends BaseTest {

    @Parameters({"searchItem"})
    @Test(groups = {"web"}, description = "Searching item via Google search page produces some relevant results")
    public void googleSearchShouldProduceSomeRelevantResults(String searchItem) {
        getDriver().get("https://www.google.com/");

        new WebDriverWait(getDriver(), 10).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        WebElement searchForm = getDriver().findElement(By.cssSelector("input[aria-autocomplete]"));
        searchForm.sendKeys(searchItem);
        searchForm.sendKeys(Keys.ENTER);

        new WebDriverWait(getDriver(), 10).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        List<WebElement> linkHeaders = getDriver().findElements(By.cssSelector("div h3"));
        // count occurrences of the item being searched in link header texts
        long mentionNumber = linkHeaders.stream()
                                        .map(WebElement::getText)
                                        .map(text -> text.contains(searchItem))
                                        .count();

        assertThat(mentionNumber)
            .isGreaterThan(1L);  // 'some' from task description is interpreted as more than 1
    }
}
