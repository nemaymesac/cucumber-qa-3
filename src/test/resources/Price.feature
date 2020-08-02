Feature: Check price on 1a.lv

  Scenario Outline: Max price test
    Given we on 1a.lv home page
    And we hover 'Sports un fitness'
    And click on 'Velosipēdi'
    When I put '<min price>' and '<max price>'
    Then Items on page equals '<number>'
    Examples:
      | min price | max price | number |
      | 0         | 50        | 57     |
      | 50        | 100       | 107    |
      | 100       | 250       | 135    |
      | 250       | 1000      | 328    |


