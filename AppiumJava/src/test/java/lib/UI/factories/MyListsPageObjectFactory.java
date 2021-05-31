package lib.UI.factories;

import lib.Platform;
import lib.UI.Android.AndroidMyListsPageObject;
import lib.UI.MyListsPageObject;
import lib.UI.iOS.iOSMyListsPageObject;
import lib.UI.mobie_web.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory{

    public static MyListsPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else if (Platform.getInstance().isIOS()){
            return new iOSMyListsPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }
}
