package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://localhost:4723/wd/hub";


    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Eugen\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");//HomePC
        //capabilities.setCapability("app","C:\\Users\\user1.DESKTOP-H3JEDUD\\Documents\\GitHub\\JavaQALearning\\JavaAppiumAutomation\\apks\\org.wikipedia.apk"); //WorkPC

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT); // вроде как самый простой вариант вернуть приложение в режим портрета
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();

        super.tearDown();
    }
}
