package pages;

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
    @FindBy(xpath="//div[@class='btmarket']/div/div/button")
    WebElement activeBetButton;

    // Single Bet Input
    @FindBy(xpath="//div[@class='betslip-selection__stake-container betslip-selection__stake-container--single']/span/input")
    WebElement singleBetInput;

    // Place Bet Button
    @FindBy(id="place-bet-button")
    WebElement placeBetButton;

    // User Balance Button
    @FindBy(id="userBalance")
    WebElement userBalanceButton;

    // ToReturn Value
    @FindBy(id="total-to-return-price")
    WebElement toReturnValue;

    // TotalStake Value
    @FindBy(id="total-stake-price")
    WebElement totalStakeValue;



    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }
}
