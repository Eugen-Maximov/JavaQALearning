package lib.UI;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject
{
    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }


    private static final String
            NAVIGATION_BAR = "id:org.wikipedia:id/fragment_main_nav_tab_layout",
            MY_LISTS_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']";


    public void clickMyLists()
    {
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
