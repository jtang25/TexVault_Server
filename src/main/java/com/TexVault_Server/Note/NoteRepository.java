package com.TexVault_Server.Note;

import com.TexVault_Server.Notebook.NoteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.nb_id = ?1")
    List<Note> findNotesByNb_id(Long nb_id);

    @Query("SELECT n FROM Note n WHERE n.note_name = ?1")
    Optional<Note> findNoteByName(String note_name);
}
