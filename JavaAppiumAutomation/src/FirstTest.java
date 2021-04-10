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


        private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }

        private WebElement waitForElementPresent(By by, String error_message)
        {
            return waitForElementPresent(by, error_message, 2);
        }

        private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSecond)
        {
            WebElement element = waitForElementPresent(by, error_message, 3);
            element.click();
            return element;
        }

        private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSecond)
        {
            WebElement element = waitForElementPresent(by, error_message, 3);
            element.sendKeys(value);
            return element;
        }

        private boolean waitForElementNotPresent(By by, String error_message, long timeOutSecond)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOutSecond);
            wait.withMessage(error_message + "\n");
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }

        private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSecond)
        {
            WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
            element.clear();
            return element;
        }
}

