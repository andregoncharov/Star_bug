package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_SKIP_BUTTON = "id:org.wikipedia.beta:id/fragment_onboarding_skip_button";
                SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
                SEARCH_INPUT = "id:org.wikipedia.beta:id/search_src_text";
                SEARCH_NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton";
                SEARCH_X_BUTTON = "id:org.wikipedia.beta:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/search_results_container']//*[contains(@text,'{SUBSTRING}')]";
                SEARCH_RESULT_BY_SUBSTRING1_TPL = "xpath://*[@resource-id='org.wikipedia.beta:id/search_results_list']//*[contains(@text,'{SUBSTRING}')]";
                SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia.beta:id/search_results_list']/*[@class='android.view.ViewGroup']";
                SEARCH_EMPTY_RESULT_ELEMENT = "id:org.wikipedia.beta:id/results_text";
    }
    public AndroidSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
