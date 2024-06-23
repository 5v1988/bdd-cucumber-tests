@Smoke @PlaceOrder
Feature: Search products and place an order

  Scenario Outline: Search product and place an order
    Given User opens home page: url
    And User searches for the product: "<productName>"
    And User verifies that the product: "<productName>" displays in the results
    When User chooses the product: "<productName>" displays in the results
    And User adds a chosen product to the cart
    And User proceeds to checkout from the cart
    And User enters the following personal shipping info:
      | email     | e@e.com |
      | firstName | Veera   |
      | lastName  | V       |
    And User enters the following shipping address info:
      | street1 | city | state | zip | country | phoneNumber |
      |         |      |       |     |         |             |
    And User chooses shipping method: "<shippingMethod>" and proceeds next
    Then User reviews payments and places order
    And User verifies that order confirmation: "<message>" is displayed

    Examples:
      | productName     | shippingMethod | message                                                               |
      | Driven Backpack | Best Way       | We'll email you an order confirmation with details and tracking info. |