import com.gargoylesoftware.htmlunit.javascript.host.dom.Text;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
            capabilities.setCapability("app", "C:\\Users\\Eugen\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

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


        private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }

        private WebElement waitForElementPresent(By by, String error_message)
        {
            return waitForElementPresent(by, error_message, 3);
        }

        private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, 3);
            element.click();
            return element;
        }

        private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, 3);
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
}

