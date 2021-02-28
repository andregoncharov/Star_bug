package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject
{
    static
    {
        ARTICLE_BY_TITLE_TPL ="xpath://ul[contains(@class,'content-unstyled')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'content-unstyled')]//h3[contains(text(),'{TITLE}')]/../../a[contains(@class, 'watched')]";
        //SEARCH_INPUT_FOR_MY_LIST = "xpath://XCUIElementTypeSearchField[@name='Search']";
        //SAVED_ARTICLE = "xpath://XCUIElementTypeLink";
    }
    public MWMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}