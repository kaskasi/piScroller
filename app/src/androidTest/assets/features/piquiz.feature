Feature: Quiz
  The app gives direct visual confirmation
  if quiz was answered correctly

  Scenario Outline: Input answer
    Given I have a PiScroller
    When I open the Quiz
    When I input answer <answer>
    Then I should <see> text color is green

    Examples:
      | answer             | see   |
      | 3                  | true  |
      | 4                  | false |