package com.tinosnegocios.financas.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilesStorage {
    private final String path;
    private final String extension;
    private String content;

    public FilesStorage(String path, String extension) {
        this.extension = extension;
        this.path = path + "." + this.extension;
    }

    public void write(String content) {
        BufferedWriter writer = null;
        try {
            File file = new File(this.path);
            if(! file.exists()) {
                file.createNewFile();
            }

            writer = new BufferedWriter(new FileWriter(this.path));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
