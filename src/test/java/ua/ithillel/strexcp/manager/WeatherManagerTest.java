package ua.ithillel.strexcp.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.ithillel.strexcp.client.WeatherClient;
import ua.ithillel.strexcp.client.response.OpenWeatherResponse;
import ua.ithillel.strexcp.exception.WeatherAppException;
import ua.ithillel.strexcp.model.Weather;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class WeatherManagerTest {
    private WeatherManager weatherManager;

    @Mock
    private WeatherClient weatherClientMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        weatherManager = new WeatherManager(weatherClientMock);
    }

    @Test
    void testGetWeatherByCity_Ok() throws Exception {
        OpenWeatherResponse mockResponse = getMockResponse();

        when(weatherClientMock.getCurrentWeatherDataByQuery(anyString()))
                .thenReturn(mockResponse);

        Weather weatherByCity = weatherManager.getWeatherByCity("Test city");

        assertNotNull(weatherByCity);
    }

    @Test
    void testGetWeatherByCity_throwsException() throws Exception {
        OpenWeatherResponse mockResponse = getMockResponse();

        when(weatherClientMock.getCurrentWeatherDataByQuery(anyString()))
                .thenThrow(new WeatherAppException("test exception"));

        assertThrows(WeatherAppException.class, () -> {
            Weather weatherByCity = weatherManager.getWeatherByCity("Test city");
        });
    }

    private OpenWeatherResponse getMockResponse() {
        try(
                InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("openweather-mock-resp.json");
                InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String content = bufferedReader.lines().reduce("", (acc, val) -> acc + val + "\n");

            ObjectMapper objectMapper = new ObjectMapper();
            OpenWeatherResponse openWeatherResponse = objectMapper.readValue(content, OpenWeatherResponse.class);

            return openWeatherResponse;
        }  catch (IOException e) {
            throw new RuntimeException("Test set up failed. File not found");
        }
    }
}
