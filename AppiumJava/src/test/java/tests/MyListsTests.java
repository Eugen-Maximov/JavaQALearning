package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.*;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("My lists tests")
public class MyListsTests extends CoreTestCase
{
    public static final String list_name = "Learning programming";
    public static final String login = "Euv1 QA";
    public static final String password = "+N7%@wtjjSwx3Ca";

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Articles"), @Feature(value="MyLists")})
    @DisplayName("Save article to MyList")
    @Description("Find and save first article to MyList")
    @Step("Starting testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addFirstArticleToMyList(list_name);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not this page after login", article_title, ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(list_name);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Articles"), @Feature(value="MyLists")})
    @DisplayName("Save 2 articles to MyList and delete one")
    @Description("Find and save 2 articles to MyLists, then delete one and check another")
    @Step("starting testSaveTwoArticlesAndDeleteOne")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveTwoArticlesAndDeleteOne()
    {
        String search_line = "Java";
        String folder_name = "Saved articles";
        String first_article_title = "rogramming language";
        String second_article_title = "bject-oriented programming language";

        //add first article
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(first_article_title);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addFirstArticleToMyList(list_name);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not this page after login", article_title, ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticleToMySaved();
        } else {
            ArticlePageObject.closeArticle();
        }


        //add second article
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(second_article_title);
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addFirstArticleToMyList(list_name);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on same page after login", article_title, ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticleToMySaved();
        } else {
            ArticlePageObject.closeArticle();
        }


        // go to folder + delete first article
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(folder_name);
        }
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.waitArticleToDisappearByTitle(first_article_title);

        // final check of titles
        String title_from_list = MyListsPageObject.getSavedArticleTitle(second_article_title);
        MyListsPageObject.openSavedArticle(second_article_title);
        String title_from_article = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "The titles of the articles from My Lists and the Opened article are different",
                title_from_article,
                title_from_list
        );
    }
}
