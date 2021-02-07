# JavaSeleniumCucumber Test Framework
[![GitHub issues](https://img.shields.io/github/issues/przemastro/java-selenium-cucumber-framework)](https://github.com/przemastro/java-selenium-cucumber-framework/issues)
[![GitHub forks](https://img.shields.io/github/forks/przemastro/java-selenium-cucumber-framework)](https://github.com/przemastro/java-selenium-cucumber-framework/network)
[![GitHub stars](https://img.shields.io/github/stars/przemastro/java-selenium-cucumber-framework)](https://github.com/przemastro/java-selenium-cucumber-framework/stargazers)
[![Java version](https://img.shields.io/badge/Java-1.8-%23b07219)](https://github.com/przemastro/java-selenium-cucumber-framework)
[![Chrome version](https://img.shields.io/badge/Chrome-57-brightgreen)](https://github.com/przemastro/java-selenium-cucumber-framework)
[![IE version](https://img.shields.io/badge/IE-11-brightgreen)](https://github.com/przemastro/java-selenium-cucumber-framework)
[![Firefox version](https://img.shields.io/badge/Firefox-52-brightgreen)](https://github.com/przemastro/java-selenium-cucumber-framework)

# Features
This is an initial framework created to demonstrate integration between selenium, testng and additional bdd layer (cucumber). I created it when applied for Dev in Test position at William Hill. It has been made in Page Object Pattern style including Page Factory.

1. Integrate pom.xml with CI. You can run tests from maven pom.xml as well as directly from Runner class
2. Run tests against 3 browsers and in addition against chrome mobile simply by enabling flags in properties file
3. Add single bet math formula into test scenario.
4. Run Scenario with different test data

# Installation

1. Open repo in your favourite IDE (I use Intellij because of built-in Maven) and set Project SDK to "java version 1.8.0_*"
2. Right click on pom.xml and set MAVEN project, you might need to re-import maven dependencies

# Run

Run maven build

# Usage

Example Test Feature file

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


    

