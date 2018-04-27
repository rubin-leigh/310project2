Feature: Export

Background:
	Given we are on the collage viewer page

Scenario: Export Button Exists

	Then I should see there is a button called export

Scenario: png and pdf Radio Buttons Exists

	Then I should see there is a button called png
	Then I should see there is a button called pdf

Scenario: when I create a collage and hit the export buttons they work
	
	When we create a new collage "cat"
	Then I hit the png download button
	Then I hit the pdf download button
