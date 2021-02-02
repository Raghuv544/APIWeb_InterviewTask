Feature: Validating 1Kosmos API's

  @1Kosmos @Smoke
  Scenario: Verify healthZ API
    Given Add Healthz API Request
    When user calls "getHealthzAPI" with "GET" http request
    Then verify the API response code
    And getEULA using "getTenantAPI" and Query Paramenter "1kosmos" based on HealthStatusCode "200"
    Then verify the API response code