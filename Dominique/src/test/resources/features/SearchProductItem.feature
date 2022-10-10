@searchProduct
Feature: Search Product

  Scenario Outline: "<ScenarioDescription>"
    Given Land on the site
    When I can search product item "<ProductItem>"
    Then I can assert the search results "<ProductItem>"
    And I can validate the deal "<Price>" "<Contract>" "<AvailableOnline>"
    Then I can get the deal and validate order summary "<ProductItem>" "<Contract>" "<AvailableOnline>"

    Examples:
      | TC | ScenarioDescription                                    | ScenarioType | TestType | ProductItem                                            | Price   | Contract  | AvailableOnline  |
      | 1  | User successfully search Apple iPhone 11 64GB          | Positive     | E2E      | Apple iPhone 11 64GB                                   | R399 PM | 36 months | Online Exclusive |
      | 1  | Samsung Galaxy S21 FE 5G DS + Samsung Galaxy Buds Live | Positive     | E2E      | Samsung Galaxy S21 FE 5G DS + Samsung Galaxy Buds Live | R399 PM | 36 months | Online Exclusive |


# you can add any additional test scenarios by changing the product and number of items