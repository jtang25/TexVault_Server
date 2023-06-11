package com.TexVault_Server.Note;

import com.TexVault_Server.Notebook.NoteBook;
import com.TexVault_Server.Notebook.NoteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/note")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("/get")
    public List<Note> getNotes(@RequestParam("nb_id") String nbId){
        Long nb_id = Long.parseLong(nbId);
        return noteService.getNotes(nb_id);
    }

    @PutMapping("/addNew")
    public void createNoteBook(@RequestParam("note_name") String note_name, @RequestParam("nb_id") String nbId) {
        Long nb_id = Long.parseLong(nbId);
        noteService.addNewNote(new Note(note_name, nb_id));
    }

    @DeleteMapping("/delete")
    public void deleteNote(@RequestParam("note_id") String note_id){
        Long Note_id = Long.parseLong(note_id);
        noteService.deleteNote(Note_id);
    }
}