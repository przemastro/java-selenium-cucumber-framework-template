package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Przemek on 21.04.2017.
 */
public class LoggedInUserPage extends MainPage{


    private WebDriverWait wait;
    public static String userBalance;

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
        activeBetButton.click();
        return this;
    }

    public LoggedInUserPage getUserBalance() {
        userBalance = userBalanceButton.getText();
        return this;
    }

    public LoggedInUserPage insertBetValue(String value) {
        singleBetInput.sendKeys(value);
        return this;
    }

    public LoggedInUserPage placeBet() {
        placeBetButton.click();
        return this;
    }

    public LoggedInUserPage verifyToReturnValue(String toReturn) {
        wait = new WebDriverWait(event_driver, 5);
        Assert.assertEquals(toReturnValue.getText(),toReturn);
        return this;
    }

    public LoggedInUserPage verifyTotalStakeValue(String totalStake) {
        wait = new WebDriverWait(event_driver, 5);
        Assert.assertEquals(totalStakeValue.getText(),totalStake);
        return this;
    }

    public LoggedInUserPage verifyUserBalanceIsUpdated(String bet) {
        wait = new WebDriverWait(event_driver, 5);
        int result = Integer.parseInt(userBalance) - Integer.parseInt(bet);
        Assert.assertEquals(userBalanceButton.getText(),Integer.toString(result));
        return this;
    }
}
