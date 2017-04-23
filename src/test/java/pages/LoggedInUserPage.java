package pages;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.interactions.DoubleClickInSession;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.hamcrest.CoreMatchers.is;

import util.MathFormula;
/**
 * Created by Przemek on 21.04.2017.
 */
public class LoggedInUserPage extends MainPage{


    private WebDriverWait wait;
    public static String userBalance;
    public static double data_num;
    public static double data_denom;
    public static double odd;
    public static double bet;
    double result;

    /**
     * Initialize all Web Elements using PageFactory
     */
    public LoggedInUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoggedInUserPage selectEventType(String event) {
        if (event.equals("football")) {
            footballButton.click();
        }
        else if (event.equals("tennis")) {
            tennisButton.click();
        }
        return this;
    }

    public LoggedInUserPage selectRandomEvent() {
        eventLink.click();
        return this;
    }

    /**
     * before clicking button get some attributes to calculate single bet in the future
     */
    public LoggedInUserPage addActiveBet() {
        WebElement activeBetButton = driver.findElement(By.xpath(findActivebutton()));
        data_num = Double.parseDouble(activeBetButton.getAttribute("data-num"));
        data_denom = Double.parseDouble(activeBetButton.getAttribute("data-denom"));
        activeBetButton.click();
        return this;
    }

    public LoggedInUserPage getUserBalance(Boolean chromeMobileFlag) {
        if(chromeMobileFlag){
            sleep(1000);
            userBalance = userBalanceLink.getText();
            mobileBetSlip.click();
        }
        else {
            userBalance = userBalanceButton.getText();
        }
        return this;
    }

    public LoggedInUserPage insertBetValue(String value, Boolean chromeMobileFlag) {
        bet = Double.parseDouble(value);
        if(chromeMobileFlag) {
            singleBetInput.click();
            playWithKeyboard(bet);
            betslipTitle.click();
        }
        else {
            singleBetInput.sendKeys(value);
        }
        return this;
    }

    public LoggedInUserPage placeBet() {
        placeBetButton.click();
        //sleep(2000);
        return this;
    }

    public LoggedInUserPage navigateToOpenBets() {
        openBetsTab.click();
        return this;
    }

    public LoggedInUserPage showBets() {
        //Yes I use sometimes sleep and I don't have problem with that :)
        sleep(2000);
        showBetsButton.click();
        return this;
    }

    public LoggedInUserPage verifyToReturnValue(String toReturn) {
        MathFormula mf = new MathFormula();
        Assert.assertEquals(toReturnValue.getText().substring(toReturnValue.getText().indexOf("£")+1)
                ,(mf.singleBetFormula(data_num,data_denom,toReturn,bet)));
        return this;
    }

    public LoggedInUserPage verifyTotalStakeValue(String totalStake) {
        Assert.assertTrue(totalStakeValue.getText().contains(totalStake));
        return this;
    }

    /**
     * we need to calculate single bet according to the formula provided in the scenario
     */
    public LoggedInUserPage verifyUserBalanceIsUpdated(String bet, Boolean chromeMobileFlag) {
        DecimalFormat res = new DecimalFormat("0.00");
        double result = Double.parseDouble(userBalance.substring(userBalance.indexOf("£")+1))
                - Double.parseDouble(bet);

        String expectedUserBalance = res.format(result).replace(',','.');
        if(chromeMobileFlag){
            Assert.assertEquals(userBalanceLink.getText().substring(userBalanceLink.getText().indexOf("£") + 1), expectedUserBalance);
        }
        else {
            Assert.assertEquals(userBalanceButton.getText().substring(userBalanceButton.getText().indexOf("£") + 1), expectedUserBalance);
        }
        return this;
    }
}
