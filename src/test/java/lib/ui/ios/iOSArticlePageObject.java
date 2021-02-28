package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject
{
    static {

        TITLE_TPL = "id:HTC";
        TITLE = "id:Nokia";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_BUTTON_BOOKMARK = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CLOSE_ARTICLE_BUTTON1 = "id:Back";
        OPTIONS_BUTTON_X_FOR_INFORMATION_WINDOW = "id:places auth close";
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField";
        OPTIONS_BUTTON_CLEAR_MINI = "id:clear mini";
    }

    public iOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
