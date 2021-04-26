import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.MainPageObject;
import lib.UI.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import java.util.List;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

        @Test
        public void testSearch()
        {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.clickByArticleWhithSubstring("Object-oriented programming language");
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
        public void testCompareArticleTitle()
        {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.clickByArticleWhithSubstring("Object-oriented programming language");

            ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
            String article_title = ArticlePageObject.getArticleTitle();

            Assert.assertEquals(
                    "We see unexpected titles",
                    "Java (programming language)",
                    article_title
            );
        }

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWhithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

        @Test
        public void testForElementHasText()
        {
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    3
            );

            MainPageObject.assertElementHasText(
                    By.id("org.wikipedia:id/search_src_text"),
                    "Search…",
                    "Cannot find the expected text 'Search…' in the search field",
                    3
            );
        }

        @Test
        public void testSearchResultsAndClear()
        {
            String search_word = "Java";

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    3
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_word,
                    "Cannot find search input",
                    3
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                    "Cannot find any search result",
                    10
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@index='1']"), // индексы элементов начинаются с 0, если необходимо проверить, что элементов несколько, то достаточно проверить лишь 2й элемент с индексом - 1
                    "There is only one search result, not several"
            );
            MainPageObject.waitForElementAndClear(
                    By.id("org.wikipedia:id/search_src_text"),
                    "Cannot find search field",
                    3
            );
            MainPageObject.waitForElementNotPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                    "Search results were not deleted",
                    3
            );
        }

        @Test
        public void testSearchResults()
        {
            String search_word = "Java";

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    3
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_word,
                    "Cannot find search input",
                    3
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                    "Cannot find any search result",
                    10
            );

            List<WebElement> search_elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
            for (WebElement s:search_elements){
                String search_result = s.getText();
                Assert.assertTrue(
                        "There is a search result without a search value: " + search_result + ", ",
                        search_result.contains(search_word)
                );

            }

        }

        @Test
        public void testSaveFirstArticleToMyList()
        {
            String list_name = "Learning programming";

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );

            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find search input",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic search by 'Java'",
                    15
            );
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.FrameLayout"),
                    "Cannot render this page",
                    30
            );
            MainPageObject.swipeUp(2000);
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Not elements by android.widget.ImageView can be upload",
                    15
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot find 'More options' button",
                    15
            );
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.TextView"),
                    "Not menu elements can be upload",
                    15
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                    "Cannot find 'Add to reading list' button",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find OK button",
                    10
            );
            MainPageObject.waitForRender(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot render input field",
                    10
            );
            MainPageObject.waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find input field",
                    5
            );
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/text_input']"),
                    "Cannot render input field",
                    10
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/text_input']"),
                    list_name,
                    "Cannot find input field and send Keys",
                    5
            );
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.Button[@text='OK']"),
                    "Cannot render input field",
                    10
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.Button[@text='OK']"),
                    "Cannot find Ok button to send the name of list",
                    5
            );
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.ImageButton"),
                    "Cannot render X button",
                    15
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot find X button",
                    5
            );
            MainPageObject.waitForRender(
                    By.id("org.wikipedia:id/fragment_main_nav_tab_layout"),
                    "Cannot render navigation bar",
                    10
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                    "Cannot find My Lists navigation button",
                    10
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='" + list_name + "']"),
                    "Cannot find created list: " + list_name + ";",
                    10
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Cannot find article in created folder",
                    10
            );
            MainPageObject.swipeElementToLeft(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Cannot find article in created folder"
            );
            MainPageObject.waitForElementNotPresent(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Article is not deleted",
                    10
            );
        }

        @Test
        public void testAmountOfNotEmptySearch()
        {
            String search_line = "Linkin Park Discography";
            String search_result_container = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_line,
                    "Cannot find search input",
                    5
            );
            MainPageObject.waitForElementPresent(
                    By.xpath(search_result_container),
                    "Cannot find anything by the request " + search_line,
                    15
            );
            int amount_of_search_results = MainPageObject.getAmountOfElements(
                    By.xpath(search_result_container)
            );
            Assert.assertTrue(
                    "We found too few results",
                    amount_of_search_results > 0
            );

        }

        @Test
        public void testAmountOfEmptySearch()
        {
            String search_element = "aodkakdakdasd";
            String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
            String empty_result_label = "//*[@text='No results found']";

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_element,
                    "Cannot find search input",
                    5
            );
            MainPageObject.waitForElementPresent(
                    By.xpath(empty_result_label),
                    "Cannot find empty result label by the request: " +search_element,
                    15
            );
            MainPageObject.assertElementNotPresent(
                    By.xpath(search_result_locator),
                    "We`ve found some results by request " + search_element
            );
        }

        @Test
        public void testChangeScreenOrientationOnSearchingResults()
        {
            String search_string = "Java";

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_string,
                    "Cannot find search input",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic search by " + search_string,
                    30
            );
            String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "text",
                    "Cannot find title of article",
                    15
            );
            driver.rotate(ScreenOrientation.LANDSCAPE);
            String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "text",
                    "Cannot find title of article",
                    15
            );
            Assert.assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_rotation
            );
            driver.rotate(ScreenOrientation.PORTRAIT);
            String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "text",
                    "Cannot find title of article",
                    15
            );
            Assert.assertEquals(
                    "Article title have been changed after screen rotation",
                    title_before_rotation,
                    title_after_second_rotation
            );
        }

        @Test
        public void testCheckSearchArticleInBackground()
        {
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );

            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find search input",
                    5
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic search by 'Java'",
                    15
            );

            driver.runAppInBackground(5);

            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' after returning after background'",
                    15
            );

        }

        @Test
        public void testSaveTwoArticlesAndDeleteOne()
        {
            String search_line = "Java";
            String folder_name = "Saved articles";
            String search_first_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Island of Indonesia']";
            String search_second_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']";
            String folder_first_article_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='island of Indonesia']";
            String folder_second_article_lokator = "//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='object-oriented programming language']";

            //add first article
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );

            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_line,
                    "Cannot find search input",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath(search_first_result_locator),
                    "Cannot find first article by search '" + search_line + "';",
                    15
            );
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Not elements by android.widget.ImageView can be upload",
                    15
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot find 'More options' button",
                    5
            );
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.TextView"),
                    "Not menu elements can be upload",
                    15
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                    "Cannot find 'Add to reading list' button",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find GOT IT button",
                    10
            );
            MainPageObject.waitForRender(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot render input field",
                    10
            );
            MainPageObject.waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find input field",
                    5
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/text_input']"),
                    folder_name,
                    "Cannot find input field and send Keys",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.Button[@text='OK']"),
                    "Cannot find Ok button to send the name of list",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot find X button",
                    5
            );

            //add second article

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );
            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_line,
                    "Cannot find search input",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath(search_second_result_locator),
                    "Cannot find second article by search '" + search_line + "';",
                    15
            );
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Not elements by More Options menu can be upload",
                    15
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot find 'More options' button",
                    5
            );
            MainPageObject.waitForRender(
                    By.xpath("//android.widget.TextView"),
                    "Not menu elements can be upload",
                    15
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                    "Cannot find 'Add to reading list' button",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.LinearLayout//*[@text='" + folder_name + "']"),
                    "Cannot find reading list: " + folder_name,
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot find X button",
                    5
            );

            // go to folder + delete first article

            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                    "Cannot find My Lists navigation button",
                    10
            );
            MainPageObject.waitForRender(
                    By.id("org.wikipedia:id/item_container"),
                    "Cannot render My List`s folders",
                    10
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@text='" + folder_name + "']"),
                    "Cannot find created list: " + folder_name + ";",
                    10
            );

            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@text='island of Indonesia']"),
                    "Cannot find first added article in list" + folder_name,
                    5
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@text='object-oriented programming language']"),
                    "Cannot find second added article in list" + folder_name,
                    5
            );
            MainPageObject.swipeElementToLeft(
                    By.xpath("//*[@text='island of Indonesia']"),
                    "Cannot find and swipe first article"
            );
            MainPageObject.waitForElementNotPresent(
                    By.xpath("//*[@text='island of Indonesia']"),
                    "The first article was not deleted from " + folder_name,
                    5
            );

            // final check of titles

            String title_from_list = MainPageObject.waitForElementAndGetAttribute(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "text",
                    "Cannot find title of article in " + folder_name + " list;",
                    5
            );
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Cannot find title of article in " + folder_name + " list;",
                    5
            );
            String title_from_article_page = MainPageObject.waitForElementAndGetAttribute(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "text",
                    "Cannot find title from page Java (programming language)",
                    10
            );
            Assert.assertEquals(
                    "The titles of the articles from My Lists and the Opened article are different",
                    title_from_article_page,
                    title_from_list
            );

        }

        @Test
        public void testFailAssertElementPresent()
        {
            String search_line = "Java";

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );

            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_line,
                    "Cannot find search input",
                    5
            );
            MainPageObject.waitForElementPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic search by 'Java'",
                    15
            );
            MainPageObject.assertElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "text",
                    "Cannot find title in article"
            );
        }
}

