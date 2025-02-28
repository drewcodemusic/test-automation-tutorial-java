Feature: API Testing Features

  @API
  Scenario: Verify Creative cdn API Response
    When I send a creative cdn api
    Then I should receive a successful api response
    And the response should contain 3 items
    And the creative cdn response items should have the expected types
    And the creative cdn response URLs should have the expected prefixes
