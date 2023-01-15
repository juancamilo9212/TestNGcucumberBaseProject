Feature: Google Home Page

  @Ignore
  Scenario:Do simple search in Google
    Given I visit google home page
    When I so a simple search for "Test automation"
    Then I will see a "Automatizacion de pruebas" as result title