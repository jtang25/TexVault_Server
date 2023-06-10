package com.TexVault_Server.Notebook;

import com.TexVault_Server.Note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteBookRepository extends JpaRepository<NoteBook, Long> {

    @Query("SELECT n FROM NoteBook n WHERE n.nb_id = ?1")
    Optional<NoteBook> findNoteBookByNb_id(String nb_id);

    @Query("SELECT n FROM NoteBook n WHERE n.nb_name = ?1")
    Optional<NoteBook> findNoteBookByName(String nb_name);
}
