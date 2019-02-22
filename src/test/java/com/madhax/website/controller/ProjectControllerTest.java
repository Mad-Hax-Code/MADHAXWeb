package com.madhax.website.controller;

import com.madhax.website.domain.Project;
import com.madhax.website.exceptions.NotFoundException;
import com.madhax.website.service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class ProjectControllerTest {

    private final String BAD_REQUEST_URL = "400error";
    private final String NOT_FOUND_URL = "404error";

    @Mock
    ProjectService projectService;

    @InjectMocks
    ProjectController controller;

    MockMvc mockMvc;

    Project project;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
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

        mockMvc.perform(get("/project"))
                .andExpect(status().isOk())
                .andExpect(view().name(controller.PROJECT_LIST_URL))
                .andExpect(model().attributeExists("projects"));

        verify(projectService, times(1)).getAll();
    }

    @Test
    public void showProjectIT() throws Exception {

        when(projectService.getById(anyLong())).thenReturn(project);

        mockMvc.perform(get("/project/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(controller.VIEW_PROJECT_URL))
                .andExpect(model().attributeExists("project"));
    }

    @Test
    public void showProjectNotFoundExceptionIT() throws Exception {

        when(projectService.getById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/project/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name(NOT_FOUND_URL));
    }

    @Test
    public void showProjectNumberFormatExceptionIT() throws Exception {

        mockMvc.perform(get("/project/asdf"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name(BAD_REQUEST_URL));

    }

    @Test
    public void newProjectIT() throws Exception{

        mockMvc.perform(get("/project/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(controller.PROJECT_FORM_URL))
                .andExpect(model().attributeExists("project"));
    }

    @Test
    public void projectFormSubmissionIT() throws Exception {

        when(projectService.save(any())).thenReturn(project);

        mockMvc.perform(
                post("/project/save")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("name", "My Project")
                    .param("description", "Project description."))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(projectService, times(1)).save(any());
    }

    @Test
    public void projectFormValidationFailIT() throws Exception {
        Project project = new Project();
        project.setId(2L);

        when(projectService.save(any())).thenReturn(project);

        mockMvc.perform(
                post("/project/save")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("id", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("project"))
                .andExpect(view().name(controller.PROJECT_FORM_URL));
    }

    @Test
    public void editProjectIT() throws Exception {

        when(projectService.getById(anyLong())).thenReturn(project);

        mockMvc.perform(get("/project/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(controller.PROJECT_FORM_URL))
                .andExpect(model().attributeExists("project"));
    }

    @Test
    public void confirmDeleteIT() throws Exception {

        when(projectService.getById(anyLong())).thenReturn(project);

        mockMvc.perform(get("/project/1/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name(controller.CONFIRM_DELETE_URL))
                .andExpect(model().attributeExists("project"));
    }

    @Test
    public void deleteProjectIT() throws Exception {
        mockMvc.perform(
                post("/project/delete")
                        .param("projectId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project"));
        verify(projectService, times(1)).deleteById(anyLong());
    }
}