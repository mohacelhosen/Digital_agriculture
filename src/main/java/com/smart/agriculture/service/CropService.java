package com.smart.agriculture.service;

import com.smart.agriculture.entity.CropEntity;
import com.smart.agriculture.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    private final CropRepository cropRepository;

    @Autowired
    public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public List<CropEntity> getAllCrops() {
        return cropRepository.findAll();
    }

    public Optional<CropEntity> getCropById(Integer id) {
        return cropRepository.findById(id);
    }

    public CropEntity createCrop(CropEntity crop) {
        return cropRepository.save(crop);
    }

    public CropEntity updateCrop(Integer id, CropEntity updatedCrop) {
        if (cropRepository.existsById(id)) {
            updatedCrop.setId(id);
            return cropRepository.save(updatedCrop);
        } else {
            throw new RuntimeException("Crop not found with id: " + id);
        }
    }

    public void deleteCrop(Integer id) {
        cropRepository.deleteById(id);
    }
}
