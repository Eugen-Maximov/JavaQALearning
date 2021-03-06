package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.Android.AndroidSearchPageObject;
import lib.UI.SearchPageObject;
import lib.UI.iOS.iOSSearchPageObject;
import lib.UI.mobie_web.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {
    public static SearchPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else if (Platform.getInstance().isIOS()){
            return new iOSSearchPageObject(driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }
}
