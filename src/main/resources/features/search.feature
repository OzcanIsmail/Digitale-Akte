Feature: Search in Google

  @all
  Scenario: Seek for Selenium documentation
    When I navigate to "https://google.de"
    And I type "Selenium"
    And I press Enter
    Then I should be shown results including "Selenium"
    #See the Excel file