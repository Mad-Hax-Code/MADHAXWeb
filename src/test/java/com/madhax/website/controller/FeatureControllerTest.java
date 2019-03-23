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
        mockMvc.perform(get("/project/feature/new/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(controller.FEATURE_FORM_URL))
                .andExpect(model().attributeExists("project"));

        verify(projectService, times(1)).getById(anyLong());
    }

    @Test
    public void saveNewFeature() throws Exception {

        when(projectService.getById(anyLong())).thenReturn(project);

        mockMvc.perform(post("/project/feature/new/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(projectService, times(1)).getById(anyLong());
        verify(projectService, times(1)).save(any());
    }

    @Test
    public void editFeature() throws Exception {
        when(featureService.getById(anyLong())).thenReturn(feature);
        mockMvc.perform(get("/project/feature/edit/1/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(controller.FEATURE_FORM_URL))
                .andExpect(model().attributeExists("feature"));
        verify(featureService, times(1)).getById(anyLong());
    }

    @Test
    public void saveEditedFeature() throws Exception {

        when(featureService.save(any())).thenReturn(feature);

        mockMvc.perform(post("/project/feature/edit/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(featureService, times(1)).save(any());
    }

    @Test
    public void confirmDeleteById() throws Exception {

        when(featureService.getById(anyLong())).thenReturn(feature);

        mockMvc.perform(get("/project/feature/delete/1/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(controller.CONFIRM_DELETE_URL))
                .andExpect(model().attributeExists("feature"));
        verify(featureService, times(1)).getById(anyLong());
    }

    @Test
    public void handleDeleteFeatureById() throws Exception {

        mockMvc.perform(
                post("/project/feature/delete/1")
                    .param("featureId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(featureService, times(1)).deleteById(anyLong());
    }
}