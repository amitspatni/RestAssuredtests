Feature: Add Place to google

  @AddPlace
  Scenario: 
    Given Add place payload
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

  @AddPlace
  Scenario Outline: 
    Given Add place payload with "<name>" "<place>" "<address>"
    When user calls "<API>" with "<method>" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify "place_id" created maps to "<name>" using "GetPlaceAPI"

    Examples: 
      | name | place     | address | API         | method |
      | amit | amithouse | gwalior | AddPlaceAPI | Post   |
      
  
  @DeletePlace
    Scenario: To verify delete place functionality
    Given Delete place payload
    When user calls "DeletePlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    
    




