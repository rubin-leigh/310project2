Feature: Export

Background:
	Given we are on the collage viewer page

Scenario: Export Button Exists

	When we create a new collage "Button"
	Then I should see there is a buttton called "export"

Scenario: png and pdf Radio Buttons Exists

	When we create a new collage "Radio"
	Then I should see there is a radio buttton called "png"
	Then I should see there is a radio buttton called "pdf"
