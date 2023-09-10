package com.smart.agriculture.service;

import com.smart.agriculture.entity.WeatherEntity;
import com.smart.agriculture.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository repository;

    public WeatherEntity save(WeatherEntity weather){
        return repository.save(weather);
    }

    public WeatherEntity update(WeatherEntity weather){
        return repository.save(weather);
    }
    public Boolean delete(Integer weatherId){
        Optional<WeatherEntity> weather = repository.findById(weatherId);
        if (weather.isPresent()){
             repository.delete(weather.get());
             return true;
        }
        return false;
    }

    public WeatherEntity getWeatherInfo(Integer weatherId){
        Optional<WeatherEntity> weather = repository.findById(weatherId);
        return weather.orElse(null);
    }

    public List<WeatherEntity> getAllWeatherInfo(){
        return repository.findAll();
    }
}
