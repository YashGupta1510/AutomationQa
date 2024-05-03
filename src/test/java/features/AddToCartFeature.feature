@all
Feature: Add To Cart Feature

	@regression
  Scenario Outline: add to cart success
    Given user is on homepage
    And user searched for "<Product>"
    When user clicked on add to cart button
    Then cart item count should increase
    
    Examples: 
      | Product	|
      | redmi 10 pro	|   
      | lenovo laptop	|   
