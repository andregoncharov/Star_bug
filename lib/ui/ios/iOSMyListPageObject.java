package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListPageObject extends MyListPageObject
{
    static
    {
        ARTICLE_BY_TITLE_TPL ="xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        SEARCH_INPUT_FOR_MY_LIST = "xpath://XCUIElementTypeSearchField[@name='Search']";
        SAVED_ARTICLE = "xpath://XCUIElementTypeLink";
    }
    public iOSMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
