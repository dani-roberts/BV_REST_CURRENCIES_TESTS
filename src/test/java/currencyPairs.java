import com.bv.CurrencyAbbreviation;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.fest.assertions.Assertions.assertThat;

public class currencyPairs {

    private final static Logger LOGGER = Logger.getLogger(currencyPairs.class);

    @BeforeTest
    private Response currencyApi() {

        String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json";

       Response response = given().log().all()
                .urlEncodingEnabled(false)
                .get(url);

        response.prettyPrint();

        return response;

    }

    @Test
    public CurrencyAbbreviation getCurrenciesListItems() {
          return currencyApi().as(CurrencyAbbreviation.class);

    }

    @Test
    public void verifyCurrencyListItems(){

        int currencyItems = getCurrenciesListItems().getCurrencyAbrv().size();

        assertThat(currencyItems)
                .as(String.format("Curry items is more than - %s\n", 20, currencyItems))
                .isGreaterThanOrEqualTo(20);
        LOGGER.info("Checking list of currency: " + currencyItems + " ...");
    }


    @Test
    public void verifyGBPAndUSD() {
        //Assert that There's British Pound & United States dollar on the list.
        CurrencyAbbreviation currencyAbrv = getCurrenciesListItems();

        assertThat(currencyAbrv.getCurrencyAbrv().get("usd").equals("United States dollar"));
        assertThat(currencyAbrv.getCurrencyAbrv().get("gbp").equals("United States dollar"));

    }

    @Test
    public void saveAllCurrencyAbbreviationToList(){
        String specificCurrencyUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/{currency}.json";

        CurrencyAbbreviation currency = getCurrenciesListItems();

        for (String key : currency.getCurrencyAbrv().keySet()) {

            given()
                    .log().all()
                    .urlEncodingEnabled(false)
                    .pathParams("currency", key)
                    .get(specificCurrencyUrl)
                    .prettyPrint();
        }

    }
       /*  String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json";

        Response response = given().log().all()
                .urlEncodingEnabled(false)
                .get(url);

        response.prettyPrint();

        CurrencyAbbreviation currency = response.as(CurrencyAbbreviation.class);

        //        Assert that list has more than 20 items.
        //Check how many elements does API returns, save all abbreviations to an ArrayList.
        System.out.println("size: " + currency.getCurrencyAbrv().size());

        //Assert that There's British Pound & United States dollar on the list.
        System.out.println(currency.getCurrencyAbrv().get("usd").equals("United States dollar"));
        System.out.println(currency.getCurrencyAbrv().get("gbp").equals("United States dollar"));


        String specificCurrencyUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/{currency}.json";

        for (String key : currency.getCurrencyAbrv().keySet()) {

            given()
                    .log().all()
                    .urlEncodingEnabled(false)
                    .pathParams("currency", key)
                    .get(specificCurrencyUrl)
                    .prettyPrint();
        }
    }*/
}
