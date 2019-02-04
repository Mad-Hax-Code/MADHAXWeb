package com.madhax.website.repository;

import com.madhax.website.domain.Feature;
import org.springframework.data.repository.CrudRepository;

public interface FeatureRepository extends CrudRepository<Feature, Long> {
}
