package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Przemek on 21.04.2017.
 */
public class HomePage extends MainPage{


    /**
     * Initialize all Web Elements using PageFactory
     */
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage navigateToLoggedInPage(String url) {
        driver.get(url);
        return this;
    }

    public HomePage login(String name, String pass) {
        accountTabButton.click();
        usernameField.sendKeys(name);
        passwordField.sendKeys(pass);
        loginButton.click();
        return this;
    }
}
