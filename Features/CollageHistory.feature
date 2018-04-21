Feature: CollageHistory

Background:
	Given we are on the collage viewer page

Scenario: Collage History should Persist
	
	When we create a new collage "cat"
	And we click SaveToHistoryButton
	And log out
	And log in
	Then we should see the collage "cat" in the PreviousCollageViewer
	
Scenario: Collage Swapping
	When we create a new collage "Swap"
	And we click SaveToHistoryButton
	When we create a new collage "Garbage"
	And click the collage "Swap" in the PreviousCollageViewer
	Then the main collage should display the collage for "Swap"

Scenario: Delete Collage
	When we create a new collage "GetRid"
	And we click SaveToHistoryButton
	And click the delete button
	Then we should NOT see the collage "GetRid" in the PreviousCollageViewer
