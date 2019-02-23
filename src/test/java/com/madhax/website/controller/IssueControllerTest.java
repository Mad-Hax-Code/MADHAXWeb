package com.madhax.website.controller;

import com.madhax.website.domain.Issue;
import com.madhax.website.domain.IssueType;
import com.madhax.website.domain.Project;
import com.madhax.website.service.IssueService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by James Cathcart on 2/22/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueControllerTest {

    protected final String CONFIRM_DELETE_URL = "project/issue/confirmDelete";
    protected final String ISSUE_FORM_URL = "project/issue/issueForm";

    @Mock
    ProjectService projectService;

    @Mock
    IssueService issueService;

    @InjectMocks
    IssueController controller;

    MockMvc mockMvc;

    Issue issue;
    Project project;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();

        project = new Project();
        project.setId(1L);
        project.setName("Project Title");
        project.setDescription("Project description.");

        issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Issue Title");
        issue.setMessage("Issue body here.");
        issue.setIssueType(IssueType.GENERAL);
        issue.setProject(project);

        project.addIssue(issue);

    }

    @Test
    public void newIssue() throws Exception {

        mockMvc.perform(get("/project/1/issue/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(ISSUE_FORM_URL))
                .andExpect(model().attributeExists("issue"))
                .andExpect(model().attributeExists("projectId"));
    }

    @Test
    public void saveNewIssue() throws Exception {

        when(projectService.getById(anyLong())).thenReturn(project);

        mockMvc.perform(post("/project/1/issue/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(projectService, times(1)).getById(anyLong());
        verify(projectService, times(1)).save(any());
    }

    @Test
    public void editIssue() throws Exception {

        when(issueService.getById(anyLong())).thenReturn(issue);

        mockMvc.perform(get("/project/1/issue/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(ISSUE_FORM_URL))
                .andExpect(model().attributeExists("issue"));

        verify(issueService, times(1)).getById(anyLong());
    }

    @Test
    public void saveEditedIssue() throws Exception {

        when(issueService.save(any())).thenReturn(issue);

        mockMvc.perform(post("/project/1/issue/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(issueService, times(1)).save(any());
    }

    @Test
    public void confirmDeleteById() throws Exception {

        when(issueService.getById(anyLong())).thenReturn(issue);

        mockMvc.perform(get("/project/1/issue/1/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name(CONFIRM_DELETE_URL))
                .andExpect(model().attributeExists("issue"));

        verify(issueService, times(1)).getById(anyLong());
    }

    @Test
    public void handleDeleteIssueById() throws Exception {

        mockMvc.perform(
                post("/project/1/issue/delete")
                        .param("issueId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(issueService, times(1)).deleteById(anyLong());
    }
}