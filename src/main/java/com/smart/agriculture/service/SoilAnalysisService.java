package com.smart.agriculture.service;

import com.smart.agriculture.entity.SoilAnalysisEntity;
import com.smart.agriculture.repository.SoilAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoilAnalysisService {

    private final SoilAnalysisRepository soilAnalysisRepository;

    @Autowired
    public SoilAnalysisService(SoilAnalysisRepository soilAnalysisRepository) {
        this.soilAnalysisRepository = soilAnalysisRepository;
    }

    public List<SoilAnalysisEntity> getAllSoilAnalyses() {
        return soilAnalysisRepository.findAll();
    }

    public Optional<SoilAnalysisEntity> getSoilAnalysisById(Integer id) {
        return soilAnalysisRepository.findById(id);
    }

    public SoilAnalysisEntity createSoilAnalysis(SoilAnalysisEntity soilAnalysis) {
        return soilAnalysisRepository.save(soilAnalysis);
    }

    public SoilAnalysisEntity updateSoilAnalysis(Integer id, SoilAnalysisEntity updatedSoilAnalysis) {
        if (soilAnalysisRepository.existsById(id)) {
            updatedSoilAnalysis.setId(id);
            return soilAnalysisRepository.save(updatedSoilAnalysis);
        } else {
            throw new RuntimeException("Soil analysis not found with id: " + id);
        }
    }

    public void deleteSoilAnalysis(Integer id) {
        soilAnalysisRepository.deleteById(id);
    }
}
