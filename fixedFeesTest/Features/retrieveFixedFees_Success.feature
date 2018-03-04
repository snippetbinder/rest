Feature: Success Scenarios
  
  Background: 
  Given A request for FixedFees API with all correct headers
  
  @tag1
  Scenario Outline: A valid request to Fixed Fees API with valid businessUnitId retrieves the fixed fees associated with that business unit.
    Given the request has a valid businessUnitId "GC15423437YQ"
    And pay for value is "<payFor>"
    When user sends the request to the API
    Then user retrieves a success response from the API
    And response contains fixed fees information associated to the businessunit ID 
    
    Examples: 
      | payFor  |
      | SELF    |
      | OTHER   |


