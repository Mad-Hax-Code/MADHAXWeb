package com.madhax.website.service;

import com.madhax.website.domain.DevNote;
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
public class DevNoteServiceTest {

    @Mock
    DevNoteService devNoteService;

    @Test
    public void getByIdTest() {
        DevNote devNote = new DevNote();
        devNote.setId(1L);
        devNote.setTitle("DevNote Title");
        devNote.setMessage("DevNote message body.");

        when(devNoteService.getById(anyLong())).thenReturn(devNote);
        DevNote returnedDevNote = devNoteService.getById(1L);

        assertEquals("DevNote Title", returnedDevNote.getTitle());
        verify(devNoteService, times(1)).getById(anyLong());
    }

    @Test
    public void getAllTest() {
        Set<DevNote> devNotes = new HashSet<>();

        DevNote devNote1 = new DevNote();
        devNote1.setId(1L);
        devNote1.setTitle("DevNote 1, Title");
        devNote1.setMessage("DevNote 1 message body.");

        DevNote devNote2 = new DevNote();
        devNote2.setId(2L);
        devNote2.setTitle("DevNote 2, Title");
        devNote2.setMessage("DevNote 2 message body.");

        devNotes.add(devNote1);
        devNotes.add(devNote2);

        when(devNoteService.getAll()).thenReturn(devNotes);
        Set<DevNote> returnedNotes = devNoteService.getAll();

        assertEquals(2, returnedNotes.size());
        verify(devNoteService, times(1)).getAll();
    }

    @Test
    public void saveTest() {
        DevNote devNote = new DevNote();
        devNote.setId(1L);
        devNote.setTitle("DevNote Title");
        devNote.setMessage("DevNote message body.");

        when(devNoteService.save(any())).thenReturn(devNote);

        DevNote savedNote = devNoteService.save(devNote);

        assertEquals("DevNote Title", savedNote.getTitle());
        verify(devNoteService, times(1)).save(any());
    }

    @Test
    public void deleteTest() {
        DevNote devNote = new DevNote();
        devNoteService.delete(devNote);
        verify(devNoteService, times(1)).delete(any());
    }

    @Test
    public void deleteByIdTest() {
        DevNote devNote = new DevNote();
        devNote.setId(1L);
        devNoteService.deleteById(1L);
        verify(devNoteService, times(1)).deleteById(anyLong());
    }
}