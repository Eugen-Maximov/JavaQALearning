import com.gargoylesoftware.htmlunit.javascript.host.Touch;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Text;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private AppiumDriver driver;

        @Before
        public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "10");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "C:\\Users\\Eugen\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");//HomePC
            //capabilities.setCapability("app","C:\\Users\\user1.DESKTOP-H3JEDUD\\Documents\\GitHub\\JavaQALearning\\JavaAppiumAutomation\\apks\\org.wikipedia.apk"); //WorkPC

            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        }
        @After
        public void tearDown()
        {
            driver.quit();
        }

        @Test
        public void firstTest()
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    3
            );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find search input",
                    3
            );

            waitForElementPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic search by 'Java'",
                    15

            );
        }


        @Test
        public void testCancelSearch()
        {
            waitForElementAndClick(
                    By.id("org.wikipedia:id/search_container"),
                    "Cannot find 'Search Wikipedia' input",
                    3
            );
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find search input",
                    3
            );

            waitForElementAndClear(
                    By.id("org.wikipedia:id/search_src_text"),
                    "Cannot find search field",
                    3
            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/search_close_btn"),
                    "Cannot find 'X' to cancel search",
                    3
            );
            waitForElementNotPresent(
                    By.id("org.wikipedia:id/search_close_bin"),
                    "X still present on the page",
                    3
            );
        }

        @Test
        public void testCompareArticleTitle()
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    3
            );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find search input",
                    3
            );
            waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic search by 'Java'",
                    5
            );

            WebElement title_element = waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot find article title",
                    10
            );

            String article_title = title_element.getAttribute("text");

            Assert.assertEquals(
                    "We see unexpected title",
                    "Java (programming language)",
                    article_title
            );
        }

    @Test
    public void testSwipeArticle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                3
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                3
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' topic search by 'Appium'",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                10
        );
        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20
        );
    }

        @Test
        public void testForElementHasText()
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    3
            );

            assertElementHasText(
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

            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    3
            );
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_word,
                    "Cannot find search input",
                    3
            );
            waitForElementPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                    "Cannot find any search result",
                    10
            );
            waitForElementPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@index='1']"), // индексы элементов начинаются с 0, если необходимо проверить, что элементов несколько, то достаточно проверить лишь 2й элемент с индексом - 1
                    "There is only one search result, not several"
            );
            waitForElementAndClear(
                    By.id("org.wikipedia:id/search_src_text"),
                    "Cannot find search field",
                    3
            );
            waitForElementNotPresent(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                    "Search results were not deleted",
                    3
            );
        }

        @Test
        public void testSearchResults()
        {
            String search_word = "Java";

            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    3
            );
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    search_word,
                    "Cannot find search input",
                    3
            );
            waitForElementPresent(
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
        public void saveFirstArticleToMyList()
        {
            String list_name = "Learning programming";
            /*
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find 'Search Wikipedia' input",
                    5
            );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Java",
                    "Cannot find search input",
                    5
            );
            waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "Cannot find 'Object-oriented programming language' topic search by 'Java'",
                    30
            );
            waitForRender(
                    By.xpath("//android.widget.FrameLayout"),
                    "Cannot render this page",
                    30
            );
            swipeUp(2000);
            waitForRender(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Not elements by android.widget.ImageView can be upload",
                    15
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot find 'More options' button",
                    15
            );
            waitForRender(
                    By.xpath("//android.widget.TextView"),
                    "Not menu elements can be upload",
                    15
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                    "Cannot find 'Add to reading list' button",
                    5
            );
            waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find OK button",
                    10
            );
            waitForRender(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot render input field",
                    10
            );
            waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find input field",
                    5
            );
            waitForRender(
                    By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/text_input']"),
                    "Cannot render input field",
                    10
            );
            waitForElementAndSendKeys(
                    By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/text_input']"),
                    list_name,
                    "Cannot find input field and send Keys",
                    5
            );
            waitForRender(
                    By.xpath("//android.widget.Button[@text='OK']"),
                    "Cannot render input field",
                    10
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.Button[@text='OK']"),
                    "Cannot find Ok button to send the name of list",
                    5
            );
            waitForRender(
                    By.xpath("//android.widget.ImageButton"),
                    "Cannot render X button",
                    15
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot find X button",
                    5
            );*/
            waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                    "Cannot find My Lists button",
                    10
            );
            waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout//*[@text='" + list_name + "']"),
                    "Cannot find created list: " + list_name + ";",
                    10
            );
            waitForElementPresent(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Cannot find article in created folder",
                    10
            );
            swipeElementToLeft(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Cannot find article in created folder"
            );
            waitForElementNotPresent(
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Article is not deleted",
                    10
            );
        }


        private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }

        private WebElement waitForElementPresent(By by, String error_message)
        {
            return waitForElementPresent(by, error_message, 5);
        }

        private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
            element.click();
            return element;
        }

        private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
            element.sendKeys(value);
            return element;
        }

        private boolean waitForElementNotPresent(By by, String error_message, long timeOutSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }

        private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
            element.clear();
            return element;
        }

        private boolean assertElementHasText(By by, String expected_text, String error_message, long timeOutInSeconds)
        {
            WebDriverWait text = new WebDriverWait(driver, timeOutInSeconds);
            text.withMessage(error_message + "\n");
            return text.until(ExpectedConditions.textToBePresentInElementLocated(by, expected_text));
        }

        protected void swipeUp(int timeOFSwipe)
        {
            TouchAction action = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            action
                    .press(x, start_y)
                    .waitAction(timeOFSwipe)
                    .moveTo(x, end_y)
                    .release()
                    .perform();
        }

        protected void swipeUpQuick()
        {
            swipeUp(200);
        }

        protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
        {
            int already_swiped = 0;
            while (driver.findElements(by).size() == 0){

                if (already_swiped > max_swipes){
                    waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                    return;
                }
                swipeUpQuick();
                ++already_swiped;
            }
        }

        private void waitForRender(By by, String error_message, long timeOutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds); wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            wait.withMessage(error_message + "\n");
        }

        protected void swipeElementToLeft(By by, String error_message)
        {
            WebElement element = waitForElementPresent(by, error_message, 10);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction(driver);
            action
                    .press(right_x, middle_y)
                    .waitAction(150)
                    .moveTo(left_x, middle_y)
                    .release()
                    .perform();

        }
}

