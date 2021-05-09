package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject
{
    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }


    private static final String
            NAVIGATION_BAR = "org.wikipedia:id/fragment_main_nav_tab_layout",
            MY_LISTS_BUTTON = "//android.widget.FrameLayout[@content-desc='My lists']";


    public void clickMyLists()
    {
        this.waitForRender(
                By.id(NAVIGATION_BAR),
                "Cannot render navigation bar",
                10
        );
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_BUTTON),
                "Cannot find My Lists navigation button",
                10
        );
    }
}
