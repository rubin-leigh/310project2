Feature: BuildCollageButton generates a collage when user has supplied necessary inputs and displays it in the main collage viewer window

Background:

	Given we are on the collage viewer page

Scenario: when a collage generated has insufficient number of images, we see an error message

	When we create a new collage "cat"
	Then I should see error text appear

Scenario: when a user gives valid inputs and clicks BuildCollageButton, a collage is generated

	When we create a new collage "cat"
	Then I should see the main collage viewer has a collage





