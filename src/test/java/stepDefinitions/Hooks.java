package stepDefinitions;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.testng.Reporter; // For reporting

public class Hooks {

    @BeforeAll
    public static void beforeAll() {
        // Code to run once before all scenarios in all feature files
        System.out.println("--- Before All ---");
        // Example: Setting up system properties, initializing connections
    }

    @AfterAll
    public static void afterAll() {
        // Code to run once after all scenarios in all feature files
        System.out.println("--- After All ---");
        // Example: Tearing down connections, generating final reports
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        // Code to run before each scenario
        System.out.println("--- Before Scenario: " + scenario.getName() + " ---");
        // Example: Clearing data, setting up test data, logging scenario start
        Reporter.log("Starting Scenario: " + scenario.getName()); // Add to report
    }

    @After
    public void afterScenario(Scenario scenario) {
        // Code to run after each scenario
        System.out.println("--- After Scenario: " + scenario.getName() + " ---");
        // Example: Clearing data, taking screenshots on failure, logging scenario result
        if (scenario.isFailed()) {
            System.out.println("Scenario Failed! Taking screenshot or logging error.");
            // Add code to take screenshot or log error
        }
        Reporter.log("Finished Scenario: " + scenario.getName() + " - Status: " + scenario.getStatus()); // Add to report
    }
}
