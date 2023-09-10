package com.smart.agriculture.repository;

import com.smart.agriculture.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Integer> {
}
