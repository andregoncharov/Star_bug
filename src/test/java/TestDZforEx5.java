import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class TestDZforEx5 {
    private AppiumDriver driver;
    @Before
    public void setUp()throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia.beta");
        capabilities.setCapability("appActivity","org.wikipedia.main.MainActivity");
        capabilities.setCapability("app","C:/Users/Andrey_G/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }


    @Test
    public void saveSecondArticleToMyList() {

        //Добовляем первую статью
        waitForElementAndClick(
                By.id("org.wikipedia.beta:id/fragment_onboarding_skip_button"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String name_of_search = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia.beta:id/search_src_text"),
                name_of_search,
                "Cannot find search input",
                10
        );

        String element_search_1 = "//*[@resource-id='org.wikipedia.beta:id/search_results_list']//*[@text='Java (programming language)']";

        waitForElementAndClick(
                By.xpath(element_search_1),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String element_empty = "//android.view.View[@content-desc='Java (programming language)']";
        waitForElementPresent(
                By.xpath(element_empty),
                "Cannot find article title",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia.beta:id/article_menu_bookmark"),
                "Cannot find button to open article options",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        //Добовляем вторую статью
        String element_search_2 = "//*[@resource-id='org.wikipedia.beta:id/search_results_list']//*[@text='Java']";

        waitForElementAndClick(
                By.xpath(element_search_2),
                "Cannot find option to add article to reading list",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia.beta:id/article_menu_bookmark"),
                "Cannot find button to open article options",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        //Переходим во вкладку сохраненные
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia.beta:id/reading_list_list']//*[@text='Saved']"),
                "Cannot find option to add article to reading list",
                5
        );
       /* swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia.beta:id/reading_list_contents']//*[@text='Java']"),
                "Cannot find saved article"

        );*/
        //В данном месте у меня возникла дилемма, так как вопрос звучит не
        String saved_result_locator = "//*[@resource-id='org.wikipedia.beta:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(saved_result_locator),
                "Cannot find anything by the request " + saved_result_locator,
                15
        );

        int amount_of_saved_results = getAAmountOfElements(
                By.xpath(saved_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_saved_results == 1
        );


        // waitForElementPresent(
        //          By.xpath("//*[@resource-id='org.wikipedia.beta:id/reading_list_contents']//*[@text='Java (programming language)']"),
        //          "Cannot find article title",
        //          5
        //  );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia.beta:id/reading_list_contents']//*[@text='Java (programming language)']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementPresent(
                By.xpath(element_empty),
                "Cannot find article title",
                5
        );




    }


        private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");
            return  wait.until(
                    ExpectedConditions.presenceOfElementLocated(by)
            );
        }

        private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
        {
            WebElement element =  waitForElementPresent(by, error_message, timeoutInSeconds);
            element.click();
            return element;
        }

        private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
        {
            WebElement element =  waitForElementPresent(by, error_message, timeoutInSeconds);
            element.sendKeys(value);
            return element;
        }

        private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
            element.clear();
            return element;
        }

        private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(by)
            );
        }

        /*protected void swipeUp(int timeOfSwipe)
        {
            TouchAction action = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action
                    .press(x, start_y)
                    .waitAction(timeOfSwipe)
                    .moveTo(x, end_y)
                    .release()
                    .perform();
        }*/

       /* protected void swipeUpQuick()
        {
            swipeUp(200);
        }

        protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
        {
            int already_swiped = 0;
            while (driver.findElements(by).size() == 0){

                if (already_swiped > max_swipes){
                    waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                    return;
                }
                swipeUpQuick();
                ++already_swiped;
            }
        }*/

        /*protected void swipeElementToLeft(By by, String error_message)
        {
            WebElement element = waitForElementPresent(
                    by,
                    error_message,
                    5);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction(driver);
            action
                    .press(right_x, middle_y)
                    .waitAction(150)
                    .moveTo(left_x, middle_y)
                    .release()
                    .perform();
        }*/

    private int getAAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }


    

}
