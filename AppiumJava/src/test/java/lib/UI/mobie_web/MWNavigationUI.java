package lib.UI.mobie_web;

import lib.UI.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        MY_LISTS_BUTTON = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "css:#mv-mf-main-menu-button";
    }

    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
