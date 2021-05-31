package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android",
            PLATFORM_MOBILE_WEB = "mobile_web";
    private static String APPIUM_URL = "http://localhost:4723/wd/hub";

    private static Platform instance;

    private Platform(){}

    public static Platform getInstance(){
        if (instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception{
        URL url = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(url, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(url, this.getIOSDesiredCapabilities());
        } else if (this.isMW()){
            return new ChromeDriver(this.getMWChromeOptions());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW() {return  isPlatform(PLATFORM_MOBILE_WEB);}

    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "11");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        //capabilities.setCapability("app", "C:\\Users\\Eugen\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");//HomePC
        capabilities.setCapability("app","C:\\Users\\Eugen\\Documents\\GitHub\\JavaQALearning\\JavaAppiumAutomation\\apks\\org.wikipedia.apk"); //WorkPC
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "tests/iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "C:\\Users\\Eugen\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\Wikipedia.app");//HomePC
        //capabilities.setCapability("app","C:\\Users\\user1.DESKTOP-H3JEDUD\\Documents\\GitHub\\JavaQALearning\\JavaAppiumAutomation\\apks\\Wikipedia.app"); //WorkPC
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }

    public String getPlatformVar(){
        return System.getenv("PLATFORM");
    }

    private boolean isPlatform(String myPlatform){
        String platform = this.getPlatformVar();
        return myPlatform.equals(platform);
    }
}
