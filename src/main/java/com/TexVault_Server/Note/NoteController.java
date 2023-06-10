package com.TexVault_Server.Note;

import com.TexVault_Server.Notebook.NoteBook;
import com.TexVault_Server.Notebook.NoteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}