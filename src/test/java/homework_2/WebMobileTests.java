package homework_2;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.WebTest;

public class WebMobileTests extends WebTest {

    @Parameters({"searchItem"})
    @Test(description = "Searching item via Google search page produces some relevant results")
    public void googleSearchShouldProduceSomeRelevantResults(String searchItem) {
        getSearchPo().open();
        getSearchPo().submitSearchItem(searchItem);

        long itemMentionNumber = getSearchResultPo().getNumberOfLinkHeadersContainingSearchItem(searchItem);
        assertThat(itemMentionNumber)
            .as("There should be multiple search results for a given item")
            .isGreaterThan(1L);
    }
}
