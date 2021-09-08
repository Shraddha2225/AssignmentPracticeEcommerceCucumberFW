Feature: Change in the image using Color Feature

  @color
  Scenario: User able to change the color in the image
    Given User signed in
    When User click on color blue
    Then Display image should be changed in blue color

