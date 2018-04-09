Feature: CollageHistory

Background:
	Given we are on the collage viewer page

Scenario: Collage should be saved in History

	When we create a new collage "History"
	And click the save button
	Then we should see the collage "History" in the PreviousCollageViewer

Scenario: Collage History should Persist
	
	When we create a new collage "Persist"
	And click the save button
	And log out
	And log in
	Then we should see the collage "Persist" in the PreviousCollageViewer
	
Scenario: Collage Swapping
	When we create a new collage "Swap"
	And click the save button
	When we create a new collage "Garbage"
	And click the collage "Swap" in the PreviousCollageViewer
	Then the main collage should display the collage for "Swap"

Scenario: Delete Collage
	When we create a new collage "Delete"
	And click the save button
	And click the delete button
	Then we should NOT see the collage "Delete" in the PreviousCollageViewer
