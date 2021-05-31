package lib.UI.factories;

import lib.Platform;
import lib.UI.Android.AndroidArticlePageObject;
import lib.UI.ArticlePageObject;
import lib.UI.iOS.iOSArticlePageObject;
import lib.UI.mobie_web.MWArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else if (Platform.getInstance().isIOS()){
            return new iOSArticlePageObject(driver);
        } else {
            return new MWArticlePageObject(driver);
        }
    }
}