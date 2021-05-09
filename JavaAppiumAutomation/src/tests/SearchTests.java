package tests;

import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
    }


    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

    }


    @Test
    public void testAmountOfNotEmptySearch()
    {
        String search_line = "Linkin Park Discography";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch()
    {
        String search_element = "aodkakdakdasd";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.typeSearchLine(search_element);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResult();
    }
}
