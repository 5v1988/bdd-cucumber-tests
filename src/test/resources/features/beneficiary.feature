@Beneficiary
Feature: Beneficiary service related scenarios

  @CreateBeneficiary
  Scenario: Create a new beneficiary successfully
    Given API User — Get and set valid access token
    When API User — Create beneficiary using the following data:
    """
    {
      "name": "Veera",
      "email": "test@test.com",
      "type": "Individual",
      "relationship": "BranchOffice"
    }
    """
    Then API User — Verify that beneficiary is created

  @CreateBeneficiary @InValid
  Scenario: Create a new beneficiary with invalid data
    Given API User — Get and set valid access token
    When API User — Create beneficiary using the invalid data:
    """
    {
      "name": "VV",
      "email": "ee@ee.com",
      "address": "1 Pn Close",
      "country": "Singapore",
      "zipcode": "390001",
      "type": "Individual",
      "relationship": "Self"
    }
    """
    Then API User — Verify that beneficiary is not created
    And API User — Verify the error message for the following field:
      | field   | relationship             |
      | message | Relationship field error |