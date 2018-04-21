Feature: Height and Width of the Collage can be properly adjusted

Background:

	Given we are on the collage viewer page

Scenario: the collage viewer page should have a height input and width input
	
	Then I should see the height input box
	Then I should see the width input box

Scenario: the collage viewer page should take a height and width input and return a subsequent collage with the specified values
	
	When I enter 50 in the height input box
	When I enter 50 in the width input box
	When we create a new collage "cat"
	Then I should see the collage has a height and width corresponding to those values


        

