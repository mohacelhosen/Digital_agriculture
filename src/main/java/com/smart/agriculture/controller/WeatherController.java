package com.smart.agriculture.controller;

import com.smart.agriculture.entity.WeatherEntity;
import com.smart.agriculture.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/weather")
public class WeatherController {
    @Autowired
    private WeatherService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveWeatherInfo(@RequestBody WeatherEntity weather) {
        // Your save logic here
        WeatherEntity entity = service.save(weather); // Save the entity

        // Example of generating recommendations based on weather conditions
        StringBuilder recommendations = new StringBuilder();

        if (entity.isIncludeTemperature()) {
            double temperature = entity.getTemperature(); // Get temperature data from 'entity' object;
            if (temperature > 30) {
                recommendations.append("It's going to be hot. Consider providing shade and extra irrigation for heat-sensitive crops. ");
            } else if (temperature < 10) {
                recommendations.append("Expect cold temperatures. Use protective coverings or greenhouses to prevent frost damage. ");
            }
        }

        if (entity.isIncludePrecipitation()) {
            boolean precipitation = true; // Get precipitation data from 'entity' object;
            recommendations.append("Rain is expected. Ensure proper drainage to prevent waterlogging. ");
        }

        if (entity.isIncludeHumidity()) {
            double humidity = entity.getHumidity(); // Get humidity data from 'entity' object;
            if (humidity > 80) {
                recommendations.append("High humidity can lead to fungal diseases. Maintain good air circulation. ");
            }
        }

        if (entity.isIncludeWindSpeed()) {
            double windSpeed = entity.getWindSpeed(); // Get wind speed data from 'entity' object;
            if (windSpeed > 30) {
                recommendations.append("Strong winds are forecasted. Secure lightweight structures like greenhouses. ");
            }
        }

        // Additional Recommendations for Crop Health
        // You can add more specific recommendations here based on weather conditions

        if (entity.isIncludeTemperature() && entity.isIncludeHumidity()) {
            if (entity.getTemperature() > 25 && entity.getHumidity() > 70) {
                recommendations.append("Optimal conditions for fungal diseases. Apply appropriate fungicides and monitor crops closely. ");
            }
        }

        if (entity.isIncludePrecipitation()) {
            if ( entity.getHumidity() > 60) {
                recommendations.append("Persistent rain and high humidity can increase the risk of mold and rot. Consider using fungicides. ");
            }
        }

        // Include other recommendations based on your data and requirements

        // Update the 'entity' object with recommendations
        entity.setRecommendations(recommendations.toString());

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }



    @PutMapping("/update")
    public ResponseEntity<?> updateWeatherInfo(@RequestBody WeatherEntity weather){
        // Your update logic here

        // Recommendation: Based on weather conditions, provide advice to the user
        // You can access weather data in the 'weather' object and generate recommendations accordingly

        WeatherEntity saved = service.update(weather);
        return  new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @GetMapping("/{weatherId}")
    public ResponseEntity<?> findWeatherInfoById(@PathVariable Integer weatherId){
        WeatherEntity saved = service.getWeatherInfo(weatherId);

        // Recommendation: Based on weather conditions, provide advice to the user
        // You can access weather data in the 'saved' object and generate recommendations accordingly

        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllWeatherInfo(){
        List<WeatherEntity> savedList = service.getAllWeatherInfo();

        // Recommendation: You can provide general weather-related advice here
        // You might consider iterating through 'savedList' to generate recommendations based on the list of weather data

        return new ResponseEntity<>(savedList, HttpStatus.OK);
    }

    @DeleteMapping("/{weatherId}")
    public ResponseEntity<?> deleteWeatherInfoById(@PathVariable Integer weatherId){
        Boolean isDelete = service.delete(weatherId);

        // Recommendation: You can provide a confirmation message here based on 'isDelete'

        return new ResponseEntity<>(isDelete, HttpStatus.OK);
    }
}
