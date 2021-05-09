package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }


    public static final String
            FOLDER_BY_NAME_TPL = "//*[@resource-id='org.wikipedia:id/item_container']//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";


    /* TEMPLATES METHODS */
    private static String getFolderByNameTpl(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathByTPL(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATES METHODS */


    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderByNameTpl(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name: " + name_of_folder + ";",
                10
        );
    }


    public void swipeByArticleToDelete(String article_title)
    {
        this.waitArticleToAppearByTitle(article_title);
        String article_title_xpath = getFolderByNameTpl(article_title);
        this.swipeElementToLeft(
                By.xpath(article_title_xpath),
                "Cannot find article in created folder"
        );
        this.waitArticleToDisappearByTitle(article_title);
    }

    public void waitArticleToDisappearByTitle(String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByTPL(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_title_xpath),
                "Saved article still present with title " + article_title,
                15
                );
    }

    public void waitArticleToAppearByTitle(String article_title)
    {
        String search_result_container = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        String article_title_xpath = getSavedArticleXpathByTPL(article_title);
        this.waitForElementPresent(
                By.xpath(article_title_xpath),
                "Cannot find saved article by title " + article_title,
                15
        );
    }
}

