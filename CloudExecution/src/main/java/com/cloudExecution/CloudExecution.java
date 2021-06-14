package com.cloudExecution;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CloudExecution {
	public static Boolean BROWSERSTACK_LOCAL=false;
    public static String BROWSERSTACK_LOCAL_IDENTIFIER="";
    public static  String URL = "";
    public static WebDriver driver;
   	public static WebDriver browserStackSetUp(String USERNAME,String AUTOMATE_KEY,String os,String os_version ,String browserName,String browser_version,String TestName) {
				DesiredCapabilities caps = new DesiredCapabilities();
				BROWSERSTACK_LOCAL= false;
			    BROWSERSTACK_LOCAL_IDENTIFIER= "identifier";
				URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
				caps.setCapability("os", os);
				caps.setCapability("os_version", os_version);
				caps.setCapability("browser_version", browser_version);
				caps.setCapability("name", TestName);
				caps.setCapability("browserstack.console", "errors");
				caps.setCapability("browserstack.debug", "true");
				caps.setCapability("browserstack.local", BROWSERSTACK_LOCAL);
				caps.setCapability("browserstack.localIdentifier", BROWSERSTACK_LOCAL_IDENTIFIER);
				if ((browserName).toUpperCase().equals("CHROME")) {
					WebDriverManager.chromedriver().setup();
					caps.setCapability("browser", "Chrome");
				} else if ((browserName).toUpperCase().equals("FIREFOX")) {
					WebDriverManager.firefoxdriver().setup();
					caps.setCapability("browser", "Firefox");
				} else if ((browserName).toUpperCase().equals("IE")) {
					WebDriverManager.iedriver().setup();
					caps.setCapability("browser", "IE");
					
				}
				
				try {
					
					//driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"), caps);
					driver = new RemoteWebDriver(new URL(URL), caps);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					
				} catch (MalformedURLException e) {
					//markTestStatus("failed",e.toString());
					e.printStackTrace();
					
				}
				return driver;
			
		}
   	public static WebDriver SauceLabSetUp(String USERNAME,String ACCESS_KEY,String platform ,String URL,String browserName,String browser_version,String testName) {
   		DesiredCapabilities caps = new DesiredCapabilities();
   		MutableCapabilities sauceOpts = new MutableCapabilities();
		sauceOpts.setCapability("name", testName);
		//sauceOpts.setCapability("build", "");
		//sauceOpts.setCapability("seleniumVersion", "3.141.59");
		sauceOpts.setCapability("username", USERNAME);
		sauceOpts.setCapability("accessKey", ACCESS_KEY);
		//sauceOpts.setCapability("tags", "");
		caps.setCapability("sauce:options", sauceOpts);
		caps.setCapability("browserVersion", browser_version);
		caps.setCapability("platformName", platform);
		if ((browserName).toUpperCase().equals("CHROME")) {
			WebDriverManager.chromedriver().setup();
			caps.setCapability("browserName", "Chrome");
		} else if ((browserName).toUpperCase().equals(("FIREFOX"))) {
			WebDriverManager.firefoxdriver().setup();
			caps.setCapability("browserName", "Firefox");
		}else if ((browserName).toUpperCase().equals("IE")) {
			WebDriverManager.iedriver().setup();
			caps.setCapability("browser", "IE");
			
		}
	try {
				
			//driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"), caps);
			driver = new RemoteWebDriver(new URL(URL), caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		} catch (MalformedURLException e) {
			//markTestStatus("failed",e.toString());
			e.printStackTrace();
			
		}
		return driver;
   	}
}
