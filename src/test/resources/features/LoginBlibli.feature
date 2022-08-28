@LoginBlibliFeature
Feature: Login to Blibli

  Scenario: Verify the Blibli login page when open it for the first time
    Given user is on Blibli.com website
    When user go to the login page
    Then verify that the login page UI is as expected with UI requirement 1

  Scenario: Verify the Blibli login page with google and facebook button is not expected
    Given user is on Blibli.com website
    When user go to the login page
    Then verify that the login page UI is as expected with UI requirement 2

  Scenario: Verify the Blibli login page with google and facebook button is optional
    Given user is on Blibli.com website
    When user go to the login page
    Then verify that the login page UI is as expected with UI requirement 3

  Scenario: Verify the Blibli login page where slight shift on Blibli login image is not allow
    Given user is on Blibli.com website
    When user go to the login page
    Then verify that the login page UI is as expected with UI requirement 4

  Scenario: Verify the Blibli login page where slight shift on Blibli login image is allow
    Given user is on Blibli.com website
    When user go to the login page
    Then verify that the login page UI is as expected with UI requirement 5