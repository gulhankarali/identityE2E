Feature: Car Registration Validation for Valuation Process
  @e2e
  Scenario: Validate Car Registration Numbers for Valuation
    Given I am on the main page for car valuation
    When I enter car registrations number in the input file and mileage
    Then I can verify passed and failed registration numbers

