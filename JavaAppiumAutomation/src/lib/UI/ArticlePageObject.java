package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTION_ADD_TO_MY_LIST_BUTTON = "//android.widget.TextView[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            INPUT_FIELD = "org.wikipedia:id/text_input",
            OK_BUTTON = "//android.widget.Button[@text='OK']",
            CLOSE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";



        public ArticlePageObject(AppiumDriver driver)
        {
            super(driver);
        }

        public WebElement waitForTitleElement()
        {
            return this.waitForElementPresent(By.id(TITLE), "Cannot find article title", 15);
        }

        public String getArticleTitle()
        {
            WebElement title_element = waitForTitleElement();
            return title_element.getAttribute("text");
        }

        public void swipeToFooter()
        {
            this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of the article", 20);
        }

        public void addFirstArticleToMyList(String name_of_folder)
        {
            this.waitForRender(
                    By.xpath("//android.widget.FrameLayout"),
                    "Cannot render this page",
                    30
            );
            this.swipeUp(2000);
            this.waitForRender(
                    By.xpath(OPTIONS_BUTTON),
                    "Not elements by android.widget.ImageView can be upload",
                    15
            );
            this.waitForElementAndClick(
                    By.xpath(OPTIONS_BUTTON),
                    "Cannot find 'More options' button",
                    15
            );
            this.waitForRender(
                    By.xpath("//android.widget.TextView"),
                    "Not menu elements can be upload",
                    15
            );
            this.waitForElementAndClick(
                    By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),
                    "Cannot find 'Add to reading list' button",
                    5
            );
            this.waitForElementAndClick(
                    By.id(ADD_TO_MY_LIST_OVERLAY),
                    "Cannot find OK button",
                    10
            );
            this.waitForRender(
                    By.id(INPUT_FIELD),
                    "Cannot render input field",
                    10
            );
            this.waitForElementAndClear(
                    By.id(INPUT_FIELD),
                    "Cannot find input field",
                    5
            );
            this.waitForRender(
                    By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/text_input']"),
                    "Cannot render input field",
                    10
            );
            this.waitForElementAndSendKeys(
                    By.xpath("//android.widget.EditText[@resource-id='org.wikipedia:id/text_input']"),
                    name_of_folder,
                    "Cannot find input field and send Keys",
                    5
            );
            this.waitForRender(
                    By.xpath(OK_BUTTON),
                    "Cannot render input field",
                    10
            );
            this.waitForElementAndClick(
                    By.xpath(OK_BUTTON),
                    "Cannot find Ok button to send the name of list",
                    5
            );
        }

        public void addSecondArticleToList(String list_name)
        {
            this.waitForRender(
                    By.xpath("//android.widget.FrameLayout"),
                    "Cannot render this page",
                    30
            );
            this.swipeUp(2000);
            this.waitForRender(
                    By.xpath(OPTIONS_BUTTON),
                    "Not elements by android.widget.ImageView can be upload",
                    15
            );
            this.waitForElementAndClick(
                    By.xpath(OPTIONS_BUTTON),
                    "Cannot find 'More options' button",
                    15
            );
            this.waitForRender(
                    By.xpath("//android.widget.TextView"),
                    "Not menu elements can be upload",
                    15
            );
            this.waitForElementAndClick(
                    By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),
                    "Cannot find 'Add to reading list' button",
                    5
            );
            this.waitForElementAndClick(
                    By.xpath("//android.widget.LinearLayout//*[@text='" + list_name + "']"),
                    "Cannot find reading list: " + list_name,
                    15
            );
        }

        public void closeArticle()
        {
            this.waitForElementAndClick(By.xpath(CLOSE_BUTTON), "Cannot find close article title", 10);
        }

        public void assertArticle()
        {
            this.assertElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "text",
                    "Cannot find title in article"
            );
        }
}
