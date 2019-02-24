package com.madhax.website.controller;

import com.madhax.website.domain.IssueType;
import com.madhax.website.domain.Note;
import com.madhax.website.domain.Project;
import com.madhax.website.service.NoteService;
import com.madhax.website.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/{projectId}/note")
public class NoteController {

    private final Logger log = LoggerFactory.getLogger(NoteController.class);

    protected final String CONFIRM_DELETE_URL = "project/note/confirmDelete";
    protected final String NOTE_FORM_URL = "project/note/noteForm";

    private final ProjectService projectService;
    private final NoteService noteService;

    public NoteController(ProjectService projectService, NoteService noteService) {
        this.projectService = projectService;
        this.noteService = noteService;
    }

    @GetMapping("/new")
    public String newIssue(@PathVariable Long projectId, Model model){
        model.addAttribute("note", new Note());
        model.addAttribute("projectId", projectId);
        return NOTE_FORM_URL;
    }

    @PostMapping("/new")
    public String saveNewNote(@PathVariable Long projectId, @ModelAttribute Note note) {
        Project project = projectService.getById((projectId));
        project.addNote(note);
        projectService.save(project);
        return "redirect:/project/" + project.getId();
    }

    @GetMapping("/{noteId}/edit")
    public String editNote(@PathVariable Long noteId, Model model) {
        model.addAttribute("note", noteService.getById(noteId));
        return NOTE_FORM_URL;
    }

    @PostMapping("/edit")
    public String savedEditedNote(@ModelAttribute Note note, @PathVariable Long projectId) {
        log.debug("Updating note ID: {} for project: {}", note.getId(), projectId);
        Note savedNote = noteService.save(note);
        return "redirect:/project/" + projectId;
    }

    @GetMapping("/{noteId}/delete")
    public String confirmDeleteById(@PathVariable Long noteId, Model model) {
        log.debug("Delete confirmation for note ID: {}", noteId);
        model.addAttribute("note", noteService.getById(noteId));
        return CONFIRM_DELETE_URL;
    }

    @PostMapping("/delete")
    public String handleDeleteNoteById(@PathVariable Long projectId, @RequestParam Long noteId) {
        noteService.deleteById(noteId);
        return "redirect:/project/" + projectId;
    }


}
