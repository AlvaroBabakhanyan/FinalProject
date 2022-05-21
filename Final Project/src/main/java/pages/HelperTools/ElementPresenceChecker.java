package pages.HelperTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementPresenceChecker {

    private static int shortWait = 5;

    private static int middleWait = 10;

    private static int longWait = 20;

    public static void shortWaitUntilVisible(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(shortWait));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void middleWaitUntilVisible(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(middleWait));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void longWaitUntilVisible(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(longWait));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void shortWaitUntilClickable(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(shortWait));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void middleWaitUntilClickable(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(middleWait));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void longWaitUntilClickable(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(longWait));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}