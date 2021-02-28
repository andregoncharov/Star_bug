package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SEARCH_SKIP_BUTTON,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_NAVIGATE_UP_BUTTON,
            SEARCH_X_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_SUBSTRING1_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT;


    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElement1(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING1_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchButton()
    {
        if (Platform.getInstance().isMW()){
            return;
        }
        this.waitForElementAndClick(SEARCH_SKIP_BUTTON,"Cannot find and click 'SKIP' init button", 5);
        this.waitForElementNotPresent(SEARCH_SKIP_BUTTON,"Find 'SKIP' after clicking 'SKIP' init button",5);
    }

    @Step("Waiting for button to cancel search result")
    public void waitForNavigateUpButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_NAVIGATE_UP_BUTTON, "Cannot find search Navigate Up button",5);
    }

    @Step("Waiting for search cancel button to disappear")
    public void waitForNavigateUpButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_NAVIGATE_UP_BUTTON, "Search Navigate Up button is still present!",5);
    }

    public void clickNavigateUpButton()
    {
        this.waitForElementAndClick(SEARCH_NAVIGATE_UP_BUTTON, "Cannot find and click search Navigate Up button", 5);
    }

    @Step("Initializing the search field")
    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init element",5);
        this.waitForElementPresent(SEARCH_INPUT,"Cannot find search input after clicking search init element",5);
    }

    @Step("Clicking button to clear search result")
    public void clearSearchLine()
    {
        this.waitForElementAndClick(SEARCH_X_BUTTON,"Cannot find and click search 'close button'",5);
        this.waitForElementPresent(SEARCH_INPUT,"Cannot find search input 'search init element'",5);
    }

    @Step("Typing '{name_of_search}' to search line")
    public void typeSearchLine(String name_of_search)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, name_of_search,"Cannot find adn type into search input",5);
    }

    @Step("Waiting for search result")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring " + substring,10);
    }

    @Step("Waiting for search result1")
    public void waitForSearchResult1(String substring)
    {
        String search_result_xpath = getResultSearchElement1(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring " + substring,10);
    }

    @Step("Waiting for search result and select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring " + substring,10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAAmountOfElements(SEARCH_RESULT_ELEMENT);

    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Cannot find empty result element.", 15);
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

}
