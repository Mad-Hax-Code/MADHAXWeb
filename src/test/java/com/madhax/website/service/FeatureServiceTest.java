package com.madhax.website.service;

import com.madhax.website.domain.Feature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by James Cathcart on 2/18/2019
 */
@RunWith(SpringRunner.class)
public class FeatureServiceTest {

    @Mock
    FeatureService featureService;

    @Test
    public void getByIdTest() {
        Feature feature = new Feature();
        feature.setId(1L);
        feature.setTitle("Feature Title");

        when(featureService.getById(anyLong())).thenReturn(feature);
        Feature returnedFeature = featureService.getById(1L);

        assertEquals("Feature Title", returnedFeature.getTitle());
        verify(featureService, times(1)).getById(anyLong());
    }

    @Test
    public void getAllTest() {
        Set<Feature> features = new HashSet<>();
        Feature feature1 = new Feature();
        feature1.setId(1L);
        Feature feature2 = new Feature();
        feature2.setId(2L);

        features.add(feature1);
        features.add(feature2);

        when(featureService.getAll()).thenReturn(features);
        Set<Feature> returnedFeatures = featureService.getAll();

        assertEquals(2, returnedFeatures.size());
        verify(featureService, times(1)).getAll();
    }

    @Test
    public void saveTest() {
        Feature feature = new Feature();
        feature.setId(1L);
        feature.setTitle("Feature Title");

        when(featureService.save(any())).thenReturn(feature);
        Feature savedFeature = featureService.save(feature);

        assertEquals("Feature Title", savedFeature.getTitle());
        verify(featureService, times(1)).save(any());
    }

    @Test
    public void deleteTest() {
        Feature feature = new Feature();
        feature.setId(1L);

        featureService.delete(feature);

        verify(featureService, times(1)).delete(any());
    }

    @Test
    public void deleteByIdTest() {
        Feature feature = new Feature();
        feature.setId(1L);

        featureService.deleteById(1L);

        verify(featureService, times(1)).deleteById(anyLong());
    }
}