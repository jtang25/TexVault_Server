package com.TexVault_Server.Notebook;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        return noteBookRepository.findNoteBookByNb_id(Long.parseLong(nb_id));
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

    @Transactional
    public void updateNoteBook(Long nb_id, String nb_name, String nb_desc){
        NoteBook nb = noteBookRepository.findById(nb_id)
                .orElseThrow(() -> new IllegalStateException(
                        "notebook with id "+nb_id+" does not exist"
                ));
        if(nb_name != null && nb_name.length()>0 && !Objects.equals(nb.getNb_name(), nb_name)){
            Optional<NoteBook> nbOptional = noteBookRepository.findNoteBookByName(nb_name);
            if(nbOptional.isPresent()){
                throw new IllegalStateException("notebook with name "+nb_name+" already exists");
            }
            nb.setNb_name(nb_name);
        }
        if(nb_desc != null && nb_desc.length()>0 && !Objects.equals(nb.getNb_description(), nb_desc)){
            nb.setNb_description(nb_desc);
        }
    }
}
