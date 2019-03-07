package com.madhax.website.controller;

import com.madhax.website.domain.Note;
import com.madhax.website.domain.Project;
import com.madhax.website.service.NoteService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteControllerTest {

    final String CONFIRM_DELETE_URL = "project/note/confirmDelete";
    final String NOTE_FORM_URL = "project/note/noteForm";

    @Mock
    ProjectService projectService;

    @Mock
    NoteService noteService;

    @InjectMocks
    NoteController noteController;

    MockMvc mockMvc;

    Project project;
    Note note1;
    Note note2;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();

        project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setDescription("Test project description");

        note1 = new Note();
        note1.setId(1L);
        note1.setTitle("Note 1 Title");
        note1.setMessage("Note 1 message.");
        note1.setProject(project);

        note2 = new Note();
        note2.setId(2L);
        note2.setTitle("Note 2 Title");
        note2.setMessage("Note 2 message.");
        note2.setProject(project);
    }

    @Test
    public void newIssue() throws Exception {

        mockMvc.perform(get("/project/1/note/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(NOTE_FORM_URL))
                .andExpect(model().attributeExists("projectId"))
                .andExpect(model().attributeExists("note"));

    }

    @Test
    public void saveNewNote() throws Exception {
        when(projectService.getById(anyLong())).thenReturn(project);
        when(projectService.save(any())).thenReturn(project);

        mockMvc.perform(post("/project/1/note/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(projectService, times(1)).getById(anyLong());
    }

    @Test
    public void editNote() throws Exception {

        when(noteService.getById(anyLong())).thenReturn(note1);

        mockMvc.perform(get("/project/1/note/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(NOTE_FORM_URL))
                .andExpect(model().attributeExists("note"));

    }

    @Test
    public void savedEditedNote() throws Exception {
        when(noteService.save(any())).thenReturn(note1);

        mockMvc.perform(post("/project/1/note/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(noteService, times(1)).save(any());
    }

    @Test
    public void confirmDeleteById() throws Exception {
        when(noteService.getById(anyLong())).thenReturn(note1);

        mockMvc.perform(get("/project/1/note/1/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name(CONFIRM_DELETE_URL))
                .andExpect(model().attributeExists("note"));

        verify(noteService, times(1)).getById(anyLong());
    }

    @Test
    public void handleDeleteNoteById() throws Exception {

        mockMvc.perform(
                post("/project/1/note/delete")
                    .param("issueId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));
        
        verify(noteService, times(1)).deleteById(anyLong());
    }
}