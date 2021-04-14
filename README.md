JavaQALearning
=====================

Need to install and Configure: Windows + Android Devices:
-----------------------------------

#### Proggrams:
1. [IntelliJ Idea](https://download-cf.jetbrains.com/idea/ideaIC-2021.1.exe)
2. [JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
3. [Android Studio + Android SDK + Android Emulators](https://r2---sn-pivhx-n8vl.gvt1.com/edgedl/android/studio/install/4.1.3.0/android-studio-ide-201.7199119-windows.exe?cms_redirect=yes&mh=es&mip=109.236.65.98&mm=28&mn=sn-pivhx-n8vl&ms=nvh&mt=1618396932&mv=u&mvi=2&pl=22&shardbypass=yes)
4. [Appium Desktop 1.5](https://github.com/appium/appium-desktop/releases/tag/v1.5.0)

#### Environment Variables:
* ANDROID_HOME - path to AndroidSDK
* JAVA_HOME - path to JDK
* Path:
	 - %ANDROID_HOME%\tools
	 - %ANDROID_HOME%\tools\bin
	 - %ANDROID_HOME%\platform-tools
	 - %ANDROID_HOME%\emulator
	 - path to Oracle\Java\javapath

#### Commands:
* adb - Android Debug Bridge
* uiautomatorviewer - Ui Automator Viewer
* adb shell package - view installed packages on device
* adb shell dumpsys window windows >> Desktop\activity.txt - view device activity

### Appium Capabilities(example):
* "platformName": "Android"
* "deviceName": "AndroidTestDevice"
* "platformVersion": "10"
* "appPackage": "org.wikipedia",
* "appActivity": ".main.MainActivity"

### Android Device:
* Enable developer mode
* Allow USB Debugging
* Allow installation via USB




>Study project for the [course](https://www.learnqa.ru/java)
