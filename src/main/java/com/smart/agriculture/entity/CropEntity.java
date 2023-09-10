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
public class CropEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Location Information Fields
    private double latitude;
    private double longitude;
    private String placeName;

    // Crop Information Fields
    private String cropName;
    private String plantingDate;
    private String harvestingDate;
    private double cropHealth; // You can use a scale (e.g., 0-100) to represent health.

    private String status; // e.g., "Growing," "Harvested," "Diseased," etc.
}
