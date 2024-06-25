@Mobile @WebApp
Feature: Klipboard features

  Scenario: Add and Save the text to Klipboard
    Given User opens the url: 'https://klipit.in/afc57493' in the browser
    And User clears Klipboard content
    When User adds and saves the text: 'This is a sample text!' to the klipboard
    And User releoads the current page
    Then User verifies that the text: 'This is a sample text!' is displayed
