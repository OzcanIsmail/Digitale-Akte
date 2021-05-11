package de.digitale.akte.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MyDriver {

    static Logger logger = LoggerFactory.getLogger(MyDriver.class);
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    // InheritableThreadLocal  --> this is like a container, bag, pool.
    // in this pool we can have separate objects for each thread
    // for each thread, in InheritableThreadLocal we can have separate object for that thread
    // driver class will provide separate webdriver object per thread

    private MyDriver() {
    }

    public static WebDriver get() {
        // if this thread doesn't have driver - create it and add to pool
        if (driverPool.get() == null) {
            // if we pass the driver from terminal then use that one
            // if we do not pass the driver from terminal then use the one properties file

            logger.info("===============================================================");
            logger.info("|          Environment : " + ConfigurationReader.getProperty("env"));
            logger.info("|          Operating System : " + System.getProperty("os.name"));
            logger.info("|          Browser: " + ConfigurationReader.getProperty("browser"));
            logger.info("===============================================================\n");

            String browser =
                    System.getProperty("browser") != null
                            ? browser = System.getProperty("browser")
                            : ConfigurationReader.getProperty("browser");

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    // In case of any locate problem with headless chrome
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
                    //Run IExplorer 32 and to override some preinstallations of administrator
                    //System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/IEDriverServer32.exe");
                    InternetExplorerOptions capabilities = new InternetExplorerOptions();
                    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    capabilities.setCapability("requireWindowFocus", true);
                    capabilities.setCapability("browserstack.sendKeys", true);
                    capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                    File file = new File(System.getProperty("user.dir") + "/IEDriverServer32.exe");
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
                case "remote_chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability("platform", Platform.ANY);
                    try {
                        // driverPool.set(new RemoteWebDriver(new
                        // URL("https://Isso-Chan:fb6d681b-8abe-498e-8046-f3e5f6eff650@ondemand.us-west-1.saucelabs.com:443/wd/hub"),chromeOptions));
                        // For SauceLab  or Browserstack cloud implementations: just change the link above!
                        driverPool.set(
                                new RemoteWebDriver(
                                        new URL("http://localhost:4444/wd/hub"),
                                        chromeOptions)); // For localhost on our own computer
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
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
