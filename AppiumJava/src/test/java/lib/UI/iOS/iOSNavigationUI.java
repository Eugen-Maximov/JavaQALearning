package lib.UI.iOS;

import lib.UI.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {
    static {
        NAVIGATION_BAR = "id:org.wikipedia:id/fragment_main_nav_tab_layout";
        MY_LISTS_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public iOSNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
