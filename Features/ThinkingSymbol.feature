Feature: ThinkingSymbol

Background:
	Given we are on the collage viewer page

Scenario: Thinking Symbol

	When we submit a new collage for "cat"
	Then we should see a loading image
