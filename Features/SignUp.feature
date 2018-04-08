Feature: SignUp

Background:
	Given we are on the login page

Scenario: When I try to signup with a unique username

	When I hit the signup button on the login page
	When I type in unique credentials
	When I hit the signup button
	Then I should be on the CollageViewerPage

Scenario: When I try to signup with a non-unique username

	When I hit the signup button on the login page
	When I type in nonUnique credentials
	When I hit the signup button
	Then I should see that there is an error




