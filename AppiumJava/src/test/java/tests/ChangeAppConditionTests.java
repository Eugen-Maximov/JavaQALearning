package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

@Epic("Conditions tests")
public class ChangeAppConditionTests extends CoreTestCase
{

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Articles"), @Feature(value="Conditions")})
    @DisplayName("Change orientation test")
    @Description("Open article, change orientation to landscape, then portrait and check article title")
    @Step("Starting testChangeScreenOrientationOnSearchingResults")
    @Severity(value = SeverityLevel.MINOR)
    public void testChangeScreenOrientationOnSearchingResults()
    {
        if (Platform.getInstance().isMW()){
            return;
        }
        String search_string = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Articles"), @Feature(value="Conditions")})
    @DisplayName("Go app to background")
    @Description("Start search, send app to background and then check search results")
    @Step("Starting testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckSearchArticleInBackground()
    {
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(5);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
