Feature: Success Scenarios for Trading Partner Relationship API
  
  Background: 
  Given A request for TradingPartnerRelations API with all correct headers
  
  @tag11
  Scenario: A valid request to Trading Partner Relations API updates the records.
    Given the request has all valid Trading Partner Relationship Details
    When user sends the request to Trading Partner Relations API
    Then user recieves a success response from Trading Partner Relationship API
    And response body contains transactionEntity related information
    



