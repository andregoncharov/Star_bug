import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class TestForDZ_Ex5 {
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
    public void saveSecondArticleToMyList()
    {
        //Добовляем первую статью
        waitForElementAndClick(
                By.id("org.wikipedia.beta:id/fragment_onboarding_skip_button"),
                "Cannot find 'SKIP button' input",
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

        String element_found = "//android.view.View[@content-desc='Java (programming language)']";

        waitForElementPresent(
                By.xpath(element_found),
                "Cannot find article title",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia.beta:id/article_menu_bookmark"),
                "Cannot find button to bookmark",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find button to Navigate up",
                5
        );

        //Добовляем вторую статью
        String element_search_2 = "//*[@resource-id='org.wikipedia.beta:id/search_results_list']//*[@text='Java']";

        waitForElementAndClick(
                By.xpath(element_search_2),
                "Cannot find in search results",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia.beta:id/article_menu_bookmark"),
                "Cannot find button to bookmark",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find button to Navigate up",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton"),
                "Cannot find button to Navigate up",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find button to My lists",
                10
        );

        //Переходим во вкладку сохраненные
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia.beta:id/reading_list_list']//*[@text='Saved']"),
                "Cannot find tab Saved",
                10
        );

       /* swipeElementToLeft(
               By.xpath("//*[@resource-id='org.wikipedia.beta:id/reading_list_contents']//*[@text='Java']"),
               "Cannot find saved article"
        );*/

        waitForElementPresent(
               By.xpath("//*[@resource-id='org.wikipedia.beta:id/reading_list_contents']//*[@text='Java (programming language)']"),
               "Cannot find article title",
               5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia.beta:id/reading_list_contents']//*[@text='Java (programming language)']"),
                "Cannot find article title",
                5
        );

        waitForElementPresent(
                By.xpath(element_found),
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
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }*/

}
