@LoginBlibliFeature
Feature: Login to Blibli

  Scenario: Verify the Blibli login page when open it for the first time
    Given user is on Blibli.com website
    When user go to the login page
    Then verify that the login page is as expected