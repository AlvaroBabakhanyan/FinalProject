package constants.locators;

public class AffordabilityCalculatorPageConstants {
    public static final String INPUT_LOCATION = "(//*[@id=\"search-box-input\"])[3]"; //multiple elements with same id!
    public static final String FIRST_LOCATION_SUGGESTION = "#content > div:nth-child(15) > section > div.HomeAffordabilityCalculator > div:nth-child(1) > div > div.left-panel > div > form > div.StaticFieldWrapper.LocationSearchBox > div.field > div > form > div.ExpandedResults.transition-display-none-to-block > div > div > div:nth-child(2) > a";
    public static final String VIEW_ALL_HOMES_ON_MAP_BUTTON = "#affordable-homes > div > a";
    public static final String MAX_ESTIMATED_PRICE = "#estimate > div > div.affordability-estimation-price-section > div.estimated-price-container > div > span";
    public static final String ADD_MY_HOME_EQUITY_BUTTON = "button[class=\"button Button add-home-equity-button\"]";
    public static final String HOME_PRICE_INPUT = "input[aria-label=\"Home Sale Price\"]";
    public static final String ADD_MY_HOME_EQUITY_DONE_BUTTON = "//span[contains(text(),'Done')]";
    public static final String OTHER_SELLING_COSTS_INPUT = "input[aria-label=\"Other Selling Costs\"]";
}
