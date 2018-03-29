Feature: CollageHistory

Background:
	Given we are on the collage viewer page

Scenario: Thinking Symbol

	When we create a new collage "Thinking Emoji"
	Then we should see a loading image
