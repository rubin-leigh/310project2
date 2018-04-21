Feature: User log in functionality works properly and is saved outside of web session

Background:

	Given we are on the login page

Scenario: logging in with correct credentials
	
	When I enter a correct username
	When I enter a corresponding correct password for that username
	Then click Log In button
	Then I should be on the CollageViewerPage

	And log out
	Then I should be on the login page

Scenario: logging in with incorrect credentials
	
	When I enter an incorrect username
	When I enter an incorrect password
	Then click Log In button
	Then I should still be on the login page
        

