package com.madhax.website.service;

import com.madhax.website.domain.DevNote;
import com.madhax.website.repository.DevNoteRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DevNoteService {

    private final DevNoteRepository devNoteRepository;

    public DevNoteService(DevNoteRepository devNoteRepository) {
        this.devNoteRepository = devNoteRepository;
    }

    public DevNote getById(Long id) {
        return devNoteRepository.findById(id).orElse(null);
    }

    public Set<DevNote> getAll() {
        Set<DevNote> devNotes = new HashSet<>();
        devNoteRepository.findAll().forEach(devNotes::add);
        return devNotes;
    }

    public DevNote save(DevNote devNote) {
        return devNoteRepository.save(devNote);
    }

    public void delete(DevNote devNote) {
        devNoteRepository.delete(devNote);
    }

    public void deleteById(Long id) {
        devNoteRepository.deleteById(id);
    }
}
