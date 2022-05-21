package tests;

import base.BaseTest;
import constants.dataProviders.dataProvider;
import constants.messages.AssertionMessages;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AffordabilityCalculatorPage;
import pages.HomesForSalePage;

import java.util.Random;

public class AffordabilityCalculatorTests extends BaseTest {

    @Test
    public void testHowMuchHouseCanIAffordCalculator() {
        //navigate to affordabilityCalculatorPage
        AffordabilityCalculatorPage affordabilityCalculatorPage = homePage.clickBuy_affordabilityCalculator();
        //add home equity
        affordabilityCalculatorPage.clickAddMyHomeEquityButton();
        //input home sale price
        affordabilityCalculatorPage.inputHomeSalePrice(300000);
        affordabilityCalculatorPage.clickAddMyHomeEquityDoneButton();
        //input location
        affordabilityCalculatorPage.inputLocation((String) dataProvider.VALID_SEARCH_KEYWORDS[0][0]);
        //search for first suggestion
        affordabilityCalculatorPage.clickFirstSuggestionOfInputtedLocation();
        //get the recommended max price
        int maxPrice = affordabilityCalculatorPage.getMaxEstimatedPrice();
        //navigate to HomesForSale page
        HomesForSalePage homesForSalePage = affordabilityCalculatorPage.clickViewAllHomesOnMapButton();
        //test random homeCards value
        int n = homesForSalePage.getHomeCardNumber();
        Random random = new Random();
        int r = random.nextInt(n + 1);
        int price = homesForSalePage.getHomeCardPrice(r);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(maxPrice >= price);
    }

    @Test
    public void testInvalidInputs() {
        //navigate to affordabilityCalculatorPage
        AffordabilityCalculatorPage affordabilityCalculatorPage = homePage.clickBuy_affordabilityCalculator();
        //add home equity
        affordabilityCalculatorPage.clickAddMyHomeEquityButton();
        //input home sale price
        affordabilityCalculatorPage.inputOtherSellingCosts(-99999999);
        affordabilityCalculatorPage.clickAddMyHomeEquityDoneButton();
        //input location
        affordabilityCalculatorPage.inputLocation((String) dataProvider.VALID_SEARCH_KEYWORDS[0][0]);
        //search for first suggestion
        affordabilityCalculatorPage.clickFirstSuggestionOfInputtedLocation();
        //get the recommended max price
        int maxPrice = affordabilityCalculatorPage.getMaxEstimatedPrice();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(maxPrice >= 0, AssertionMessages.UNSUCCESSFUL_AFFORDABILITY_CALCULATOR_INPUT);
    }
}
