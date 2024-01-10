package com.TexVault_Server.Notebook;

import com.TexVault_Server.Note.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.time.Duration;
import java.time.Instant;

import java.io.*;
import java.nio.Buffer;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/notebook")
public class NoteBookController {
    private final NoteBookService noteBookService;

    @Autowired
    NoteBookController(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    @GetMapping("/get")
    public List<NoteBook> getNoteBooks() {
        return noteBookService.getNoteBooks();
    }

    @GetMapping("/getPdf")
    public ResponseEntity<InputStreamResource> generatePDF(@RequestParam("text") String text) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("/TexVault_Server/LaTex.tex"));
            bw.write(text);
            bw.close();
            ProcessBuilder pb = new ProcessBuilder("pdflatex", "-synctex=1","-interaction=batchmode", "LaTeX.tex");
            pb.directory(new File("/TexVault_Server"));
            pb.redirectErrorStream(true);

            Process p = pb.start();
            // Read the process output (optional)
            InputStream inputStream = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            Instant start = Instant.now();
            while ((line = reader.readLine()) != null) {
            }
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
            // Wait for the process to finish
            int exitCode = p.waitFor();

            if (exitCode == 0) {
                // Successful generation of PDF
                File pdfFile = new File("/TexVault_Server/LaTeX.pdf");
                InputStreamResource resource = new InputStreamResource(pdfFile.toURI().toURL().openStream());

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=LaTeX.pdf");

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } else {
                // Failed to generate PDF
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/addNew")
    public ResponseEntity<String> createNoteBook(@RequestParam("nb_name") String nb_name) {
        try {
            noteBookService.addNewNoteBook(new NoteBook(nb_name));
            return ResponseEntity.ok("Notebook created successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public void deleteNoteBook(@RequestParam("nb_id") String nb_id) {
        Long NoteBook_id = Long.parseLong(nb_id);
        noteBookService.deleteNoteBook(NoteBook_id);
    }

    @PutMapping("/updateTitle")
    public void updateNoteBookTitle(@RequestParam("nb_id") String nb_id,
            @RequestParam(name = "nb_name", required = false) String nb_name,
            @RequestParam(name = "nb_desc", required = false) String nb_desc) {
        noteBookService.updateNoteBook(Long.parseLong(nb_id), nb_name, nb_desc);
    }
}
