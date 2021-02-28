package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for My Lists")
public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Mobil device company";
    private static final String
    login = "Andgoncharov",
    password = "16121983Bad";

    @Test
    @Features(value = {@Feature(value="My lists"), @Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Save first article to my lists")
    @Description("We are looking for an article about nokia, save it to my lists. We open my lists and remove the found article from there.")
    @Step("Starting test SaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)

    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");
        SearchPageObject.clickByArticleWithSubstring("innish");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();


        String article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.clickStar(); //Ошибка происходи тут!!!
        /*if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {ArticlePageObject.addArticlesToMySaved(); }*/

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);

            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid())
        { MyListPageObject.openFolderByName(name_of_folder); }

        MyListPageObject.swipeByArticleToDelete(article_title);

    }


    //Refactoring Ex11
    // Для Android статьи добовляются не просто в папку "Save", а в специально создаваемую папку.
    //Для другого способа верификации статьи я использую встроенный поиск, который реализован как в iOS, так и на Android,
    //он позволяет найти статью среди добавленых в закладки, а потом мы считаем количество оставшихся закладок, после свайпа одной.

    @Test
    @Features(value = {@Feature(value="My lists"), @Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Save two articles on my lists")
    @Description("We are looking for two articles we save to our lists. Open my lists and delete one article from there.")
    @Step("Starting test SaveSecondArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveSecondArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Nokia");

        if (Platform.getInstance().isAndroid()){
            SearchPageObject.clickByArticleWithSubstring("innish technology and telecommunications company");
        } else { SearchPageObject.clickByArticleWithSubstring("innish"); }

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = "HTC";
        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else { ArticlePageObject.addArticlesToMySaved(); }

        ArticlePageObject.closeArticleAndClearSearchLine();

        SearchPageObject.typeSearchLine("HTC");
        if(Platform.getInstance().isAndroid()){
            SearchPageObject.clickByArticleWithSubstring("aiwanese electronics company");
        } else { SearchPageObject.clickByArticleWithSubstring("aiwanese"); }

        ArticlePageObject.waitForTitleElement1();
        if(Platform.getInstance().isAndroid()) {
        ArticlePageObject.addArticleMyList();
        ArticlePageObject.savedArticleToMyList(name_of_folder);
        } else { ArticlePageObject.addArticlesToMySavedNotInformationWindow(); }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid())
        { MyListPageObject.openFolderByName(name_of_folder); }
        MyListPageObject.swipeByArticleToDelete(article_title);
        //другой способ верификации оставшейся статьи
        MyListPageObject.waitForArticleNotUsedTitle("Nokia");
    }

}
