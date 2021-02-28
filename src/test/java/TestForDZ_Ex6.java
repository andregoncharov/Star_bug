import io.appium.java_client.AppiumDriver;
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
import java.util.List;

public class TestForDZ_Ex6
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
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void waitTitleNotTimeout() {
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

        String element_found = "//android.view.View[@content-desc='Nokia']";

        waitForElementPresent(
                By.xpath(element_found),
                "Cannot find article title by the request " + name_of_search,
                0
        );

        assertElementPresent(
                By.xpath(element_found),
                "We've found some results by request " + name_of_search
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

        private int getAAmountOfElements(By by)
        {
            List elements = driver.findElements(by);
            return elements.size();
        }

        private void assertElementPresent(By by, String error_message)
        {
            int amount_of_elements = getAAmountOfElements(by);
            if (amount_of_elements == 0) {
               String default_message = "An element '" + by.toString() + "' supposed to be not present";
               throw new AssertionError(default_message + " " + error_message);
        }
    }
}
