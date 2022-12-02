import com.bv.CurrencyAbbreviation;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.fest.assertions.Assertions.assertThat;

public class CurrencyPairs {
    String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json";

    private CurrencyAbbreviation currencyAbbreviation;

    private final static Logger LOGGER = Logger.getLogger(CurrencyPairs.class);

    @BeforeTest
    private void beforeTest() {

       currencyAbbreviation = given().log().all()
                .urlEncodingEnabled(false)
                .get(url).as(CurrencyAbbreviation.class);
    }

    @Test
    public void shouldContainsMoreThanTwentyCurrencyItems() {
        //Assert that list has more than 20 items.
        int currencyItems = currencyAbbreviation.getCurrencyAbrv().size();
        int expectedValue = 20;

        assertThat(currencyItems)
                .as(String.format("Curry items is more than - %s\n", expectedValue, currencyItems))
                .isGreaterThanOrEqualTo(expectedValue);
        LOGGER.info("Checking list of currency: " + currencyItems + " ...");
    }


    @Test
    public void shouldVerifyGBPAndUSDAreOnTheList() {
        //Assert that There's British Pound & United States dollar on the list.
        assertThat(currencyAbbreviation.getCurrencyAbrv().get("usd"))
                .isNotNull()
                .isEqualTo("United States dollar");
        assertThat(currencyAbbreviation.getCurrencyAbrv().get("gbp"))
                .isNotNull()
                .isEqualTo("Pound sterling");

    }

    @Test
    public void souldCheckAndSaveAllCurrencyAbbreviationToList() {
        //Check how many elements does API returns, save all abbreviations to an ArrayList.
        int apiListSize = currencyAbbreviation.getCurrencyAbrv().size();
        System.out.println("API returns... " +apiListSize+ " elements");

        String specificCurrencyUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/{currency}.json";

        for (int i = 0; i < apiListSize; i++) {
            for (String key : currencyAbbreviation.getCurrencyAbrv().keySet()) {


                given()
                        .log().all()
                        .urlEncodingEnabled(false)
                        .pathParams("currency", key)
                        .get(specificCurrencyUrl)
                        .getBody().jsonPath().get(key);
            }
        }
    }
}
