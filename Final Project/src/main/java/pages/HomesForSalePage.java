package pages;

import constants.locators.HomePageConstants;
import constants.locators.HomesForSalePageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HelperTools.ElementPresenceChecker;

import java.util.ArrayList;
import java.util.List;

public class HomesForSalePage {
    private WebDriver driver;
    private By sortBySquareFeetButton = By.xpath(HomesForSalePageConstants.SORT_BY_SQUARE_FEET_BUTTON);
    private By tableButton = By.cssSelector(HomesForSalePageConstants.TABLE_BUTTON);
    private By photosButton = By.xpath(HomesForSalePageConstants.PHOTOS_BUTTON);
    private By mapTabButton = By.xpath(HomesForSalePageConstants.MAP_TAB_BUTTON);
    private By filterByPriceButton = By.xpath(HomesForSalePageConstants.FILTER_BY_PRICE_BUTTON);
    private By filterByBedBathButton = By.xpath(HomesForSalePageConstants.FILTER_BY_BED_BATH_BUTTON);
    private By bedBathFilterDoneButton = By.xpath(HomesForSalePageConstants.BED_BATH_FILTER_DONE_BUTTON);
    private By minPriceInputField = By.cssSelector(HomesForSalePageConstants.MIN_PRICE_INPUT_FIELD);
    private By maxPriceInputField = By.cssSelector(HomesForSalePageConstants.MAX_PRICE_INPUT_FIELD);
    private By priceFilterDoneButton = By.cssSelector(HomesForSalePageConstants.FILTER_BY_PRICE_DONE_BUTTON);
    private By filterByPriceClearButton = By.cssSelector(HomesForSalePageConstants.FILTER_BY_PRICE_CLEAR_BUTTON);
    private By homeCardPriceInfo = By.className(HomesForSalePageConstants.HOME_CARD_PRICE_INFO);
    private By homeCardAreaInfo = By.cssSelector(HomesForSalePageConstants.HOME_CARD_AREA_INFO);
    private By homeCardBedInfo = By.cssSelector(HomesForSalePageConstants.HOME_CARD_BED_INFO);
    private By homeCardBathInfo = By.cssSelector(HomesForSalePageConstants.HOME_CARD_BATH_INFO);
    private By bedOptions = By.xpath(HomesForSalePageConstants.BED_OPTIONS);
    private By bathOptions = By.xpath(HomesForSalePageConstants.BATH_OPTIONS);
    private By homeNumberInfo = By.cssSelector(HomesForSalePageConstants.HOME_NUMBER_INFO);

    public HomesForSalePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTableButton() {
        if (!driver.findElements(mapTabButton).isEmpty() &&
            !driver.findElement(mapTabButton).getText().contains("isClosed")) {
            driver.findElement(mapTabButton).click();
        }
        ElementPresenceChecker.middleWaitUntilVisible(driver, tableButton);
        driver.findElement(tableButton).click();
    }

    public void clickTableSortBySqFtButton() {
        ElementPresenceChecker.shortWaitUntilVisible(driver, sortBySquareFeetButton);
        driver.findElement(sortBySquareFeetButton).click();
    }

    public void clickPhotosButton() {
        ElementPresenceChecker.middleWaitUntilVisible(driver, photosButton);
        driver.findElement(photosButton).click();
    }

    private By getHomeCard(int index) {
        ElementPresenceChecker.longWaitUntilVisible(driver, By.id(HomesForSalePageConstants.HOME_CARD + index));
        return By.id(HomesForSalePageConstants.HOME_CARD + index);
    }

    public String getHomeCardAddress(int index) {
        By homeCard = getHomeCard(index);
        ElementPresenceChecker.shortWaitUntilVisible(driver, homeCard);
        WebElement we = driver.findElement(homeCard);
        return we.findElement(By.className(HomesForSalePageConstants.HOME_CARD_ADDRESS)).getText();
    }

    public int getHomeCardPrice(int index) {
        WebElement homeCard = driver.findElement(getHomeCard(index));
        String priceText = homeCard.findElement(homeCardPriceInfo).getText();
        priceText = priceText.replace("$", "");
        priceText = priceText.replace(",", "");
        return Integer.parseInt(priceText);
    }

    public int getHomeCardBedNumber(int index) {
        WebElement homeCard = driver.findElement(getHomeCard(index));
        String bedText = homeCard.findElement(homeCardBedInfo).getText();
        int i = bedText.indexOf(' ');
        return Integer.parseInt(bedText.substring(0, i));
    }

    public double getHomeCardBathNumber(int index) {
        WebElement homeCard = driver.findElement(getHomeCard(index));
        String bathText = homeCard.findElement(homeCardBathInfo).getText();
        int i = bathText.indexOf(' ');
        return Double.parseDouble(bathText.substring(0, i));
    }

    public int getHomeCardArea(int index) {
        WebElement homeCard = driver.findElement(getHomeCard(index));
        String areaSquareFeet = homeCard.findElement(homeCardAreaInfo).getText();
        return Integer.parseInt(areaSquareFeet.replaceAll("[\\D]", ""));
    }

    public PropertyDetailsPage clickHomeCard(int index) {
        driver.findElement(getHomeCard(index)).click();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        return new PropertyDetailsPage(driver);
    }

    public void filterByPrice(String min, String max) {
        ElementPresenceChecker.longWaitUntilVisible(driver, filterByPriceButton);
        driver.findElement(filterByPriceButton).click();
        driver.findElement(filterByPriceClearButton).click();
        driver.findElement(minPriceInputField).sendKeys(min);
        driver.findElement(maxPriceInputField).sendKeys(max);
        driver.findElement(priceFilterDoneButton).click();
    }

    public void filterByBed(int min, int max) {
        ElementPresenceChecker.middleWaitUntilVisible(driver, filterByBedBathButton);
        driver.findElement(filterByBedBathButton).click();
        List<WebElement> searchResults = driver.findElement(bedOptions).findElements(By.tagName("div"));
        searchResults.get(min+1).click();
        if (max == 0 || max == 1 || max == 2 || max == 3 || max == 4) {
            searchResults.get(max+1).click();
        }
        driver.findElement(bedBathFilterDoneButton).click();
    }

    public void filterByBath(String number) {
        ElementPresenceChecker.middleWaitUntilVisible(driver, filterByBedBathButton);
        driver.findElement(filterByBedBathButton).click();
        List<WebElement> searchResults = driver.findElement(bathOptions).findElements(By.tagName("div"));
        switch (number) {
            case "1":
                searchResults.get(1).click();
                break;
            case "1.5":
                searchResults.get(2).click();
                break;
            case "2":
                searchResults.get(3).click();
                break;
            case "2.5":
                searchResults.get(4).click();
                break;
            case "3":
                searchResults.get(5).click();
                break;
            case "4":
                searchResults.get(6).click();
                break;
        }
        driver.findElement(bedBathFilterDoneButton).click();
    }

    public String getMinPrice() {
        ElementPresenceChecker.middleWaitUntilVisible(driver, filterByPriceButton);
        driver.findElement(filterByPriceButton).click();
        var textM = driver.findElement(minPriceInputField).getText();
        return textM;
    }

    public int getHomeCardNumber() {
        try {
            ElementPresenceChecker.longWaitUntilClickable(driver, By.id("MapHomeCard_0"));
        }
        catch (NoSuchElementException e) {
            return 0;
        }
        String s = driver.findElement(homeNumberInfo).getText();
        int i = s.indexOf(' ');
        s = s.substring(0, i);
        return Integer.parseInt(s);
    }


}

