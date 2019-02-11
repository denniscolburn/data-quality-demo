@hadoop
Feature: Data quality feature

  @device_code @data_quality @detective
  Scenario Outline: Data quality scenario
    When I look for intervals with null "device codes" for authority "<authority>"
    Then I should not find any intervals with null "device codes" for "<authority>"
    Examples:
      | authority |
      | 10        |
#      | 04        |
#      | 07        |
#      | 94        |
#      | 95        |
#      | 97        |