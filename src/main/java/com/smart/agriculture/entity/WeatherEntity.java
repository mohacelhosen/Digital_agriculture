package com.smart.agriculture.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Location Information Fields
    private double latitude;
    private double longitude;
    private String placeName;

    // Time Frame Fields
    private String startDate;
    private String endDate;

    // Weather Parameters Fields
    private boolean includeTemperature;
    private double temperature;

    private boolean includePrecipitation;

    private boolean includeHumidity;
    private double humidity;

    private boolean includeWindSpeed;
    private double windSpeed;

    // Recommendation field to store recommendations
    private String recommendations;
}
