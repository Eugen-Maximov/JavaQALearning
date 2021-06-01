package tests.iOS;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.WelcomePageObject;
import org.junit.Test;

@Epic("GetStarted tests")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Feature(value="Welcome Screens")
    @DisplayName("Chek Welcome screens")
    @Description("Check welcome screens and pass them")
    @Step("Starting testPassThroughWelcome")
    @Severity(value = SeverityLevel.NORMAL)
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid()){
            return;
        } else if (Platform.getInstance().isMW()){
            return;
        }
        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForNewWayToExploreText();
        welcomePage.clickNextButton();

        welcomePage.waitForAddOrEditPreferredLang();
        welcomePage.clickNextButton();

        welcomePage.waitForLearnMoreAboutDataCollected();
        welcomePage.clickGetStartedButton();
    }
}
