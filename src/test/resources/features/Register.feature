
Feature: Registering to automation practice

  @regression
  Scenario: Validate user is able to register
    Given Automation practice application is opened
    When I click sign in
    And I create account with emailid "testRandom@testauto.com"
    Then I should be able to register with my below details
      | Gender | FirstName | LastName  | Password  | Address            | City      | State  | Zip  | Mobile     |
      | Mr     | TestFName | TestLName | Password1 | 100 Spencer Street | Anchorage | Alaska | 30000 | 0406345454 |


  @regression @validations @emails
  Scenario: Validate invalid email address shows expected errors
    Given Automation practice application is opened
    When I click sign in
    Then I verify the invalid email address shows expected errors
      |EmailAddress|ExpectedErrors|
      |    xyz@    |    Invalid email address.|
      |    xyz@xyz |    Invalid email address.|
      | xyz.com@xyz|    Invalid email address.|
      |xyz@xyz.com |                          |






