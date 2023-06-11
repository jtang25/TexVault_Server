package com.TexVault_Server.Note;

import com.TexVault_Server.Notebook.NoteBook;
import com.TexVault_Server.Notebook.NoteBookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public void deleteNote(Long note_id){
        boolean exists = noteRepository.existsById(note_id);
        if (!exists) {
            throw new IllegalStateException("note with id "+note_id+" does not exist");
        }
        else{
            noteRepository.deleteById(note_id);
        }
    }

    @Transactional
    public void updateNote(Long note_id, String note_name, String nb_id, String note_text){
        Note n = noteRepository.findById(note_id)
                .orElseThrow(() -> new IllegalStateException(
                        "note with id "+note_id+" does not exist"
                ));
        if(note_name != null && note_name.length()>0 && !Objects.equals(n.getNote_name(), note_name)){
            Optional<Note> nOptional = noteRepository.findNoteByName(note_name);
            if(nOptional.isPresent()){
                throw new IllegalStateException("note with name "+note_name+" already exists");
            }
            n.setNote_name(note_name);
        }
        if(note_text != null && note_text.length()>0 && !Objects.equals(n.getNote_text(), note_text)){
            n.setNote_text(note_text);
        }
        if(nb_id != null && !nb_id.equals(n.getNb_Id())){
            Long nb_id_L = Long.parseLong(nb_id);
            n.setNb_Id(nb_id_L);
        }
    }

    @Transactional
    public void updateNote(Long note_id, String note_name, Long nb_id, String note_text){
        Note n = noteRepository.findById(note_id)
                .orElseThrow(() -> new IllegalStateException(
                        "note with id "+note_id+" does not exist"
                ));
        if(note_name != null && note_name.length()>0 && !Objects.equals(n.getNote_name(), note_name)){
            Optional<Note> nOptional = noteRepository.findNoteByName(note_name);
            if(nOptional.isPresent()){
                throw new IllegalStateException("note with name "+note_name+" already exists");
            }
            n.setNote_name(note_name);
        }
        if(note_text != null && note_text.length()>0 && !Objects.equals(n.getNote_text(), note_text)){
            n.setNote_text(note_text);
        }
        if(nb_id != null && !nb_id.equals(n.getNb_Id())){
            n.setNb_Id(nb_id);
        }
    }
}
