package MainClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
/**     * This reusable class MainClass_BDD is used to handle the BDD(Behaviour Driven Development) consumption    */
public class MainClass_BDD {
    public static WebDriver wdriver;
    protected static Logger log = LogManager.getLogger();

    // Default constructor
    public MainClass_BDD() {
    }

    // Method to convert a relative path to an absolute path
    public static String toAbsolutePath(String relativePath) {
        Path relPath = Paths.get(relativePath);
        Path absolutePath = null;
        if (!relPath.isAbsolute()) {
            Path base = Paths.get("");
            absolutePath = base.resolve(relPath).toAbsolutePath();
        }
        return absolutePath.normalize().toString();
    }

    // Method to launch the browser based on configuration properties
    public void launchBrowser() throws MalformedURLException {
        String environment = this.getConfigPropertyValue("src/test/java/resources/execution.properties", "environment");
        String browser = this.getConfigPropertyValue("src/test/java/resources/execution.properties", "Browser");
        String baseUrl = null;

        if (environment.equals("dev")) {
            baseUrl = this.getConfigPropertyValue("src/test/java/resources/execution.properties", "DevUrl");
        } else if (environment.equals("qa")) {
            baseUrl = this.getConfigPropertyValue("src/test/java/resources/execution.properties", "QaUrl");
        }

        if (baseUrl == null) {
            throw new IllegalArgumentException("Base URL is not set for the environment: " + environment);
        }

        this.setupWebDriver(browser);
        wdriver.get(baseUrl);
        log.info("Launched browser and Entered Url : " + baseUrl);
        wdriver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    }

    // Method to close the browser
    public void closeBrowser() {
        wdriver.close();
        log.info("Closed browser");
    }

    // Method to set up the WebDriver based on the browser name
    public WebDriver setupWebDriver(String browserName) throws MalformedURLException {
        String macDriverLocation = "./src/test/java/resources/Webdrivers/Mac/";
        String linuxDriverLocation = "./src/test/resources/Webdrivers/Linux/";
        String windowsDriverLocation = "./src/test/java/resources/Webdrivers/Windows/";
        Boolean isHeadless = Boolean.valueOf(this.getConfigPropertyValue("src/test/resources/execution.properties", "IsHeadless"));
        log.info("Browser name : " + browserName);
        String ieDriverPath;
        String absoluteIeDriverPath;

        if (browserName.equalsIgnoreCase("chrome")) {
            ieDriverPath = null;
            if (this.getOsName().equalsIgnoreCase("Windows")) {
                ieDriverPath = windowsDriverLocation + "chromedriver.exe";
            } else if (this.getOsName().equalsIgnoreCase("Mac OS")) {
                ieDriverPath = macDriverLocation + "chromedriver";
            } else if (this.getOsName().equalsIgnoreCase("Linux")) {
                ieDriverPath = linuxDriverLocation + "chromedriver";
            }
            log.info("This is the chrome driver path is :::: " + ieDriverPath);
            absoluteIeDriverPath = toAbsolutePath(ieDriverPath);
            log.info("This is the chrome driver real path is :::: " + absoluteIeDriverPath);
            System.setProperty("webdriver.chrome.driver", absoluteIeDriverPath);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            if (isHeadless) {
                chromeOptions.addArguments("--headless");
            }
            chromeOptions.addArguments("Zoom 50%");
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("disable-infobars");
            wdriver = new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            ieDriverPath = null;
            log.info("Firefox ?: " + browserName);
            if (this.getOsName().equalsIgnoreCase("Windows")) {
                ieDriverPath = windowsDriverLocation + "geckodriver.exe";
            } else if (this.getOsName().equalsIgnoreCase("Mac OS")) {
                ieDriverPath = macDriverLocation + "geckodriver";
            } else if (this.getOsName().equalsIgnoreCase("Linux")) {
                ieDriverPath = linuxDriverLocation + "geckodriver";
            }
            log.info("This is the firefox driver path is :::: " + ieDriverPath);
            absoluteIeDriverPath = toAbsolutePath(ieDriverPath);
            log.info("This is the gecko driver real path is :::: " + absoluteIeDriverPath);
            System.setProperty("webdriver.gecko.driver", absoluteIeDriverPath);
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setProfile(new FirefoxProfile());
            firefoxOptions.addPreference("dom.webnotifications.enabled", false);
            firefoxOptions.addArguments(new String[]{"--disable-notifications"});
            wdriver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equalsIgnoreCase("edge")) {
            ieDriverPath = null;
            log.info("edge ?: " + browserName);
            if (this.getOsName().equalsIgnoreCase("Windows")) {
                ieDriverPath = windowsDriverLocation + "msedgedriver.exe";
            } else if (this.getOsName().equalsIgnoreCase("Mac OS")) {
                ieDriverPath = macDriverLocation + "msedgedriver";
            } else if (this.getOsName().equalsIgnoreCase("Linux")) {
                ieDriverPath = linuxDriverLocation + "msedgedriver";
            }
            log.info("This is the edge driver path is :::: " + ieDriverPath);
            absoluteIeDriverPath = toAbsolutePath(ieDriverPath);
            log.info("This is the edge driver real path is :::: " + absoluteIeDriverPath);
            System.setProperty("webdriver.edge.driver", absoluteIeDriverPath);
            wdriver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("IE")) {
            ieDriverPath = null;
            if (this.getOsName().equalsIgnoreCase("Windows")) {
                ieDriverPath = windowsDriverLocation + "IEDriverServer.exe";
            } else if (this.getOsName().equalsIgnoreCase("Mac OS")) {
                ieDriverPath = macDriverLocation + "IEDriverServer";
            } else if (this.getOsName().equalsIgnoreCase("Linux")) {
                ieDriverPath = linuxDriverLocation + "IEDriverServer";
            }
            log.info("This is the chrome driver path is :::: " + ieDriverPath);
            absoluteIeDriverPath = toAbsolutePath(ieDriverPath);
            log.info("This is the chrome driver real path is :::: " + absoluteIeDriverPath);
            System.setProperty("webdriver.ie.driver", absoluteIeDriverPath);
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            ieOptions.setCapability("ignoreProtectedModeSettings", true);
            ieOptions.setCapability("enablePersistentHover", false);
            ieOptions.setCapability("requireWindowFocus", true);
            wdriver = new InternetExplorerDriver(ieOptions);
            System.setProperty("webdriver.ie.driver", absoluteIeDriverPath);
            wdriver = new InternetExplorerDriver();
        } else if (browserName.equalsIgnoreCase("grid")) {
            ChromeOptions gridOptions = new ChromeOptions();
            gridOptions.setCapability("version", "90.0.4430.85");
            gridOptions.setCapability("debug", true);
            gridOptions.addArguments(new String[]{"--disable-dev-shm-usage"});
            gridOptions.setAcceptInsecureCerts(true);
            wdriver = new RemoteWebDriver(new URL("http://selenium.devops.nednet.co.za/wd/hub"), gridOptions);
        }

        wdriver.manage().window().maximize();
        return wdriver;
    }

    // Method to get the operating system name
    public String getOsName() {
        String osName = "";
        String osType = System.getProperty("os.name");
        if (!osType.contains("Windows") && !osType.contains("windows")) {
            if (!osType.contains("Mac") && !osType.contains("mac")) {
                if (osType.contains("Linux") || osType.contains("linux")) {
                    osName = "Linux";
                }
            } else {
                osName = "Mac OS";
            }
        } else {
            osName = "Windows";
        }
        log.info("os Type is ::: " + osType + "found os Name ::: " + osName);
        return osName;
    }

    // Method to get a configuration property value from a properties file
    public String getConfigPropertyValue(String propertyFileName, String propertyName) {
        String Value = null;
        try {
            FileInputStream fileIS = new FileInputStream(new File(propertyFileName));
            Properties prop = new Properties();
            prop.load(fileIS);
            Value = prop.getProperty(propertyName);
        } catch (IOException var6) {
            log.info(var6.getStackTrace());
        }
        return Value;
    }
}