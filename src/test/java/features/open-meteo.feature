Feature: GetWellingtonWeatherData

  @Positive
  Scenario Outline: Get weather data based on the provided parameters
    When I send "GET" to retrieve weather data
    | key       | value          |
    | latitude  | <latitude>     |
    | longitude | <longitude>    |
    | hourly    | <hourly>       |
    | daily     | <daily>        |
    | timezone  | <timezone>     |
    Then I receive a valid HTTP response code 200
    And I can verify that the json schema is correct
    And I can verify that the response data is correct

    Examples:
      | test name                          | latitude     | longitude      | hourly              |  daily              | timezone           |
      | Wellington - required fields only  | -41.29       | 174.78         |                     |                     |                    |
      | Wellington - with weather data     | -41.29       | 174.78         | temperature_2m      |                     |                    |
      | Auckland - required fields only    | -36.85       | 174.76         |                     |                     |                    |
      | Auckland - with weather data       | -36.85       | 174.76         | temperature_2m      |                     |                    |
      | Auckland - with daily variables    | -36.85       | 174.76         | temperature_2m      | weathercode,sunrise | Pacific/Auckland   |
      | Auckland - with daily variables    | -36.85       | 174.76         | temperature_2m      |                     | Pacific/Auckland   |


  @Negative
  Scenario Outline: Validate negative scenarios
    When I send "GET" to retrieve weather data
      | key       | value          |
      | latitude  | <latitude>     |
      | longitude | <longitude>    |
      | hourly    | <hourly>       |
      | daily     | <daily>        |
      | timezone  | <timezone>     |
    Then I receive a valid HTTP response code 400
    And I can verify that the error "<reason>" is correct

    Examples:
      | test name               | latitude     | longitude      | hourly              |  daily              | timezone           | reason                                                        |
      | w/o mandatory           |              |                |                     |                     |                    | Value of type 'Float' required for key 'latitude'.            |
      | w/o latitude            |              | 174.78         |                     |                     |                    | Value of type 'Float' required for key 'latitude'.            |
      | w/o longitude           | -41.29       |                |                     |                     |                    | Value of type 'Float' required for key 'longitude'.           |
      | invalid latitude        |  100         | 174.78         |                     |                     |                    | Latitude must be in range of -90 to 90°. Given: 100.0.        |
      | invalid longitude       | -41.29       | 200            |                     |                     |                    | Longitude must be in range of -180 to 180°. Given: 200.0.     |
      | Auckland - w/o timezone | -36.85       | 174.76         | temperature_2m      | weathercode,sunrise |                    | Timezone is required                                          |


