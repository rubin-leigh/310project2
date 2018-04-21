Feature: CollageShapeBox properly uses user input to generate a collage in the given shape of the letters given by the user

Background:

	Given we are on the collage viewer page

Scenario: generating a new collage with a given string of letters, properly uses that letter in collage generation
	
	When we create a new collage "cat"
	Then the current collage should have alt text "t"



