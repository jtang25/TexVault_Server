package com.TexVault_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class TexVaultServerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TexVaultServerApplication.class, args);
    }

}
