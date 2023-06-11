package com.TexVault_Server.Note;

import com.TexVault_Server.Notebook.NoteBook;
import jakarta.persistence.*;

@Entity
@Table(name="notes")
public class Note {
    @Id
    @SequenceGenerator(
            name="note_sequence",
            sequenceName="note_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )
    private Long note_id;
    private String note_name;
    private Long nb_id;
    @Column(columnDefinition = "LONGTEXT")
    private String note_text;

    public Note() {
    }

    public Note(String note_name, Long nb_id) {
        this.note_name = note_name;
        this.nb_id = nb_id;
    }

    public Note(String note_name, Long nb_id, String note_text) {
        this.note_name = note_name;
        this.nb_id = nb_id;
        this.note_text = note_text;
    }

    public Long getNote_id() {
        return note_id;
    }

    public void setNote_id(Long note_id) {
        this.note_id = note_id;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }

    public Long getNb_Id() {
        return this.nb_id;
    }

    public void setNb_Id(Long nb_id) {
        this.nb_id = nb_id;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }
}
