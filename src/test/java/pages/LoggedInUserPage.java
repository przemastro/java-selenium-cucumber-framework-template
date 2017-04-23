package pages;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    public LoggedInUserPage addActiveBet() {
        data_num = Double.parseDouble(activeBetButton.getAttribute("data-num"));
        data_denom = Double.parseDouble(activeBetButton.getAttribute("data-denom"));
        activeBetButton.click();
        return this;
    }

    public LoggedInUserPage getUserBalance() {
        userBalance = userBalanceButton.getText();
        return this;
    }

    public LoggedInUserPage insertBetValue(String value) {
        bet = Double.parseDouble(value);
        singleBetInput.sendKeys(value);
        return this;
    }

    public LoggedInUserPage placeBet() {
        placeBetButton.click();
        return this;
    }

    public LoggedInUserPage navigateToOpenBets() {
        openBetsTab.click();
        return this;
    }


    public LoggedInUserPage verifyToReturnValue(String toReturn) {
        wait = new WebDriverWait(event_driver, 5);
        MathFormula mf = new MathFormula();
        System.out.println(toReturnValue.getText());
        Assert.assertTrue(toReturnValue.getText().contains(mf.singleBetFormula(data_num,data_denom,toReturn,bet)));
        return this;
    }

    public LoggedInUserPage verifyTotalStakeValue(String totalStake) {
        wait = new WebDriverWait(event_driver, 5);
        Assert.assertTrue(totalStakeValue.getText().contains(totalStake));
        return this;
    }

    public LoggedInUserPage verifyUserBalanceIsUpdated(String bet) {
        wait = new WebDriverWait(event_driver, 5);
        int result = Integer.parseInt(userBalance) - Integer.parseInt(bet);
        Assert.assertEquals(userBalanceButton.getText(),Integer.toString(result));
        return this;
    }
}
