Feature: Verify flight booking on ibibo

@goibibo
  Scenario: Verify sorting in goibibo
    Given I launch goibibo
  When I Enter from "Hyd" and to "Maa" details
  And I Enter traveller details
  Then Verify search results

