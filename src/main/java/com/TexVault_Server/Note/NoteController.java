package com.TexVault_Server.Note;

import com.TexVault_Server.Notebook.NoteBook;
import com.TexVault_Server.Notebook.NoteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createNote(@RequestParam("note_name") String note_name, @RequestParam("nb_id") String nbId){
        try {
            Note n = new Note(note_name, Long.parseLong(nbId));
            noteService.addNewNote(n);
            return ResponseEntity.ok(Long.toString(n.getNote_id()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public void deleteNote(@RequestParam("note_id") String note_id){
        Long Note_id = Long.parseLong(note_id);
        noteService.deleteNote(Note_id);
    }

    @PutMapping("/updateName")
    public void updateNoteBookTitle(@RequestParam("note_id") String note_id, @RequestParam(name="note_name", required = false) String note_name,
                                    @RequestParam(name="nb_id", required = false) String nb_id, @RequestParam(name="note_text", required = false) String note_text){
        if(nb_id==null){
            noteService.updateNote(Long.parseLong(note_id), note_name, nb_id, note_text);
        }
        else{
            noteService.updateNote(Long.parseLong(note_id), note_name, Long.parseLong(nb_id), note_text);
        }
    }
}