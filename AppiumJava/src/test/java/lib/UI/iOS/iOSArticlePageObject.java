package lib.UI.iOS;

import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.TextView[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        INPUT_FIELD = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "xpath://android.widget.Button[@text='OK']";
        CLOSE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public iOSArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
