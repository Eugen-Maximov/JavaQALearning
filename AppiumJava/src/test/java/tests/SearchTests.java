package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@Epic("Search tests")
public class SearchTests extends CoreTestCase
{
    @Test
    @Feature(value="Search")
    @DisplayName("Search test")
    @Description("Test wiki search")
    @Step("Starting testSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Cancel search test")
    @Description("Test wiki search and cancel it")
    @Step("Starting testCancelSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

    }


    @Test
    @Feature(value="Search")
    @DisplayName("Test some results by search")
    @Description("Test wiki search that there is some search results")
    @Step("Starting testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testAmountOfNotEmptySearch()
    {
        String search_line = "Linkin Park Discography";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Test invalid search")
    @Description("Test invalid search and nothing search result by it")
    @Step("Starting testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch()
    {
        String search_element = "aodkakdakdasd";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.typeSearchLine(search_element);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResult();
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Test init search input")
    @Description("Check that search input has text")
    @Step("Starting testForElementHasText")
    @Severity(value = SeverityLevel.MINOR)
    public void testForElementHasText()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Search results and clear them")
    @Description("Search and clear results")
    @Step("Starting testSearchResultsAndClear")
    @Severity(value = SeverityLevel.MINOR)
    public void testSearchResultsAndClear()
    {
        String search_word = "Java";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_word);
        SearchPageObject.waitForSearchResult(search_word);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForArticleIsDisappear();
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Find some search results")
    @Description("Search and check for any result")
    @Step("Starting testSearchResults")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchResults()
    {
        String search_word = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForAnySearchResult();

        List<WebElement> search_elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        for (WebElement s:search_elements){
            String search_result = s.getText();
            Assert.assertTrue(
                    "There is a search result without a search value: " + search_result + ", ",
                    search_result.contains(search_word)
            );
        }
    }
}
