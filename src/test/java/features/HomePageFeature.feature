@all
Feature: HomePageFeature


	Background:
		Given Site is opened
		
	@smoke
	Scenario: Logo is Present
		Then  Logo is Visible
	
	@smoke
	Scenario: Change Language to Hindi
		And user clicks on language page
		When user selects hindi
		Then Langauge should change