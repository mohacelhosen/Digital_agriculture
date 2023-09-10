package com.smart.agriculture.repository;

import com.smart.agriculture.entity.SoilAnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoilAnalysisRepository extends JpaRepository<SoilAnalysisEntity, Integer> {
}
