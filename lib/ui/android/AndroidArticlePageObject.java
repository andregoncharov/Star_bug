package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {

        TITLE_TPL = "xpath://android.view.View[@content-desc='HTC']";
                TITLE = "xpath://android.view.View[@content-desc='Nokia']";
                FOOTER_ELEMENT = "xpath://android.view.View[@content-desc='View article in browser']";
                OPTIONS_BUTTON_BOOKMARK = "id:org.wikipedia.beta:id/article_menu_bookmark";
                OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@resource-id='org.wikipedia.beta:id/content']//*[@text='Add to another reading list']";
                OPTIONS_BUTTON_CREATE = "id:org.wikipedia.beta:id/create_button";
                OPTIONS_SAVES_TO_MY_LIST_BUTTON_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/lists_container']//*[@text='{FOLDER_NAME}']";
                MY_LIST_NAME_INPUT = "id:org.wikipedia.beta:id/text_input";
                MY_LIST_OK_BUTTON = "id:android:id/button1";
                CLOSE_ARTICLE_BUTTON1 = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                CLOSE_ARTICLE_BUTTON2 = "xpath://android.widget.ImageButton";
                CLEAR_X_BUTTON = "id:org.wikipedia.beta:id/search_close_btn";
    }
    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
