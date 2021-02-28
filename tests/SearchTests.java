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

@Epic("Tests for Search")
public class SearchTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Search article")
    @Description("Using the search bar, look for an article about Nokia")
    @Step("Starting test Search")
    @Severity(value = SeverityLevel.BLOCKER)

    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForSearchResult("innish");
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Cancel search article")
    @Description("Using the search bar, we are looking for an article about Nokia. We delete the search query.")
    @Step("Starting test CancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)

    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForNavigateUpButtonToAppear();
        SearchPageObject.clickNavigateUpButton();
        SearchPageObject.waitForNavigateUpButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Amount of not empty search")
    @Description("We do a search and check that the number of worn articles is greater than 0.")
    @Step("Starting test AmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)

    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        String search_Line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_Line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Amount of empty search")
    @Description("We do a search and check that the quantity is not correct, the search result is empty.")
    @Step("Starting test AAmountOfEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)

    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        String search_Line = "zxcvasdfqwer";
        SearchPageObject.typeSearchLine(search_Line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="NavigationUI")})
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


    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Cancellation search")
    @Description("Using the search bar, we are looking for an article about Nokia. We delete the search query.")
    @Step("Starting test CancellationSearch")
    @Severity(value = SeverityLevel.CRITICAL)

    public void testCancellationSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.waitForSearchResult("innish technology and telecommunications company");
        SearchPageObject.clearSearchLine();
    }
}
