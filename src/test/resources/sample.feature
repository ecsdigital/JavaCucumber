Feature: Demo capabilities of Selenium
  As a test engineer
  I want to show a selenium example
  So that I can give people an idea of how it works

  Scenario: Do a basic google search
    Given I've opened the google home page
    When I search for 'gherkin'
    Then there should be a result titled 'Pickled cucumber - Wikipedia'