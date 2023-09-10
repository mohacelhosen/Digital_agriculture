package com.smart.agriculture.controller;

import com.smart.agriculture.entity.CropEntity;
import com.smart.agriculture.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crops")
public class CropController {

    private final CropService cropService;

    @Autowired
    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @GetMapping
    public List<CropEntity> getAllCrops() {
        return cropService.getAllCrops();
    }

    @GetMapping("/{id}")
    public Optional<CropEntity> getCropById(@PathVariable Integer id) {
        return cropService.getCropById(id);
    }

    @PostMapping("/crops")
    public ResponseEntity<String> createCrop(@RequestBody CropEntity crop) {
        // Save crop data to the database
        CropEntity savedCrop = cropService.createCrop(crop);

        // Generate recommendations based on the saved data
        String recommendations = generateRecommendations(savedCrop);

        // Return the recommendations in the response
        return ResponseEntity.ok(recommendations);
    }

    private String generateRecommendations(CropEntity crop) {
        String cropName = crop.getCropName();
        String plantingDate = crop.getPlantingDate();
        String harvestingDate = crop.getHarvestingDate();
        double cropHealth = crop.getCropHealth();

        // Logic to generate recommendations based on crop parameters and type
        StringBuilder recommendations = new StringBuilder("Recommendations for crop:\n");

        if ("Tomato".equalsIgnoreCase(cropName)) {
            recommendations.append("For Tomato crop:\n");
            if (cropHealth < 70.0) {
                recommendations.append("- Monitor and address any signs of disease or pest infestation.\n");
            }
            if (plantingDate.equals("2023-03-15")) {
                recommendations.append("- Ensure proper irrigation and fertilization during the growing season.\n");
            }
            if (harvestingDate.equals("2023-06-15")) {
                recommendations.append("- Harvest ripe tomatoes on time to maintain quality.\n");
            }
        } else if ("Wheat".equalsIgnoreCase(cropName)) {
            recommendations.append("For Wheat crop:\n");
            if (cropHealth < 60.0) {
                recommendations.append("- Check for wheat rust and apply appropriate fungicides if necessary.\n");
            }
            if (plantingDate.equals("2023-11-01")) {
                recommendations.append("- Plant wheat seeds at the recommended depth.\n");
            }
            if (harvestingDate.equals("2024-04-30")) {
                recommendations.append("- Harvest wheat when it reaches the optimum moisture content.\n");
            }
        } else {
            recommendations.append("Recommendations for this crop are not available.\n");
        }

        return recommendations.toString();
    }


    @PutMapping("/{id}")
    public CropEntity updateCrop(@PathVariable Integer id, @RequestBody CropEntity updatedCrop) {
        return cropService.updateCrop(id, updatedCrop);
    }

    @DeleteMapping("/{id}")
    public void deleteCrop(@PathVariable Integer id) {
        cropService.deleteCrop(id);
    }
}
