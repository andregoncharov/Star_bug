package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
    TITLE_TPL,
    TITLE,
    FOOTER_ELEMENT,
    OPTIONS_BUTTON_BOOKMARK,
    OPTIONS_ADD_TO_MY_LIST_BUTTON,
    OPTIONS_BUTTON_CREATE,
    OPTIONS_SAVES_TO_MY_LIST_BUTTON_TPL,
    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
    MY_LIST_NAME_INPUT,
    MY_LIST_OK_BUTTON,
    CLOSE_ARTICLE_BUTTON1,
    CLOSE_ARTICLE_BUTTON2,
    CLEAR_X_BUTTON,
    OPTIONS_BUTTON_X_FOR_INFORMATION_WINDOW,
    SEARCH_INIT_ELEMENT,
    SEARCH_INPUT,
    OPTIONS_BUTTON_CLEAR_MINI;


    private static String getSavedFolderXpath(String name_of_folder)
    {
        return OPTIONS_SAVES_TO_MY_LIST_BUTTON_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }

    private static String getElementFound(String element_found)
    {
        return TITLE_TPL.replace("{TITLE}", element_found);
    }

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!",5);
    }

    public WebElement waitForTitleElement1()
    {
        return this.waitForElementPresent(TITLE_TPL, "Cannot find article title on page!",5);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement1();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("content-desc");
        } else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find end of article", 40);
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find end of article", 40); }
          else { this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(OPTIONS_BUTTON_BOOKMARK, "Cannot find button to bookmark", 5);
        this.waitForElementAndClick(OPTIONS_BUTTON_BOOKMARK, "Cannot find button to bookmark", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add another reading list", 5);
        this.waitForElementAndClick(OPTIONS_BUTTON_CREATE, "Cannot find 'Create button' tip overlay", 5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder, "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot press OK button", 5);
    }

    public void addArticleMyList() {
        this.waitForElementAndClick(OPTIONS_BUTTON_BOOKMARK, "Cannot find button to bookmark", 5);
        this.waitForElementAndClick(OPTIONS_BUTTON_BOOKMARK, "Cannot find button to bookmark", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add another reading list", 5);
    }

    public void savedArticleToMyList(String name_of_folder)
    {
        String folder_name = getSavedFolderXpath(name_of_folder);
        this.waitForElementAndClick (folder_name, "Cannot find 'Create button' tip overlay", 5);
    }

    public void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
            this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find to option to add article to reading list", 5);
        }
        //this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find to option to add article to reading list", 5);
        this.waitForElementAndClick(OPTIONS_BUTTON_BOOKMARK, "Cannot find options to add article to reading list", 5);
        this.waitForElementAndClick(OPTIONS_BUTTON_X_FOR_INFORMATION_WINDOW,"Cannot find options to add article to information window", 5);
    }

    public void removeArticleFromSavedIfItAdded ()
    {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot click button to remove an article from saved", 5);

            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button to add an article to saved list after removing it from this list before", 5);

        }
    }

    public void clickStar() {

        this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button to add an article to saved list after removing it from this list before", 10);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find to option to add article to reading list", 10);

    }

    public void addArticlesToMySavedNotInformationWindow()
    {

        this.waitForElementAndClick(OPTIONS_BUTTON_BOOKMARK, "Cannot find options to add article to reading list", 5);
    }

    public void closeArticle()
    {
        if (Platform.getInstance().isMW())
            return;

        //       System.out.println("Method closeArticle() do noting for platform " + Platform.getInstance().getPlatformVar());

        if (Platform.getInstance().isAndroid()){
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON1, "Cannot find button to Navigate up", 5);
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON2, "Cannot find button to Navigate up", 5);
        } else {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON1, "Cannot find button to Navigate up", 5);
        }
    }

    public void closeArticleAndClearSearchLine()
    {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON1, "Cannot find button to Navigate up", 5);
            this.waitForElementAndClick(CLEAR_X_BUTTON, "Cannot find button to X button", 5);
        } else {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON1, "Cannot find button to Navigate up", 5);
            this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init element",5);
            this.waitForElementPresent(SEARCH_INPUT,"Cannot find search input after clicking search init element",5);
            this.waitForElementAndClick(OPTIONS_BUTTON_CLEAR_MINI,"Cannot find search and click button clear mini",5);

        }
    }

    public int getAAmountOfElements(String element_found)
    {
        String element_by_found = getElementFound(element_found);
        List elements = driver.findElements(By.xpath(element_by_found));
        return elements.size();
    }

    public void assertElementPresent(String element_found)
    {
        int amount_of_elements = getAAmountOfElements(element_found);
        if (amount_of_elements == 0) {
            String default_message = "An element '" + element_found + "' supposed to be not present";
            throw new AssertionError(default_message );
        }
    }
    //" + by.toString() + "
}
