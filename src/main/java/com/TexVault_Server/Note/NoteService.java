package com.TexVault_Server.Note;

import com.TexVault_Server.Notebook.NoteBook;
import com.TexVault_Server.Notebook.NoteBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void addNewNote(Note n){
        Optional<Note> noteOptional = noteRepository.findNoteByName(n.getNote_name());
        if (noteOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }
        noteRepository.save(n);
    }
}
