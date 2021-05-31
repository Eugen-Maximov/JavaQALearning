package lib.UI.mobie_web;

import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
                TITLE = "css:#content h1";
                FOOTER_ELEMENT = "css:footer";
                OPTION_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
                OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "ccs:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
