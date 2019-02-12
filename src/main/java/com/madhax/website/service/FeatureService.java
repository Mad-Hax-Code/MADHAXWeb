package com.madhax.website.service;

import com.madhax.website.domain.Feature;
import com.madhax.website.repository.FeatureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public Feature getById(Long id) {
        return featureRepository.findById(id).orElse(null);
    }

    public Set<Feature> getAll() {
        Set<Feature> features = new HashSet<>();
        featureRepository.findAll().forEach(features::add);
        return features;
    }

    public Feature save(Feature feature) {
        return featureRepository.save(feature);
    }

    public void delete(Feature feature) {
        featureRepository.delete(feature);
    }

    public void deleteById(Long id) {
        featureRepository.deleteById(id);
    }
}
