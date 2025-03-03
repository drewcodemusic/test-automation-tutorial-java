package stepDefinitions;

import api.ApiSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import static utils.ValidationUtils.*;
import static org.testng.Assert.*;      // utility

public class CucumberSteps {
    private final ApiSteps apiSteps;
    private String currentPageId;

    public CucumberSteps() {
        apiSteps = new ApiSteps();
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

//        assertTrue(firstUrl.startsWith("https://"),
//                "First URL should start with 'https://'");
//        assertTrue(secondUrl.startsWith("https://asia.creativecdn.com/ig-membership?"),
//                "Second URL should start with 'https://asia.creativecdn.com/ig-membership?'");
//        assertTrue(thirdUrl.startsWith("https://asia.creativecdn.com/topics-membership?"),
//                "Third URL should start with 'https://asia.creativecdn.com/topics-membership?'");

        validateValuePrefix(firstUrl, "https://");
        validateValuePrefix(secondUrl, "https://asia.creativecdn.com/ig-membership?");
        validateValuePrefix(thirdUrl, "https://asia.creativecdn.com/topics-membership?");
    }

    @When("I send a column page request for {string}")
    public void iSendRequestToFetchColumnPageContent(String pageId) {
        apiSteps.sendColumnPageRequest(pageId);
        System.out.println("Sent request for page ID: " + pageId);
    }
}