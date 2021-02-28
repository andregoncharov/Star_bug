import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class TestForDZ_Ex7
{
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
        driver.rotate(ScreenOrientation.PORTRAIT);
        //Мы просто добовляем в аннотацию @Before предварительную ротацию в портретный режим, а потом уже запускаем тест.
        //Если у нас в памяти Appium сохранился поворот экрана при падении теста, то данное действие исправит это и тест
        //пойдёт запланированно.
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
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

        String name_of_search = "Nokia";

        waitForElementAndSendKeys(
                By.id("org.wikipedia.beta:id/search_src_text"),
                name_of_search,
                "Cannot find search input",
                10
        );

        String element_search_1 = "//*[@resource-id='org.wikipedia.beta:id/search_results_container']//*[@text='Nokia']";

        waitForElementAndClick(
                By.xpath(element_search_1),
                "Cannot find in search results",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String element_found = "//android.view.View[@content-desc='Nokio']";
        //Создаём ситуацию падения теста
        waitForElementPresent(
                By.xpath(element_found),
                "Cannot find article title by the request " + name_of_search,
                0
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        waitForElementPresent(
                By.xpath(element_found),
                "Cannot find article title by the request " + name_of_search,
                0
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

}
