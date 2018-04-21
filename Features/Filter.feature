Feature: The filter options of the Collage can be properly adjusted

Background:

	Given we are on the collage viewer page

Scenario: when the black and white option is checked the collage properly adjusts
	
	When I hit black and white
	When we create a new collage "cat"
	Then I should see the correct black and white collage adjustment

Scenario: when the sepia option is checked the collage properly adjusts
	
	When I hit sepia
	When we create a new collage "cat"
	Then I should see the correct sepia collage adjustment

Scenario: when the none option is checked the collage properly adjusts
	
	When I hit none
	When we create a new collage "cat"
	Then I should see the correct none collage adjustment

Scenario: when the grayscale option is checked the collage properly adjusts
	
	When I hit grayscale
	When we create a new collage "cat"
	Then I should see the correct grayscale collage adjustment

        

