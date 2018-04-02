Feature: Export

Background:
	Given we are on the collage viewer page

Scenario: Export Button Exists

	Then I should see there is a button called "export"

Scenario: png and pdf Radio Buttons Exists

	Then I should see there is a radio button called "png"
	Then I should see there is a radio button called "pdf"
