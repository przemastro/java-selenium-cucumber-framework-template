package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebEventListener;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Przemek on 21.04.2017.
 */
public class MainPage {

    public static final int DEFAULT_WAIT_FOR_PAGE_LOAD = 60;
    protected final WebDriver driver;
    protected EventFiringWebDriver event_driver;
    protected WebEventListener eventListener;
    public static String locator;

    /**
     * Some Implicit Waits definition.
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        event_driver = new EventFiringWebDriver(driver);
        eventListener = new WebEventListener();
        event_driver.register(eventListener);
        event_driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
        event_driver.manage().timeouts().pageLoadTimeout(DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
        event_driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
    }

    /**
     * this method searches for first enabled button from the table-list of events e.g. match highlights
     */
    public String findActivebutton() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        //firstly wait until at least one element is on the list
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@class='btmarket__wrapper clickable-selections -expanded']/div[2]")));
        boolean flag = false;
        int j = 2;
        //Then search for enabled button on the list
        while(!flag) {
            for(int i=1;i<4;i++) {
                if(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@class='btmarket__wrapper clickable-selections -expanded']/div["+j+"]/div[2]/div[2]/div["+i+"]/button"))).isEnabled()) {
                    locator = "//section[@class='btmarket__wrapper clickable-selections -expanded']//div["+j+"]/div[2]/div[2]/div["+i+"]/button";
                    flag = true;
                    break;
                }
            }j++;
        }
        return locator;
    }

    /**
     * this method is used only by mobile chrome. We need to deal with keyboard. We can not send value to input we need to press some keys.
     * Firstly in playWithKeyboard method we split our bet and add values to array. Then we search in a loop for appropriate value on
     * keyboard and press it.
     */
    public String findKey(String key) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("numberpad")));
        boolean flag = false;
        int j = 1;
        while(!flag) {
            for(int i=1;i<5;i++) {
                if(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='numberpad']/div["+j+"]/button["+i+"]"))).getAttribute("data-value").equals(key)) {
                    locator = "//div[@id='numberpad']/div["+j+"]/button["+i+"]";
                    flag = true;
                    break;
                }
            }j++;
        }
        return locator;
    }

    public void playWithKeyboard(Double bet) {
        int betLength = Double.toString(bet).length();
        String[] arr = Double.toString(bet).split("(?!^)");
        for(int i=0;i<betLength;i++){
            driver.findElement(By.xpath(findKey(arr[i]))).click();
        }
    }


    /**
     * Definition of Web Elements. I prefer to keep all of Web Elements in one class instead of creating
     * dozens of classes. The same situation is with pages. I keep it simple, one class for one page.
     */
    // Account Tab Button
    @FindBy(id="accountTabButton")
    WebElement accountTabButton;

    // Username input field
    @FindBy(id="loginUsernameInput")
    WebElement usernameField;

    // Password input field
    @FindBy(id="loginPasswordInput")
    WebElement passwordField;

    // Login Button
    @FindBy(id="loginButton")
    WebElement loginButton;

    // Football Button
    @FindBy(id="nav-football")
    WebElement footballButton;

    // Tenis Button
    @FindBy(id="nav-tennis")
    WebElement tennisButton;

    // Random Event Link
    @FindBy(xpath="//section[@class='btmarket__wrapper clickable-selections -expanded']/div[2]/div[2]/div/ul/li/a")
    WebElement eventLink;

    // Active Bet Button
    //@FindBy(xpath="//section[@class='btmarket__wrapper clickable-selections -expanded']/div[2]/div[2]/div[2]/div/button")
    //WebElement activeBetButton;

    // Mobile Bet Slip
    @FindBy(id="mobile-betslip-count")
    WebElement mobileBetSlip;

    // Single Bet Input
    @FindBy(xpath="//div[@class='betslip-selection__stake-container betslip-selection__stake-container--single']/span/input")
    WebElement singleBetInput;

    // Betslip Title
    @FindBy(id="betslip-title")
    WebElement betslipTitle;

    // Place Bet Button
    @FindBy(id="place-bet-button")
    WebElement placeBetButton;

    // Open Bets Tab
    @FindBy(id="openbets-tab")
    WebElement openBetsTab;

    // Show Bets Button
    @FindBy(xpath="//div[@id='cimb-alert']/input")
    WebElement showBetsButton;

    // User Balance Link
    @FindBy(id="balanceLink")
    WebElement userBalanceLink;

    // User Balance Button
    @FindBy(id="userBalance")
    WebElement userBalanceButton;

    // ToReturn Value
    @FindBy(xpath="//div[@class='betslip-placed-bet__returns-amount u-bold']")
    WebElement toReturnValue;

    // TotalStake Value
    @FindBy(xpath="//div[@class='betslip-placed-bet__stake-amount']")
    WebElement totalStakeValue;



    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }
}
