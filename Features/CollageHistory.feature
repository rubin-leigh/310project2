Feature: CollageHistory

Background:
	Given we are on the collage viewer page

Scenario: Collage History should Persist
	
	When we create a new collage "cat"
	And we click SaveToHistoryButton
	And log out
	Then I should be on the login page
	And log in
	Then we should see the collage "cat" in the PreviousCollageViewer
	And click the delete button
	
Scenario: Collage Swapping
	When we create a new collage "cat"
	And we click SaveToHistoryButton
	When we create a new collage "cat"
	And click the collage "cat" in the PreviousCollageViewer
	Then the main collage should display the collage for "cat"

Scenario: Delete Collage
	When we create a new collage "owl"
	And we click SaveToHistoryButton
	And click the delete button
	Then we should NOT see the collage "owl" in the PreviousCollageViewer
