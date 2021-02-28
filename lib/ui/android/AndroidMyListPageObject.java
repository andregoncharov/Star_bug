package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject
{
    static
    {
        FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/reading_list_list']//*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/reading_list_contents']//*[@text='{TITLE}']";
        BUTTON_FILTER_MY_LIST = "xpath://android.widget.TextView[@content-desc='Filter my lists']";
        SEARCH_INPUT_FOR_MY_LIST = "id:org.wikipedia.beta:id/search_src_text";
        SAVED_ARTICLE = "xpath://*[@resource-id='org.wikipedia.beta:id/reading_list_contents']/*[@class='android.view.ViewGroup']";
    }
    public AndroidMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
