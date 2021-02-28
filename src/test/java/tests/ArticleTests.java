package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Article")
public class ArticleTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open 'Finnish' article and make sure the title is expected")
    @Step("Starting test CompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)

    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("innish");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

       // ArticlePageObject.takeScreenshot("article_page");

        Assert.assertEquals(
                "We see unexpected title!",
                "Nokia",
                article_title
        );

    }

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and swipe it to the footer(проверка русского языка)")
    @Step("Starting test SwipeArticle")
    @Severity(value = SeverityLevel.MINOR)

    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("innish");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }

    //Refactoring Ex6 (TestForDZ_Ex6)
    // wait закоментен, т.к. он используется общий для ArticleTest c timeoutInSeconds 15
    /*@Test
    public void testWaitTitleNotTimeout()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("Finnish technology and telecommunications company");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String element_found = "Nokia";
        //ArticlePageObject.waitForTitleElement();
        ArticlePageObject.assertElementPresent(element_found);
    }*/


}
