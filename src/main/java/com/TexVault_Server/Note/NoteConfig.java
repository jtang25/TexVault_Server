package com.TexVault_Server.Note;

import com.TexVault_Server.Notebook.NoteBook;
import com.TexVault_Server.Notebook.NoteBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class NoteConfig {

    @Autowired
    private NoteBookRepository noteBookRepository;

    @Bean
    CommandLineRunner commandLineRunnerNote(NoteRepository repository){
        return args -> {

            Optional<NoteBook> math135 = noteBookRepository.findNoteBookByName("math135");
            Optional<NoteBook> cs135 = noteBookRepository.findNoteBookByName("cs135");
            Note note1 = null;
            Note note2 = null;
            if (math135.isPresent()) {
                NoteBook nb1 = math135.get();
                note1 = new Note(
                        "Intro to Linear Algebra",
                        nb1.getNb_id(),
                        "The line is algebraic."
                );
            }
            if (cs135.isPresent()) {
                NoteBook nb2 = cs135.get();
                note2 = new Note(
                        "Intro to Computer Science",
                        nb2.getNb_id(),
                        "The computer is sciencey."
                );
            }

            repository.saveAll(List.of(note1, note2));
        };
    }
}