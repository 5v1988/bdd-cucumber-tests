@Mobile
Feature: Mobile sample scenarios

  @TextField
  Scenario: Verify the text is entered on the field
    Given Mobile User taps 'Views' on the list
    When Mobile User taps 'Controls' on the list
    And Mobile User selects the theme: "2. Dark Theme"
    And Mobile User enters the text: 'This is a test!'
    Then Mobile User verifies the text: 'This is a test!' is entered

  @MobileLogin @IOS @Android
  Scenario: Verify the user can login to 'SwagLabs' app
    When Mobile User logons using username: 'standard_user' and password: 'secret_sauce'
    Then Mobile User verifies that home screen is displayed
