package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            INPUT_FIELD,
            OK_BUTTON,
            CLOSE_BUTTON;



        public ArticlePageObject(RemoteWebDriver driver)
        {
            super(driver);
        }

        public WebElement waitForTitleElement()
        {
            return this.waitForElementPresent(TITLE, "Cannot find article title", 15);
        }

        public String getArticleTitle()
        {
            WebElement title_element = waitForTitleElement();
            if (Platform.getInstance().isAndroid()) {
                return title_element.getAttribute("text");
            } else if (Platform.getInstance().isIOS()){
                return title_element.getAttribute("name");
            } else {
                return title_element.getText();
            }

        }

        public void swipeToFooter()
        {
            if (Platform.getInstance().isAndroid()){
                this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of the article", 20);
            } else if (Platform.getInstance().isIOS()){
                this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of the article", 20);
            } else {
                this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 40);
            }

        }

        public void addArticleToMySaved(){
            if (Platform.getInstance().isMW()){
                this.removeArticleFromSavedIfITAdded();
            }
            this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find and add article to saved", 5);
        }

        public void removeArticleFromSavedIfITAdded()
        {
            if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
                this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot click button to remove the article", 5);
                this.waitForElementPresent(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find add button", 5);
            }
        }

        public void addFirstArticleToMyList(String name_of_folder)
        {
            this.waitForRender(
                    "xpath://android.widget.FrameLayout",
                    "Cannot render this page",
                    30
            );
            this.swipeUp(2000);
            this.waitForRender(
                    OPTIONS_BUTTON,
                    "Not elements by android.widget.ImageView can be upload",
                    15
            );
            this.waitForElementAndClick(
                    OPTIONS_BUTTON,
                    "Cannot find 'More options' button",
                    15
            );
            this.waitForRender(
                    "xpath://android.widget.TextView",
                    "Not menu elements can be upload",
                    15
            );
            this.waitForElementAndClick(
                    OPTION_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find 'Add to reading list' button",
                    5
            );
            this.waitForElementAndClick(
                    ADD_TO_MY_LIST_OVERLAY,
                    "Cannot find OK button",
                    10
            );
            this.waitForRender(
                    INPUT_FIELD,
                    "Cannot render input field",
                    10
            );
            this.waitForElementAndClear(
                    INPUT_FIELD,
                    "Cannot find input field",
                    5
            );
            this.waitForRender(
                    "xpath://android.widget.EditText[@resource-id='org.wikipedia:id/text_input']",
                    "Cannot render input field",
                    10
            );
            this.waitForElementAndSendKeys(
                    "xpath://android.widget.EditText[@resource-id='org.wikipedia:id/text_input']",
                    name_of_folder,
                    "Cannot find input field and send Keys",
                    5
            );
            this.waitForRender(
                    OK_BUTTON,
                    "Cannot render input field",
                    10
            );
            this.waitForElementAndClick(
                    OK_BUTTON,
                    "Cannot find Ok button to send the name of list",
                    5
            );
        }

        public void addSecondArticleToList(String list_name)
        {
            this.waitForRender(
                    "xpath://android.widget.FrameLayout",
                    "Cannot render this page",
                    30
            );
            this.swipeUp(2000);
            this.waitForRender(
                    OPTIONS_BUTTON,
                    "Not elements by android.widget.ImageView can be upload",
                    15
            );
            this.waitForElementAndClick(
                    OPTIONS_BUTTON,
                    "Cannot find 'More options' button",
                    15
            );
            this.waitForRender(
                    "xpath://android.widget.TextView",
                    "Not menu elements can be upload",
                    15
            );
            this.waitForElementAndClick(
                    OPTION_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find 'Add to reading list' button",
                    5
            );
            this.waitForElementAndClick(
                    "xpath://android.widget.LinearLayout//*[@text='" + list_name + "']",
                    "Cannot find reading list: " + list_name,
                    15
            );
        }

        public void closeArticle()
        {
            this.waitForElementAndClick(CLOSE_BUTTON, "Cannot find close article title", 10);
        }

        public void assertArticle()
        {
            if (Platform.getInstance().isAndroid() || (Platform.getInstance().isIOS())){
                this.assertElementPresent(
                        "id:org.wikipedia:id/view_page_title_text",
                        "text",
                        "Cannot find title in article"
                );
            } else {
                System.out.println("Method rotate isn`t work on platform: " + Platform.getInstance().getPlatformVar());
            }
        }
}
