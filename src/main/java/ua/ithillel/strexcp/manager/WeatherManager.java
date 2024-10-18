package ua.ithillel.strexcp.manager;

import ua.ithillel.strexcp.client.WeatherClient;
import ua.ithillel.strexcp.client.response.OpenWeatherResponse;
import ua.ithillel.strexcp.exception.WeatherAppException;
import ua.ithillel.strexcp.model.Weather;

import java.io.IOException;

public class WeatherManager {
    private final WeatherClient weatherClient;

    public WeatherManager(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public Weather getWeatherByCity(String city) throws WeatherAppException, Exception {
        OpenWeatherResponse currentWeatherDataByQuery = weatherClient.getCurrentWeatherDataByQuery(city);

        double temp = currentWeatherDataByQuery.getMain().getTemp();
        double humidity = currentWeatherDataByQuery.getMain().getHumidity();

        Weather weather = new Weather();
        weather.setTemperature(temp);
        weather.setHumidity(humidity);

        String iconUrl = String.format("https://openweathermap.org/img/wn/%s@2x.png", currentWeatherDataByQuery.getWeather()[0].getIcon());
        weather.setIconUrl(iconUrl);

        String description = currentWeatherDataByQuery.getWeather()[0].getMain() + ": " + currentWeatherDataByQuery.getWeather()[0].getDescription();
        weather.setDescription(description);

        return weather;
    }
}
