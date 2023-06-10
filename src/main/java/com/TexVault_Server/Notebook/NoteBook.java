package com.TexVault_Server.Notebook;

import com.TexVault_Server.Note.Note;
import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "notebooks")
public class NoteBook {

    @Id
    @SequenceGenerator(
            name="notebook_sequence",
            sequenceName="notebook_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notebook_sequence"
    )
    private Long nb_id;
    private String nb_name;

    public NoteBook() {
    }

    public NoteBook(String nb_name) {
        this.nb_name = nb_name;
    }

    public Long getNb_id() {
        return nb_id;
    }

    public String getNb_name() {
        return nb_name;
    }

    public void setNb_id(Long nb_id) {
        this.nb_id = nb_id;
    }

    public void setNb_name(String nb_name) {
        this.nb_name = nb_name;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "nb_id=" + nb_id +
                ", nb_name='" + nb_name + '\'' +
                '}';
    }
}
