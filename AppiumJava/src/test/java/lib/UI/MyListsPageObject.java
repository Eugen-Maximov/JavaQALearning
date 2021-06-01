package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;


public class MyListsPageObject extends MainPageObject {
    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }


    protected static String
                FOLDER_BY_NAME_TPL,
                ARTICLE_BY_TITLE_TPL,
                REMOVE_FROM_SAVED_BUTTON;


    /* TEMPLATES METHODS */
    private static String getFolderByNameTpl(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathByTPL(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    private static String getLocatorButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */

    @Step("Open folder by name: '{name_of_folder}'")
    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderByNameTpl(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name: " + name_of_folder + ";",
                10
        );
    }

    @Step("Swipe article to delete")
    public void swipeByArticleToDelete(String article_title)
    {
        this.waitArticleToAppearByTitle(article_title);
        String article_title_xpath = getFolderByNameTpl(article_title);

        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isIOS())){
            this.swipeElementToLeft(
                    article_title_xpath,
                    "Cannot find article in created folder"
            );
        } else {
            String remove_locator = getLocatorButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Cannot find and click remove button", 5);
        }

        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
        this.waitArticleToDisappearByTitle(article_title);
    }

    @Step("Wait article to disappear by title")
    public void waitArticleToDisappearByTitle(String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByTPL(article_title);
        this.waitForElementNotPresent(
                article_title_xpath,
                "Saved article still present with title " + article_title,
                15
                );
    }

    @Step("Wait article to appear by title")
    public void waitArticleToAppearByTitle(String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByTPL(article_title);
        this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    @Step("Get saved article title")
    public String getSavedArticleTitle(String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByTPL(article_title);
        return this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article by title " + article_title,
                15
        ).getText();
    }

    @Step("Open saver article by name: '{article_title}'")
    public void openSavedArticle(String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByTPL(article_title);
        this.waitForElementAndClick(
                article_title_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }
}

