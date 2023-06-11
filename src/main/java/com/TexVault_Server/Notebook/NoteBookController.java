package com.TexVault_Server.Notebook;

import com.TexVault_Server.Note.Note;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createNoteBook(@RequestParam("nb_name") String nb_name){
        noteBookService.addNewNoteBook(new NoteBook(nb_name));
    }

    @DeleteMapping("/delete")
    public void deleteNoteBook(@RequestParam("nb_id") String nb_id){
        Long NoteBook_id = Long.parseLong(nb_id);
        noteBookService.deleteNoteBook(NoteBook_id);
    }
}
