package com.tinosnegocios.financas.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FilesStorage {
    private final String path;
    private final String extension;
    private String content;

    public FilesStorage(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    public void write(String content){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.path + this.extension));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
