package org.oraclenextone.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private final FileWriter arquivo;
    private final File arquivoFisico;

    public FileManager(String filename) {
        try {
            this.arquivo = new FileWriter(filename, true);
            this.arquivoFisico = new File(filename);
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

    public void outputFile(){
        try {
            Scanner leitor = new Scanner(arquivoFisico);
            while (leitor.hasNextLine()){
                System.out.println(leitor.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Não há nenhum histórico gerado");
        }


    }
}
