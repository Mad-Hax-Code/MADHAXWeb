package com.madhax.website.controller;

import com.madhax.website.domain.Project;
import com.madhax.website.service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftwareControllerTest {

    @Mock
    ProjectService projectService;

    @InjectMocks
    SoftwareController controller;

    MockMvc mockMvc;

    Project project;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        project = new Project();
        project.setId(1L);
        project.setName("My Project");
        project.setDescription("Project description.");
    }

    @Test
    public void listProjectsIT() throws Exception {

        Set<Project> projects = new HashSet<>();
        projects.add(project);

        when(projectService.getAll()).thenReturn(projects);

        mockMvc.perform(get("/software"))
                .andExpect(status().isOk())
                .andExpect(view().name("software/softwareList"))
                .andExpect(model().attributeExists("projects"));

        verify(projectService, times(1)).getAll();
    }

    @Test
    public void showProjectIT() throws Exception {

        when(projectService.getById(anyLong())).thenReturn(project);

        mockMvc.perform(get("/software/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("software/project"))
                .andExpect(model().attributeExists("project"));
    }

    @Test
    public void newProjectIT() throws Exception{

        mockMvc.perform(get("/software/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("software/newProject"))
                .andExpect(model().attributeExists("project"));
    }

    @Test
    public void editProjectIT() throws Exception {

        when(projectService.getById(anyLong())).thenReturn(project);

        mockMvc.perform(get("/software/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("software/editProject"))
                .andExpect(model().attributeExists("project"));
    }

    @Test
    public void saveProjectIT() throws Exception {
        // todo add view().name() expectation
        mockMvc.perform(post("/software/save"))
                .andExpect(status().is3xxRedirection());
        verify(projectService, times(1)).save(any());
    }

    @Test
    public void confirmDeleteIT() throws Exception {

        when(projectService.getById(anyLong())).thenReturn(project);

        mockMvc.perform(get("/software/delete/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("software/confirmDelete"))
                .andExpect(model().attributeExists("project"));
    }

    @Test
    public void deleteProjectIT() throws Exception {
        // todo add view().name() expectation
        mockMvc.perform(post("/software/delete"))
                .andExpect(status().is3xxRedirection());
        verify(projectService, times(1)).delete(any());
    }
}