package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Change app condition")
public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="NavigationUI"), @Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Change screen orientation on search results")
    @Description("We are looking for nokia and rotate the screen to landscape, and then back to portrait.")
    @Step("Starting test ChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.MINOR)

    public void testChangeScreenOrientationOnSearchResults()
    {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("innish technology and telecommunications company");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have ben changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have ben changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="NavigationUI")})
    @DisplayName("Check search article in background")
    @Description("Checking the application's reaction to leaving the background and returning from it.")
    @Step("Starting test CheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.MINOR)

    public void testCheckSearchArticleInBackground()
    {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForSearchResult("innish technology and telecommunications company");
        this.backgroundApp(5);
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForSearchResult("innish technology and telecommunications company");

    }
}
