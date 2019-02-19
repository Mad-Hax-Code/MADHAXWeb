package com.madhax.website.controller;

import com.madhax.website.domain.Feature;
import com.madhax.website.domain.Project;
import com.madhax.website.service.FeatureService;
import com.madhax.website.service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeatureControllerTest {

    @Mock
    ProjectService projectService;
    @Mock
    FeatureService featureService;
    @InjectMocks
    FeatureController controller;

    MockMvc mockMvc;

    Project project;

    Feature feature;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        project = new Project();
        project.setId(1L);
        project.setName("My Project");
        project.setDescription("Project description.");

        feature = new Feature();
        feature.setId(1L);
        feature.setTitle("Feature Title");
        feature.setBody("Feature body.");
        feature.setProject(project);

        project.getFeatures().add(feature);
    }

    @Test
    public void newFeature() throws Exception {
        when(projectService.getById(anyLong())).thenReturn(project);
        mockMvc.perform(get("/software/features/new/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/software/feature/addFeature"))
                .andExpect(model().attributeExists("project"));

        verify(projectService, times(1)).getById(anyLong());
    }

    @Test
    public void saveFeature() throws Exception {
        // todo add view().name() expectation
        when(projectService.getById(anyLong())).thenReturn(project);
        mockMvc.perform(post("/software/features/save/1"))
                .andExpect(status().is3xxRedirection());
        verify(projectService, times(1)).getById(anyLong());
        verify(projectService, times(1)).save(any());
    }

    @Test
    public void editFeature() throws Exception {
        when(featureService.getById(anyLong())).thenReturn(feature);
        mockMvc.perform(get("/software/features/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/software/feature/editFeature"))
                .andExpect(model().attributeExists("feature"));
        verify(featureService, times(1)).getById(anyLong());
    }
}