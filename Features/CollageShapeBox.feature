Feature: CollageShapeBox properly uses user input to generate a collage in the given shape of the letters given by the user

Background:

	Given we are on the collage viewer page

Scenario: generating a new collage with a given string of letters, properly uses that letter in collage generation
	
	When a I generate a new collage 

Scenario: once a collage has been generated, clicking SaveToHistoryButton saves the current collage
	
	When we create a new collage ""
	Then the current collage should have alt text ""

