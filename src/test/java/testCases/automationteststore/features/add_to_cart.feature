Feature: Add Dove Item to Cart
  As a user
  I want to go to the home page on website and add items to the cart
#  So that I can scroll the page, select a Dove item from the brands scrolling list, and add it to the cart
  Background:
    Given I am logged in to the website

  Scenario: Add Dove Item to Cart
    Given If any item in cart then I Delete Items From Cart
    And Go to the home page
    When I scroll the page and click the brand:DOVE
    And I will sort the items by Date Old > New
    And I add the select newest item and to the cart
    Then I verify that the Dove item is added to the cart

  Scenario: Add Shirts and shoes to cart
    Given If any item in cart then I Delete Items From Cart
    When I go to the APPAREL & ACCESSORIES section
    And I go to Tshirts
    And I sort the Tshirts by Price low to high
    And I select the top three lowest value Tshirts
    And I go back to the APPAREL & ACCESSORIES section
    And I go to the shoes section
    And I sort the Tshirts by Price high to low
    And I add the highest value shoe to the cart with quantity two
    Then I verify that selected items should be in the cart


  Scenario: Add product to cart ending with M in men category
    Given If any item in cart then I Delete Items From Cart
    When I go to the Men section
    And I add the product ending with M to the cart
    Then  verify that the item in the cart should end with M


  Scenario: Add each makeup and men sale items with max price 200$,£ Or €. And add 15 items in total with Nonsale items
    Given If any item in cart then I Delete Items From Cart
    When I change Currency to Euro
    And Go to the All Makeup Sub Pages And Add Sale Items
    And Go to the All Men Sub Pages And Add Sale Items
    And I go to the cart page
    And Check if amount > than 200€ delete 1 item then check again repeat till price <=200
    And Change currency to dollar
    And Check if amount > than $200 delete 1 item then check again repeat till price <=200
    And Change currency to pound
    And Check if amount > than £200 delete 1 item then check again repeat till price <=200
    And check if item count is less than 15 then add All NonSale items under sections of Men & Makeup
    Then  verify that the item in the cart should be 15 and total amount should be equal to previous total + new items amount



