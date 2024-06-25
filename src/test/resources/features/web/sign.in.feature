@SignIn
Feature: Sign In scenarios

  Scenario Outline: Login using valid creds
    Given User opens home page: url
    When User navigates to sign-in page
    Then User logons using credentials: "<email>" and "<password>"

    Examples:
      | email                 | password               |
      | roni_cost@example.com | roni_cost3@example.com |