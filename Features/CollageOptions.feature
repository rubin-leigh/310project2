Feature: CollageOptions properly allows the user to adjust the filter, border, rotation, collage topic, and collage shape letters for generated collages

Background:

	Given we are on the collage viewer page

Scenario: we can change the filter options for the generated collage
	
	Then I should see there is a button called FilterOptions
	Then I should see there is a radio button called "black&white"
	Then I should see there is a radio button called "sepia"
	Then I should see there is a radio button called "grayscale"
	Then I should see there is a radio button called "none"

Scenario: we can change the photo rotation for the generated collage
	
	Then I should see there is a button called PhotoRotation
	Then I should see there is a radio button called "on"
	Then I should see there is a radio button called "off"

Scenario: we can change the photo border for the generated collage
	
	Then I should see there is a button called PhotoBorder
	Then I should see there is a radio button called "on"
	Then I should see there is a radio button called "off"

Scenario: the input box has placeholder text "enter topic"
	
	Then I should see the input box has placeholder equal to "enter topic"





