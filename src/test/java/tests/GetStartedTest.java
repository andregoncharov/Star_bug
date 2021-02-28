package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for Get started")
public class GetStartedTest extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="Welcome")})
    @DisplayName("Go through the welcome screen")
    @Description("Open the application and go through welcome")
    @Step("Starting test PassThoughWelcome")
    @Severity(value = SeverityLevel.MINOR)

    public void testPassThoughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW()))
        {
            return;
        }

        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddEditPreferredLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();

    }

}
