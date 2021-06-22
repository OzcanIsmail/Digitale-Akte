package de.digitale.akte.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDriver {

  static Logger logger = LoggerFactory.getLogger(MyDriver.class);
  private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

  // InheritableThreadLocal  --> this is like a container, bag, pool.
  // in this pool we can have separate objects for each thread
  // for each thread, in InheritableThreadLocal we can have separate object for that thread
  // driver class will provide separate webdriver object per thread

  private MyDriver() {}

  public static WebDriver get() {
    // if this thread doesn't have driver - create it and add to pool
    if (driverPool.get() == null) {
      // if we pass the driver from terminal then use that one
      // if we do not pass the driver from terminal then use the one properties file

      logger.info("===============================================================");
//      logger.info("|          Environment : " + ConfigurationReader.getProperty("env"));
      logger.info("|          Operating System : " + System.getProperty("os.name"));
//      logger.info("|          Browser: " + ConfigurationReader.getProperty("browser"));
      logger.info("===============================================================\n");

      String browser =
          System.getProperty("browser") != null
              ? browser = System.getProperty("browser")
              : "ie"; //ConfigurationReader.getProperty("browser");

      switch (browser) {
        case "chrome":
          WebDriverManager.chromedriver().setup();
          driverPool.set(new ChromeDriver());
          break;
        case "chrome-headless":
          WebDriverManager.chromedriver().setup();
          // In case of any locating problem with headless chrome
          ChromeOptions options = new ChromeOptions();
          options.setHeadless(true);
          options.addArguments("--window-size=1920,1080");
          options.addArguments("--disable-gpu");
          options.addArguments("--disable-extensions");
          options.setExperimentalOption("useAutomationExtension", false);
          options.addArguments("--proxy-server='direct://'");
          options.addArguments("--proxy-bypass-list=*");
          options.addArguments("--start-maximized");
          options.addArguments("--headless");
          driverPool.set(new ChromeDriver(options));
          break;
        case "firefox":
          WebDriverManager.firefoxdriver().setup();
          driverPool.set(new FirefoxDriver());
          break;
        case "firefox-headless":
          WebDriverManager.firefoxdriver().setup();
          driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
          break;
        case "ie":
          if (!System.getProperty("os.name").toLowerCase().contains("windows"))
            throw new WebDriverException("Your OS doesn't support Internet Explorer");
          // Run IExplorer 32 and to override some preinstallations of administrator
          InternetExplorerOptions capabilities = new InternetExplorerOptions();
          capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
          capabilities.setCapability("requireWindowFocus", true);
          capabilities.setCapability("browserstack.sendKeys", true);
          capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
          capabilities.setCapability("ignoreProtectedModeSettings",true);
          capabilities.setCapability("ignoreZoomSetting",true);
          capabilities.setCapability("nativeEvents",false);
          capabilities.setCapability("acceptSslCerts",true);
          File file = new File(System.getProperty("user.dir") + "\\IEDriverServer64.exe");
          WebDriverManager.iedriver().setup();
          driverPool.set(new InternetExplorerDriver(capabilities));
          break;
        case "edge":
          if (!System.getProperty("os.name").toLowerCase().contains("windows"))
            throw new WebDriverException("Your OS doesn't support Edge");
          WebDriverManager.edgedriver().setup();
          driverPool.set(new EdgeDriver());
          break;
        case "safari":
          if (!System.getProperty("os.name").toLowerCase().contains("mac"))
            throw new WebDriverException("Your OS doesn't support Safari");
          WebDriverManager.getInstance(SafariDriver.class).setup();
          driverPool.set(new SafariDriver());
          break;
      }
    }
    return driverPool.get();
  }

  public static void closeDriver() {
    driverPool.get().quit();
    driverPool.remove();
  }
}
