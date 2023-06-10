package com.TexVault_Server.Notebook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NoteBookConfig {

    @Bean
    CommandLineRunner commandLineRunnerNoteBook(NoteBookRepository repository){
        return args -> {
            NoteBook math135 = new NoteBook(
                "math135"
            );
            NoteBook cs135 = new NoteBook (
                "cs135"
            );

            repository.saveAll(List.of(math135,cs135));
        };
    }
}
