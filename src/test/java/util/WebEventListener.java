package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * Created by Przemek on 21.04.2017.
 */
public class WebEventListener extends AbstractWebDriverEventListener {

    /**
     * Let's overwrite methods with our own.
     */
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Trying to navigate to: '" + url + "'");
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigated to:'" + url + "'");
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("Trying to click on: " + element.toString());
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("Clicked on: " + element.toString());
    }

    public void onException(Throwable error, WebDriver driver) {
        System.out.println("Error occurred: " + error);
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Trying to find Element " + by.toString());
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Found Element " + by.toString());
    }
}
