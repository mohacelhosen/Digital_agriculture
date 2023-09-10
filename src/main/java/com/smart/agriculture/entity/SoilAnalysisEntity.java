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
public class SoilAnalysisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Location Information Fields
    private double latitude;
    private double longitude;
    private String placeName;

    // Date of Analysis
    private String analysisDate;

    // Soil Parameters Fields
    private double pHLevel;
    private double nutrientLevel;
    private double moistureContent;
    private String soilType;

}
