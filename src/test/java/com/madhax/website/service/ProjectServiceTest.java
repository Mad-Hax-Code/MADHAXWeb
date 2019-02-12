package com.madhax.website.service;

import com.madhax.website.domain.Project;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    ProjectService projectService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest() {
        // given
        Set<Project> projects = new HashSet<>();
        Project project = new Project();
        projects.add(project);
        // when
        when(projectService.getAll()).thenReturn(projects);
        Set<Project> returnedProjects = projectService.getAll();
        // then
        assertEquals(1, returnedProjects.size());
        verify(projectService, times(1)).getAll();
    }

    @Test
    public void getByIdTest() {
        // given
        Project project = new Project();
        project.setId(1L);

        // when
        when(projectService.getById(anyLong())).thenReturn(project);
        Project returnedProject = projectService.getById(1L);

        // then
        assertNotNull(returnedProject);
        verify(projectService, times(1)).getById(anyLong());
    }

    @Test
    public void saveTest() {
        Project project = new Project();
        project.setName("My Project");

        when(projectService.save(any())).thenReturn(project);
        Project savedProject = projectService.save(project);

        assertNotNull(savedProject);
        assertEquals("My Project", savedProject.getName());
        verify(projectService, times(1)).save(any());
    }

    @Test
    public void deleteTest() {
        Project project = new Project();
        projectService.delete(project);
        verify(projectService, times(1)).delete(any());
    }

    @Test
    public void deleteByIdTest() {
        Long idTodelete = 2L;
        projectService.deleteById(idTodelete);
        verify(projectService, times(1)).deleteById(anyLong());
    }
}