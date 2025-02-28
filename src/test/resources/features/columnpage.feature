Feature: Column page features

  @column
  Scenario Outline: Verify column page content for different page IDs
    When I send a column page request for "<pageId>"
    Then I should receive a successful api response

    Examples:
      | pageId    |
      | h24/3c    |
      | h24/cp    |
      | h24/nb    |
