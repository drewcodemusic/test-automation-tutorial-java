package stepDefinitions;

import api.ApiSteps;
import api.RestfulBookerApiSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import static utils.ValidationUtils.*;
import static org.testng.Assert.*;

public class CucumberSteps {
    private final ApiSteps apiSteps;
    private final RestfulBookerApiSteps restfulBookerApiSteps;
    private String currentPageId;

    public CucumberSteps() {
        apiSteps = new ApiSteps();
        restfulBookerApiSteps = new RestfulBookerApiSteps();
    }

    @When("I send a creative cdn api")
    public void i_send_a_creative_cdn_api() {
        apiSteps.setupApiRequest();
    }

    @Then("I should receive a successful api response")
    public void i_should_receive_a_successful_api_response() {
        apiSteps.validateResponseStatus();
    }

    @And("the response should contain {int} items")
    public void theResponseShouldContainItems(int expectedCount) {
        JSONArray responseArray = new JSONArray(apiSteps.getResponseBody());
        assertEquals(responseArray.length(), expectedCount,
                "Response should contain exactly " + expectedCount + " items");
    }

    @And("the creative cdn response items should have the expected types")
    public void theResponseItemsShouldHaveTheExpectedTypes() {
        JSONArray responseArray = new JSONArray(apiSteps.getResponseBody());
        assertEquals(responseArray.getJSONObject(0).getString("type"), "IMG",
                "First item should have type 'IMG'");
        assertEquals(responseArray.getJSONObject(1).getString("type"), "IFRAME",
                "Second item should have type 'IFRAME'");
        assertEquals(responseArray.getJSONObject(2).getString("type"), "IFRAME",
                "Third item should have type 'IFRAME'");
    }

    @And("the creative cdn response URLs should have the expected prefixes")
    public void theResponseURLsShouldHaveTheExpectedPrefixes() {
        JSONArray responseArray = new JSONArray(apiSteps.getResponseBody());

        String firstUrl = responseArray.getJSONObject(0).getString("url");
        String secondUrl = responseArray.getJSONObject(1).getString("url");
        String thirdUrl = responseArray.getJSONObject(2).getString("url");

        validateValuePrefix(firstUrl, "https://");
        validateValuePrefix(secondUrl, "https://asia.creativecdn.com/ig-membership?");
        validateValuePrefix(thirdUrl, "https://asia.creativecdn.com/topics-membership?");
    }

    @When("I send a column page request for {string}")
    public void iSendRequestToFetchColumnPageContent(String pageId) {
        apiSteps.sendColumnPageRequest(pageId);
        System.out.println("Sent request for page ID: " + pageId);
    }

    @Given("I have an authentication token for {string} and {string}")
    public void iHaveAnAuthenticationTokenForAnd(String username, String password) {
        restfulBookerApiSteps.createToken(username, password);
    }

    @When("I create a booking with firstname {string}, lastname {string}, totalprice {int}, depositpaid {string}, checkin {string}, checkout {string}, and additionalneeds {string}")
    public void iCreateABookingWith(String firstname, String lastname, int totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        boolean deposit = Boolean.parseBoolean(depositpaid);
        restfulBookerApiSteps.createBooking(firstname, lastname, totalprice, deposit, checkin, checkout, additionalneeds);
    }

    @When("I update the booking with firstname {string}, lastname {string}, totalprice {int}, depositpaid {string}, checkin {string}, checkout {string}, and additionalneeds {string}")
    public void iUpdateTheBookingWith(String firstname, String lastname, int totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        boolean deposit = Boolean.parseBoolean(depositpaid);
        restfulBookerApiSteps.updateBooking(firstname, lastname, totalprice, deposit, checkin, checkout, additionalneeds);
    }

    @Then("I should receive a successful restful-booker api response")
    public void iShouldReceiveASuccessfulRestfulBookerApiResponse() {
        restfulBookerApiSteps.validateResponseStatus();
    }

    @Then("The updated response jsonpath value {string} should be different from original response jsonpath value {string}")
    public void validateUpdatedJsonpathValueIsDifferent(String jsonpath1, String jsonpath2) {
        restfulBookerApiSteps.validateJsonPathValueIsUpdated(jsonpath1, jsonpath2);
    }

    @Then("The updated response jsonpath value {string} should be same as original response jsonpath value {string}")
    public void validateUpdatedJsonpathValueIsSame(String jsonpath1, String jsonpath2) {
        restfulBookerApiSteps.validateJsonPathValueIsSame(jsonpath1, jsonpath2);
    }
}