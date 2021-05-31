package tests.iOS;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
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
