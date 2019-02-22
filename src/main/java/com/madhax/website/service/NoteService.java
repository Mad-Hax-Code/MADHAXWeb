package com.madhax.website.service;

import com.madhax.website.domain.Note;
import com.madhax.website.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NoteService {

    private final Logger log = LoggerFactory.getLogger(NoteService.class);
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note getById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public Set<Note> getAll() {
        Set<Note> notes = new HashSet<>();
        noteRepository.findAll().forEach(notes::add);
        return notes;
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}
