# javaSeleniumCucumberFramework
Java + Selenium WebDriver 3.3 + TestNG + Cucumber

This is an initial framework created to demonstrate integration between selenium, testng and additional bdd layer (cucumber). It has been created in Page Object Pattern style including Page Factory. Framework is prepared to run in CI as a maven project.
It consists of following:

Java classes

    MainTest - initialize properties and drivers
    TestSteps - test steps binded with cucumber Scenario
    
    HomePage 
    LoggedInUserPage
    MainPage - mostly definitions of Web Elements
 
    WebEventListener - method definitions for driver listeners. 
    
    Runner - runner of tests. User can run tests directly from this file or from maven.

Features

    Scenario - cucumber scenario for William Hill

Properties file

    functional-automated-tests.properties - .exe file paths and binaries paths for browsers


XML file 

    pom - maven file with all dependencies and additional plugin to run as a maven project. It runs any cucumber runner class you want.


Drivers

    chromeDriver.exe - for Chrome 57 and mobile chrome emulator
    IEDriverServer.exe - for IE 11
    geckodriver.exe - for firefox 52

