package tests;

import base.BaseTest;
import constants.dataProviders.dataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomesForSalePage;

import java.util.Random;

public class FilterTests extends BaseTest {

    @DataProvider
    public static Object[][] validPriceRange() {
        return dataProvider.VALID_PRICE_RANGE;
    }

    @DataProvider
    public static Object[][] invalidPriceRange() {
        return dataProvider.INVALID_PRICE_RANGE;
    }

    @DataProvider
    public static Object[][] validBedRange() { return dataProvider.VALID_BED_RANGE;}

    @DataProvider
    public static Object[][] validBathValue() { return dataProvider.VALID_BATH_VALUE;}

    @Test(description = "Search homes inputting valid price range", dataProvider = "validPriceRange")
    public void testFilterByPriceValidPriceRange(String[] range) {
        HomesForSalePage homesForSalePage = homePage.inputSearchKeywordAndClickFirstSearchSuggestion((String) dataProvider.VALID_SEARCH_KEYWORDS[0][0]);

        homesForSalePage.filterByPrice(range[0], range[1]);

        SoftAssert softAssert = new SoftAssert();

        int n = homesForSalePage.getHomeCardNumber();
        Random random = new Random();
        int r = random.nextInt(n);
        int price = homesForSalePage.getHomeCardPrice(r);
        softAssert.assertTrue(price >= Integer.parseInt(range[0]));
        softAssert.assertTrue(price <= Integer.parseInt(range[1]));
        softAssert.assertAll();
    }

    @Test(description = "Search homes inputting invalid price range", dataProvider = "invalidPriceRange")
    public void testFilterByPriceInvalidPriceRange(String[] range) {
        HomesForSalePage homesForSalePage = homePage.inputSearchKeywordAndClickFirstSearchSuggestion((String) dataProvider.VALID_SEARCH_KEYWORDS[0][0]);

        int n = homesForSalePage.getHomeCardNumber();
        Random random = new Random();
        int r = random.nextInt(n);
        int oldHomeCardPrice = homesForSalePage.getHomeCardPrice(r);

        homesForSalePage.filterByPrice(range[0], range[1]);

        SoftAssert softAssert = new SoftAssert();
        int newHomeCardPrice = homesForSalePage.getHomeCardPrice(r);
        softAssert.assertEquals(oldHomeCardPrice, newHomeCardPrice);

        String min = homesForSalePage.getMinPrice();
        softAssert.assertTrue(min.equals(""));

        softAssert.assertAll();
    }

    @Test(description = "Search homes applying bed filter", dataProvider = "validBedRange")
    public void testFilterByBed(Integer[] range) { //deal with studio case
        HomesForSalePage homesForSalePage = homePage.inputSearchKeywordAndClickFirstSearchSuggestion((String) dataProvider.VALID_SEARCH_KEYWORDS[0][0]);

        homesForSalePage.filterByBed(range[0], range[1]);

        SoftAssert softAssert = new SoftAssert();

        int n = homesForSalePage.getHomeCardNumber();
        Random random = new Random();
        int r = random.nextInt(n);
        int bedNumber = homesForSalePage.getHomeCardBedNumber(r);
        if (range[0] == 5 || range[1] == 5) { // in this case there is no upper bound for beds
            softAssert.assertTrue(bedNumber >= range[0]);
        }
        else {
            softAssert.assertTrue(bedNumber >= range[0] && bedNumber <= range[1]);
        }

    }

    @Test(description = "Search homes applying bath filter", dataProvider = "validBathValue")
    public void testFilterByBath(String value) throws InterruptedException { //deal with studio case
        HomesForSalePage homesForSalePage = homePage.inputSearchKeywordAndClickFirstSearchSuggestion((String) dataProvider.VALID_SEARCH_KEYWORDS[0][0]);

        homesForSalePage.filterByBath(value);

        SoftAssert softAssert = new SoftAssert();

        int n = homesForSalePage.getHomeCardNumber();

        Random random = new Random();
        int r = random.nextInt(n);
        double bathNumber = homesForSalePage.getHomeCardBathNumber(r);
        softAssert.assertTrue(bathNumber >= Double.parseDouble(value));
    }
}
