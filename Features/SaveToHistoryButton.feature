Feature: SaveToHistoryButton properly saves the current collage when clicked

Background:

	Given we are on the collage viewer page

Scenario: SaveToHistoryButton shouldn't be clickable
	
	Then SaveToHistoryButton shouldn't be clickable

Scenario: once a collage has been generated, clicking SaveToHistoryButton saves the current collage
	
	When we create a new collage ""
	Then we click SaveToHistoryButton
	Then we should see the PreviousCollageViewer has 1 new element


        

