package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.*;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    public static final String list_name = "Learning programming";
    public static final String login = "QQJamm";
    public static final String password = "1234567890";

    @Test
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

            assertEquals("We are not this page after login", article_title, ArticlePageObject.getArticleTitle());

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
    public void testSaveTwoArticlesAndDeleteOne()
    {
        String search_line = "Java";
        String folder_name = "Saved articles";
        String first_article_title = "Programming language";
        String second_article_title = "Object-oriented programming language";

        //add first article
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(first_article_title);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.addFirstArticleToMyList(folder_name);
        ArticlePageObject.closeArticle();

        //add second article
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(second_article_title);
        ArticlePageObject.addSecondArticleToList(folder_name);
        ArticlePageObject.closeArticle();

        // go to folder + delete first article
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(folder_name);
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.waitArticleToAppearByTitle(first_article_title);

        // final check of titles
        String title_from_list = MyListsPageObject.getSavedArticleTitle(second_article_title);
        MyListsPageObject.openSavedArticle(second_article_title);
        String title_from_article = ArticlePageObject.getArticleTitle();
        assertEquals(
                "The titles of the articles from My Lists and the Opened article are different",
                title_from_article,
                title_from_list
        );
    }
}
