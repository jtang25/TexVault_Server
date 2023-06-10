package com.TexVault_Server.Note;

import com.TexVault_Server.Notebook.NoteBook;
import com.TexVault_Server.Notebook.NoteBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotes(Long nb_id){
        return noteRepository.findNotesByNb_id(nb_id);
    }
}
