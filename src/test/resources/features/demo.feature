Feature: API Testing Features

  @BBDSmoke
  Scenario: Test Basic Cucumber Functionality
    Given I start the test
    When I execute the step
    Then I should see the result

  @API
  Scenario: Verify Creative cdn API Response
    When I send a creative cdn api
    Then I should receive a successful api response
#    And the response should contain the correct data structure
#    And the response should have valid URLs and types

  @newfeature
  Scenario: New feature
    Given I start the test
    When I send a creative cdn api
    Then I should receive a successful api response
#    And the response should contain the correct data structure
#    And the response should have valid URLs and types