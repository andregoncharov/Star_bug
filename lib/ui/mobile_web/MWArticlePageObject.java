package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static {

        TITLE_TPL = "css:#content h1";
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions a#ca-watch.mw-ui-icon-wikimedia-star-base20";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON ="css:#page-actions a#ca-watch.mw-ui-icon-wikimedia-unStar-progressive[role='button']";
        CLOSE_ARTICLE_BUTTON1 = "id:Back";
        OPTIONS_BUTTON_X_FOR_INFORMATION_WINDOW = "id:places auth close";
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField";
        OPTIONS_BUTTON_CLEAR_MINI = "id:clear mini";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
