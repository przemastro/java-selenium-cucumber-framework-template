@smokeTest
Feature: Scenario

  Scenario Outline: My first Cucumber test Scenario
    Given I navigate to http://sports.williamhill.com/sr-admin-set-white-list-cookie.html page
    When I login with WHATA_FOG2 and F0gUaTtest
    And I navigate to <sport> event
    And I Add the first active selection to the betslip
    And I Place a <bet> pounds bet
    Then To return: value is equal <toReturn>
    And Total stake: value is equal <totalStake>
    And user balance is updated by <bet>

    Examples:
      | sport    | bet  | toReturn    | totalStake |
      | football | 0.05 | (odd+1)*bet | 0.05       |

    # Test data
    #| tennis   | 0.05 | (odd+1)*bet | 0.05       |