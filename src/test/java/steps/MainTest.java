package steps;

import util.WebEventListener;
import pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Przemek on 21.04.2017.
 */
public class MainTest {


    protected WebDriver driver;
    protected EventFiringWebDriver event_driver;
    private WebEventListener eventListener;
    private String chromeDriver;
    private String chromeBinary;
    private Boolean chromeFlag;
    private Boolean chromeMobileFlag;
    private String ieDriver;
    private String ieBinary;
    private Boolean ieFlag;
    private String firefoxDriver;
    private String firefoxBinary;
    private Boolean firefoxFlag;
    private DesiredCapabilities  capabilities;
    private String deviceName;

    protected String pathResources;

    public MainTest() {
        initializeProperties();
    }


    /**
     * initializeProperties() method used to get all the properties data from functional-automated-tests.properties
     * In this little example initializeProperties() method gets the paths to .exe and binaries of 3 browsers
     */
    private void initializeProperties() {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("functional-automated-tests.properties");
        try {
            prop.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        chromeFlag = Boolean.parseBoolean(prop.getProperty("functional-automated-tests.ChromeFlag"));
        chromeMobileFlag = Boolean.parseBoolean(prop.getProperty("functional-automated-tests.ChromeMobileFlag"));
        ieFlag = Boolean.parseBoolean(prop.getProperty("functional-automated-tests.IEFlag"));
        firefoxFlag = Boolean.parseBoolean(prop.getProperty("functional-automated-tests.FirefoxFlag"));
        deviceName = prop.getProperty("functional-automated-tests.Device");

        if(chromeFlag || chromeMobileFlag) {
            chromeDriver = prop.getProperty("functional-automated-tests.ChromeDriverPath");
            chromeBinary = prop.getProperty("functional-automated-tests.ChromeBinaryPath");
        }
        else if(ieFlag) {
            ieDriver = prop.getProperty("functional-automated-tests.IEDriverPath");
            ieBinary = prop.getProperty("functional-automated-tests.IEBinaryPath");
        }
        else {
            firefoxDriver = prop.getProperty("functional-automated-tests.FirefoxDriverPath");
            firefoxBinary = prop.getProperty("functional-automated-tests.FirefoxBinaryPath");
        }
        pathResources = prop.getProperty("mainTest.pathResources");
    }

    /**
     * This method is called by Cucumber @Before annotation in TestSteps class. It simply starts driver.
     * I also initialize WebEventListener class as a good selenium practice. I simply want to know what
     * caused tests failure.
     */
    public void beforeClass() {
        if(chromeFlag) {
            beforeChrome();
        }
        else if(ieFlag) {
            beforeIE();
        }
        else if(firefoxFlag){
            beforeFirefox();
        }
        else {
            beforeChromeMobile();
        }
        event_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        event_driver.register(eventListener);
        event_driver.manage().window().maximize();
        event_driver.manage().timeouts().implicitlyWait(MainPage.DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
        event_driver.manage().timeouts().pageLoadTimeout(MainPage.DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
        event_driver.manage().timeouts().setScriptTimeout(MainPage.DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
        event_driver.manage().deleteAllCookies();
    }

    /**
     * This method is called by Cucumber @After annotation in TestSteps class. It simply kills driver
     */
    public void afterClass() {
        event_driver.close();
        event_driver.quit();
    }

    /**
     * Just definition of divers and initialization of new objects. Please take a look at the
     * functional-automated-tests.properties file for more details about versions
     */
    private void beforeChrome() {
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(chromeBinary);
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
    }

    private void beforeIE() {
        System.setProperty("webdriver.ie.driver", ieDriver);
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        driver = new InternetExplorerDriver(ieCapabilities);
    }

    private void beforeFirefox() {
        System.setProperty("webdriver.gecko.driver", firefoxDriver);
        driver = new FirefoxDriver();
    }

    private void beforeChromeMobile() {
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", deviceName);
        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);

        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(capabilities);
    }
}
