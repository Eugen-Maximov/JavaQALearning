package lib.UI.iOS;

import lib.UI.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {
        static {
            FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='{FOLDER_NAME}']";
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        }


    public iOSMyListsPageObject(RemoteWebDriver driver){
            super(driver);
        }
}
