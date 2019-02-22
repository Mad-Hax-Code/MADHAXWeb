package com.madhax.website.service;

import com.madhax.website.domain.Project;
import com.madhax.website.exceptions.NotFoundException;
import com.madhax.website.repository.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectService projectService;

    Project project;

    @Before
    public void setUp() {
        this.project = new Project();
        this.project.setId(1L);
        this.project.setName("My Project");
        this.project.setDescription("My project description.");
    }

    @Test
    public void getAllTest() {
        // given
        Set<Project> projects = new HashSet<>();
        Project project = new Project();
        projects.add(project);
        // when
        when(projectRepository.findAll()).thenReturn(projects);
        Set<Project> returnedProjects = projectService.getAll();
        // then
        assertEquals(1, returnedProjects.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {

        Optional<Project> optionalProject = Optional.of(project);

        when(projectRepository.findById(anyLong())).thenReturn(optionalProject);
        Project returnedProject = projectService.getById(1L);

        assertNotNull(returnedProject);
        verify(projectRepository, times(1)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void getByIdNotFoundTest() throws Exception {

        Optional<Project> optionalProject = Optional.empty();
        when(projectRepository.findById(anyLong())).thenReturn(optionalProject);

        Project returnedProject = projectService.getById(1L);
    }

    @Test
    public void saveTest() {

        when(projectRepository.save(any())).thenReturn(project);
        Project savedProject = projectService.save(project);

        assertNotNull(savedProject);
        assertEquals("My Project", savedProject.getName());
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    public void deleteTest() {
        Project project = new Project();
        projectService.delete(project);
        verify(projectRepository, times(1)).delete(any());
    }

    @Test
    public void deleteByIdTest() {
        Long idToDelete = 2L;
        projectService.deleteById(idToDelete);
        verify(projectRepository, times(1)).deleteById(anyLong());
    }
}