package pages;

import constants.locators.AffordabilityCalculatorPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.HelperTools.ElementPresenceChecker;

import java.util.ArrayList;

public class AffordabilityCalculatorPage {
    private WebDriver driver;
    private By locationInput = By.xpath(AffordabilityCalculatorPageConstants.INPUT_LOCATION);
    private By firstLocationSuggestion = By.cssSelector(AffordabilityCalculatorPageConstants.FIRST_LOCATION_SUGGESTION);
    private By viewAllHomesOnMapButton = By.cssSelector(AffordabilityCalculatorPageConstants.VIEW_ALL_HOMES_ON_MAP_BUTTON);
    private By maxEstimatedPrice = By.cssSelector(AffordabilityCalculatorPageConstants.MAX_ESTIMATED_PRICE);
    private By addMyHomeEquityButton = By.cssSelector(AffordabilityCalculatorPageConstants.ADD_MY_HOME_EQUITY_BUTTON);
    private By homePriceInput = By.cssSelector(AffordabilityCalculatorPageConstants.HOME_PRICE_INPUT);
    private By addHomeEquityDoneButton = By.xpath(AffordabilityCalculatorPageConstants.ADD_MY_HOME_EQUITY_DONE_BUTTON);
    private By otherSellingCostsInput = By.cssSelector(AffordabilityCalculatorPageConstants.OTHER_SELLING_COSTS_INPUT);

    public AffordabilityCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getMaxEstimatedPrice() {
        ElementPresenceChecker.middleWaitUntilVisible(driver, maxEstimatedPrice);
        String price = driver.findElement(maxEstimatedPrice).getText();
        price = price.replace("$", "");
        price = price.replace(",", "");
        int result = 0;
        try {
            result = Integer.parseInt(price);
        }
        catch (NumberFormatException e) {
            result = Integer.parseInt(price.replaceAll("[\\D]", ""));
        }
        return result;
    }

    public void inputLocation(String searchText) {
        ElementPresenceChecker.middleWaitUntilClickable(driver, locationInput);
        driver.findElement(locationInput).sendKeys(searchText);
    }

    public void clickFirstSuggestionOfInputtedLocation() {
        ElementPresenceChecker.longWaitUntilVisible(driver, firstLocationSuggestion);
        driver.findElement(firstLocationSuggestion).click();
    }

    public HomesForSalePage clickViewAllHomesOnMapButton() {
        ElementPresenceChecker.middleWaitUntilVisible(driver, viewAllHomesOnMapButton);
        driver.findElement(viewAllHomesOnMapButton).click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        System.out.println(driver.getCurrentUrl());
        return new HomesForSalePage(driver);
    }

    public void clickAddMyHomeEquityButton() {
        ElementPresenceChecker.longWaitUntilVisible(driver, addMyHomeEquityButton);
        driver.findElement(addMyHomeEquityButton).click();
    }

    public void inputHomeSalePrice(int price) {
        ElementPresenceChecker.middleWaitUntilVisible(driver, homePriceInput);
        driver.findElement(homePriceInput).sendKeys(String.valueOf(price));
    }

    public void inputOtherSellingCosts(int price) {
        ElementPresenceChecker.middleWaitUntilVisible(driver, otherSellingCostsInput);
        driver.findElement(otherSellingCostsInput).sendKeys(String.valueOf(price));
    }

    public void clickAddMyHomeEquityDoneButton() {
        ElementPresenceChecker.shortWaitUntilVisible(driver, addHomeEquityDoneButton);
        driver.findElement(addHomeEquityDoneButton).click();
    }
}
