package constants.dataProviders;

public class dataProvider {
    public static Object[][] VALID_SEARCH_KEYWORDS = new Object[][]{
            {"New York"},
            {"10003"}
    };

    public static Object[][] INVALID_SEARCH_KEYWORDS = new Object[][]{
            {"adfghsdhfgs"},
            {"85245964"}
    };

    public static Object[][] VALID_PRICE_RANGE = new Object[][]{
            {"100000", "999999"},
            {"0", "566000"}
    };

    public static Object[][] INVALID_PRICE_RANGE = new Object[][]{
            {"one million", "dmujkt"},
    };

    public static Object[][] VALID_BED_RANGE = new Object[][]{
            {2, 3}
    };

    public static Object[][] VALID_BATH_VALUE = new Object[][]{
            {"2.5"}
    };
}

