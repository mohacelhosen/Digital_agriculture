package com.smart.agriculture.controller;

import com.smart.agriculture.entity.SoilAnalysisEntity;
import com.smart.agriculture.service.SoilAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/soil-analyses")
public class SoilAnalysisController {

    private final SoilAnalysisService soilAnalysisService;

    @Autowired
    public SoilAnalysisController(SoilAnalysisService soilAnalysisService) {
        this.soilAnalysisService = soilAnalysisService;
    }

    @GetMapping
    public List<SoilAnalysisEntity> getAllSoilAnalyses() {
        return soilAnalysisService.getAllSoilAnalyses();
    }

    @GetMapping("/{id}")
    public Optional<SoilAnalysisEntity> getSoilAnalysisById(@PathVariable Integer id) {
        return soilAnalysisService.getSoilAnalysisById(id);
    }

    @PostMapping("/soil-analysis")
    public ResponseEntity<String> createSoilAnalysis(@RequestBody SoilAnalysisEntity soilAnalysis) {
        // Save soil analysis data to the database
        SoilAnalysisEntity savedSoilAnalysis = soilAnalysisService.createSoilAnalysis(soilAnalysis);

        // Generate recommendations based on the saved data
        String recommendations = generateRecommendations(savedSoilAnalysis);

        // Return the recommendations in the response
        return ResponseEntity.ok(recommendations);
    }

    @PutMapping("/{id}")
    public SoilAnalysisEntity updateSoilAnalysis(@PathVariable Integer id, @RequestBody SoilAnalysisEntity updatedSoilAnalysis) {
        return soilAnalysisService.updateSoilAnalysis(id, updatedSoilAnalysis);
    }

    @DeleteMapping("/{id}")
    public void deleteSoilAnalysis(@PathVariable Integer id) {
        soilAnalysisService.deleteSoilAnalysis(id);
    }


    private String generateRecommendations(SoilAnalysisEntity soilAnalysis) {
        double pHLevel = soilAnalysis.getPHLevel();
        double nutrientLevel = soilAnalysis.getNutrientLevel();
        double moistureContent = soilAnalysis.getMoistureContent();
        String soilType = soilAnalysis.getSoilType();

        // Logic to generate recommendations based on soil parameters and type
        StringBuilder recommendations = new StringBuilder("Recommendations based on soil analysis:\n");

        if ("Loam".equalsIgnoreCase(soilType)) {
            recommendations.append("For Loam soil:\n");
            if (pHLevel < 6.0 || pHLevel > 7.5) {
                recommendations.append("- Adjust pH level to the range of 6.0 to 7.5.\n");
            }
            if (nutrientLevel < 20.0) {
                recommendations.append("- Apply a nutrient-rich fertilizer.\n");
            }
            if (moistureContent < 30.0) {
                recommendations.append("- Increase soil moisture through irrigation.\n");
            }
        } else if ("Clay".equalsIgnoreCase(soilType)) {
            recommendations.append("For Clay soil:\n");
            if (pHLevel < 5.5 || pHLevel > 7.0) {
                recommendations.append("- Adjust pH level to the range of 5.5 to 7.0.\n");
            }
            if (nutrientLevel < 25.0) {
                recommendations.append("- Apply a balanced fertilizer.\n");
            }
            if (moistureContent > 35.0) {
                recommendations.append("- Improve drainage to prevent waterlogging.\n");
            }
        } else {
            recommendations.append("Recommendations for this soil type are not available.\n");
        }

        return recommendations.toString();
    }
}
