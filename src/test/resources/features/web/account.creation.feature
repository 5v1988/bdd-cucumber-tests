@NewAccount
Feature: Account Creation related scenarios

  Scenario Outline: Create a new account
    Given User opens home page: url
    When User navigates to create new customer account
    And User enters auto-generated personal info
    And User checks the checkbox 'Sign Up for Newsletter'
    And User enters following sign-in info:
      | email           |  |
      | password        |  |
      | confirmPassword |  |
    Then User creates an account and verifies the message: "<message>"

    Examples:
      | message                                            |
      | Thank you for registering with Main Website Store. |
