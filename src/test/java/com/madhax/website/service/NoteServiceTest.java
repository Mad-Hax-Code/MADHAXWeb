package com.madhax.website.service;

import com.madhax.website.domain.Note;
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
public class NoteServiceTest {

    @Mock
    NoteService noteService;

    @Test
    public void getByIdTest() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Note Title");
        note.setMessage("Note message body.");

        when(noteService.getById(anyLong())).thenReturn(note);
        Note returnedNote = noteService.getById(1L);

        assertEquals("Note Title", returnedNote.getTitle());
        verify(noteService, times(1)).getById(anyLong());
    }

    @Test
    public void getAllTest() {
        Set<Note> notes = new HashSet<>();

        Note note1 = new Note();
        note1.setId(1L);
        note1.setTitle("Note 1, Title");
        note1.setMessage("Note 1 message body.");

        Note note2 = new Note();
        note2.setId(2L);
        note2.setTitle("Note 2, Title");
        note2.setMessage("Note 2 message body.");

        notes.add(note1);
        notes.add(note2);

        when(noteService.getAll()).thenReturn(notes);
        Set<Note> returnedNotes = noteService.getAll();

        assertEquals(2, returnedNotes.size());
        verify(noteService, times(1)).getAll();
    }

    @Test
    public void saveTest() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Note Title");
        note.setMessage("Note message body.");

        when(noteService.save(any())).thenReturn(note);

        Note savedNote = noteService.save(note);

        assertEquals("Note Title", savedNote.getTitle());
        verify(noteService, times(1)).save(any());
    }

    @Test
    public void deleteTest() {
        Note note = new Note();
        noteService.delete(note);
        verify(noteService, times(1)).delete(any());
    }

    @Test
    public void deleteByIdTest() {
        Note note = new Note();
        note.setId(1L);
        noteService.deleteById(1L);
        verify(noteService, times(1)).deleteById(anyLong());
    }
}