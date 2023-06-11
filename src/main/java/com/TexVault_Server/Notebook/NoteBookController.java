package com.TexVault_Server.Notebook;

import com.TexVault_Server.Note.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/notebook")
public class NoteBookController {
    private final NoteBookService noteBookService;

    @Autowired
    NoteBookController(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    @GetMapping("/get")
    public List<NoteBook> getNoteBooks(){
        return noteBookService.getNoteBooks();
    }

    @PutMapping("/addNew")
    public ResponseEntity<String> createNoteBook(@RequestParam("nb_name") String nb_name){
        try {
            noteBookService.addNewNoteBook(new NoteBook(nb_name));
            return ResponseEntity.ok("Notebook created successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public void deleteNoteBook(@RequestParam("nb_id") String nb_id){
        Long NoteBook_id = Long.parseLong(nb_id);
        noteBookService.deleteNoteBook(NoteBook_id);
    }

    @PutMapping("/updateTitle")
    public void updateNoteBookTitle(@RequestParam("nb_id") String nb_id, @RequestParam(name="nb_name", required = false)
            String nb_name, @RequestParam(name="nb_desc", required = false) String nb_desc){
        noteBookService.updateNoteBook(Long.parseLong(nb_id), nb_name, nb_desc);
    }
}
