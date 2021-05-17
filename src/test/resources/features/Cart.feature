
Feature: Verify Cart works in application

    @regression @cart
    Scenario Outline: Verify Cart works in application
      Given Automation practice application is opened
      Then I verify the mega menu is working
        |Menu  |
        |<Menu>|
      Then I add product to cart from Quick View
        |Name  |Color  |Quantity  |
        |<Name>|<Color>|<Quantity>|
      Then I verify the product is added in cart
        |Name  |Color  |Quantity  |
        |<Name>|<Color>|<Quantity>|

      Examples:
       |Menu                  |Name                |Color|Quantity|
       |DRESSES/SUMMER DRESSES|Printed Summer Dress|Blue |1       |

