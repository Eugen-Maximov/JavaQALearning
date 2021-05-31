import lib.CoreTestCase;
import lib.UI.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;
    public void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


        @Test
        public void testFailAssertElementPresent()
        {
            String search_line = "Java";

            MainPageObject.waitForElementAndClick(
                    "xpath://*[contains(@text,'Search Wikipedia')]",
                    "Cannot find 'Search Wikipedia' input",
                    5
            );

            MainPageObject.waitForElementAndSendKeys(
                    "xpath://*[contains(@text,'Search…')]",
                    search_line,
                    "Cannot find search input",
                    5
            );
            MainPageObject.waitForElementPresent(
                    "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
                    "Cannot find 'Object-oriented programming language' topic search by 'Java'",
                    15
            );
            MainPageObject.assertElementPresent(
                    "id:org.wikipedia:id/view_page_title_text",
                    "text",
                    "Cannot find title in article"
            );
        }
}

