package org.oraclenextone.model;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private FileWriter arquivo;

    public FileManager(String filename) {
        try {
            this.arquivo = new FileWriter(filename, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLine(String mensagem) {
        String line = String.format("%s", mensagem);

        try {
            this.arquivo.append(line);
            this.arquivo.flush();
        } catch (IOException e) {
            System.out.println("Houve um erro ao gravar os dados no arquivo.");
        }
    }

    public void closeFile(){
        try {
            this.arquivo.close();
        } catch (IOException e) {
            System.out.println("Houve um erro ao gravar os dados no arquivo.");
        }
    }
}
