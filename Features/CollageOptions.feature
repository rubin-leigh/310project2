Feature: CollageOptions properly allows the user to adjust the filter, border, rotation, collage topic, and collage shape letters for generated collages

Background:

	Given we are on the collage viewer page

Scenario: we can change the filter options for the generated collage

	Then I should see there is a radio button with the black and white option
	Then I should see there is a radio button with the sepia option
	Then I should see there is a radio button with the grayscale option
	Then I should see there is a radio button with the none option

Scenario: we can change the photo rotation for the generated collage
	
	Then I should see there is a radio button with the on option for rotation
	Then I should see there is a radio button with the off option for rotation

Scenario: we can change the photo border for the generated collage
	
	Then I should see there is a radio button with the on option for photo border
	Then I should see there is a radio button with the off option for photo border

Scenario: the input box has placeholder text "enter topic"
	
	Then I should see the input box has placeholder equal to enter topic





