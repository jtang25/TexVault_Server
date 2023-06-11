package com.TexVault_Server.Notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteBookService {
    private final NoteBookRepository noteBookRepository;

    @Autowired
    public NoteBookService(NoteBookRepository noteBookRepository){
        this.noteBookRepository = noteBookRepository;
    }

    public List<NoteBook> getNoteBooks(){
        return noteBookRepository.findAll();
    }

    public Optional<NoteBook> getNoteBookById(String nb_id){
        return noteBookRepository.findNoteBookByNb_id(nb_id);
    }

    public void addNewNoteBook(NoteBook nb){
        Optional<NoteBook> noteBookOptional = noteBookRepository.findNoteBookByName(nb.getNb_name());
        if (noteBookOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }
        noteBookRepository.save(nb);
    }

    public void deleteNoteBook(Long nb_id){
        boolean exists = noteBookRepository.existsById(nb_id);
        if (!exists) {
            throw new IllegalStateException("notebook with id "+nb_id+" does not exist");
        }
        else{
            noteBookRepository.deleteById(nb_id);
        }
    }
}
