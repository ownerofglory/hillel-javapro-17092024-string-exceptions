package ua.ithillel.strexcp.client;

import ua.ithillel.strexcp.client.response.OpenWeatherResponse;
import ua.ithillel.strexcp.exception.WeatherAppException;

import java.io.IOException;

public interface WeatherClient {
    OpenWeatherResponse getCurrentWeatherDataByQuery(String city) throws Exception;
}
