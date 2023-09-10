package com.smart.agriculture.repository;

import com.smart.agriculture.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<CropEntity, Integer> {
}
