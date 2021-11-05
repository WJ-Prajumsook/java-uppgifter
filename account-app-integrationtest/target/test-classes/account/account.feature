Feature: Bank account

  Scenario: As a login user, I should be able to see all my accounts
    Given I want to view all my account
    When I view Account tab in Web page
    Then I should see all my account

  Scenario: As a login user, I should be able to create a new accounts
    Given I want to create a new account
    When I create a new account
    Then My account should be created

  Scenario: As a login user, I should be able to see one of my account
    Given I want to view one of my account
    When I request to view an account with account id
    Then I should see my account