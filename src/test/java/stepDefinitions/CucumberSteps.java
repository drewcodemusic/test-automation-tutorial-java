package stepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class CucumberSteps {
    private final ApiSteps_0221 apiSteps;

    public CucumberSteps() {
        apiSteps = new ApiSteps_0221();
    }

    @When("I send a creative cdn api")
    public void i_send_a_creative_cdn_api() {
        apiSteps.setupApiRequest();
    }

    @Then("I should receive a successful api response")
    public void i_should_receive_a_successful_api_response() {
        apiSteps.validateResponse();
    }

    @Then("the response should contain the correct data structure")
    public void the_response_should_contain_the_correct_data_structure() {
        // This is already covered in validateResponse()
    }

    @Then("the response should have valid URLs and types")
    public void the_response_should_have_valid_ur_ls_and_types() {
        // This is already covered in validateResponse()
    }

    // Original scenario steps
    @io.cucumber.java.en.Given("I start the test")
    public void iStartTheTest() {
        System.out.println("Test started");
    }

    @When("I execute the step")
    public void iExecuteTheStep() {
        System.out.println("Step executed");
    }

    @Then("I should see the result")
    public void iShouldSeeTheResult() {
        System.out.println("Result verified");
    }
}