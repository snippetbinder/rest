Feature: Success Scenarios for assign Fixed Fees for the BU
  
  Background: 
  Given A request for Fixed Fees API to assign fixed fees for BU with all correct headers
  
  @tag21
  Scenario: A valid post request to Fixed Fees API for a BU assigns the fixed fees for the BU.
    Given the request has all valid Fixed Fees inputs
    And assignType "BU" is specified in the request
    When user posts the those information to Fixed Fees API
    Then user recieves a success response from API
    And the record is created
    



