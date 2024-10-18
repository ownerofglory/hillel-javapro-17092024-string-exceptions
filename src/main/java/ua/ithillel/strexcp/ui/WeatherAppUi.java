package ua.ithillel.strexcp.ui;


import ua.ithillel.strexcp.exception.WeatherAppException;
import ua.ithillel.strexcp.exception.WeatherAppSetupException;
import ua.ithillel.strexcp.manager.WeatherManager;
import ua.ithillel.strexcp.model.Weather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URI;

// --------- Weather App -------
//  City:   |  Kyiv |
//  ______
// | Get |
// ------
//          Error: city does not exist
// Temprature:    12 C
// Humidity:      65%
// icon

public class WeatherAppUi extends JFrame {
    private final WeatherManager weatherManager;

    private JLabel cityLabel = new JLabel("City");
    private JTextField cityTextField = new JTextField(15);
    private JButton getWeatherButttom = new JButton("Get weather");

    private JLabel messageLabel = new JLabel("");
    private JLabel temperatureLabel = new JLabel("Temperature");
    private JLabel temperatureValueLabel = new JLabel("");

    private JLabel humidityLabel = new JLabel("Humidity");
    private JLabel humidityValueLabel = new JLabel("");

    private JLabel weatherIcon = new JLabel();

    {
        setSize(400, 300);
        setTitle("Weather App");
        setLayout(new GridLayout(6, 2));

        // code
        add(cityLabel);
        add(cityTextField);

        add(getWeatherButttom);
        add(new JLabel());

        add(messageLabel);
        add(new JLabel());

        add(temperatureLabel);
        add(temperatureValueLabel);

        add(humidityLabel);
        add(humidityValueLabel);

        add(weatherIcon);
        add(new JLabel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public WeatherAppUi(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;

        getWeatherButttom.addActionListener(event -> {
            try {
                String city = cityTextField.getText();

                Weather weatherByCity = weatherManager.getWeatherByCity(city);

                temperatureValueLabel.setText(String.valueOf(weatherByCity.getTemperature()) + "°C");
                humidityValueLabel.setText(String.valueOf(weatherByCity.getHumidity()) + "%");
                messageLabel.setText(weatherByCity.getDescription());

                URI iconUri = new URI(weatherByCity.getIconUrl());
                weatherIcon.setIcon(new ImageIcon(iconUri.toURL()));
            } catch (WeatherAppSetupException e) {
                System.out.println("Failed to set up");
            } catch (WeatherAppException e) {
                System.out.println("Weather app exception");
                cityTextField.setText("");
                messageLabel.setText(e.getMessage());
                temperatureValueLabel.setText("");
                humidityValueLabel.setText("");
                weatherIcon.setIcon(null);
            } catch (Exception e) {
                System.out.println();
            } finally {
                System.out.println("Button click handler finished");
            }
        });
    }
}
