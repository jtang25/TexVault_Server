package com.TexVault_Server.Notebook;

import com.TexVault_Server.Note.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
