package lib.UI;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject
{
    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }


    protected static String
            NAVIGATION_BAR,
            MY_LISTS_BUTTON,
            OPEN_NAVIGATION;


    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempt(MY_LISTS_BUTTON, "Cannot find My Lists navigation button", 10);
        } else {
            this.waitForRender(
                    NAVIGATION_BAR,
                    "Cannot render navigation bar",
                    10
            );
            this.waitForElementAndClick(
                    MY_LISTS_BUTTON,
                    "Cannot find My Lists navigation button",
                    10
            );
        }
    }

    public void openNavigation(){
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation btn", 5);
        } else {
            System.out.println("Method rotate isn`t work on platform: " + Platform.getInstance().getPlatformVar());
        }
    }
}
