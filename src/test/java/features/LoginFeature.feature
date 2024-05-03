@all @login
Feature: Login Functionality

	Background:
		Given user is on Login Page

@valid
	Scenario Outline: Valid Login
		When user enter username "<Username>"
		When user enter password "<Password>"
		Then user moves to solve page
		Examples:
		|Username|Password|
		|test@test.com|pwdpwdpwd|
		|test2@test.com|pwspwpspws|
		|test3@test.com|pwdpwdpwd|
		|test4@test.com|pwspwpspws|
		
@invalid
	Scenario Outline: Invalid Login
		When user enter username "<Username>"
		Then user gets error
		Examples:
		|Username|
		|testtest.com|
		|test2@test|
		|dadsdad|
