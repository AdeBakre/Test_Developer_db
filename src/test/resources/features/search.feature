Feature: Sample


  @ui @pageobject
  Scenario: Should be able to search for a product from the input box
    Given John is viewing the Etsy landing page
    When he searches for a product from the input box
    Then the result should be displayed

#  @ui @wip
  Scenario: Should be able to search for a product from the input box (screenplay)
    Given John is viewing the Etsy landing page (screenplay)
    When he searches for a product from the input box (screenplay)
    Then the result should be displayed (screenplay)

  @ui @pageobject
  Scenario Outline: Should be able to search for a product from the drop-down menu
    Given John is viewing the Etsy landing page
    When he clicks on the "<main category>" with "<sub category>"
    Then he should be on the "<sub category>" page

  Examples:
  |main category|sub category|
  |Jewellery|Bracelets   |


  @ui @pageobject
  Scenario Outline: Should be able to search for a product from the icons
    Given John is viewing the Etsy landing page
    When he clicks on "<icon name>"
    Then he should be on the "<icon name>" page
  Examples:
  |icon name|
  |Clothing |
  |Weddings |

  @api @pageobject
  Scenario: Should be able to get all the top categories
    Given John requested a search for all top categories
    Then the service should return results for top categories

  @api @pageobject
    Scenario Outline: Should be able to search for a particular category
    Given John searched for categories by "<search query>" with "<keyword>"
    Then the result should contain categories with "<keyword>"
    Examples:
    |search query|keyword|
    |name        |dolls_and_miniatures|



