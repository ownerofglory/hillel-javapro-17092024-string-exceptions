package ua.ithillel.strexcp.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse {
    private OpenWeatherMainWeather main;
    private OpenWeatherDescription[] weather;

    public OpenWeatherMainWeather getMain() {
        return main;
    }

    public void setMain(OpenWeatherMainWeather main) {
        this.main = main;
    }

    public OpenWeatherDescription[] getWeather() {
        return weather;
    }

    public void setWeather(OpenWeatherDescription[] weather) {
        this.weather = weather;
    }
}
