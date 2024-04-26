package org.oraclenextone.model;

import java.time.LocalDateTime;

public class Historico extends FileManager{
    public Historico(String filename) {
        super(filename);
    }

    public void addHistorico(LocalDateTime dataOcorrido, String moedaBase, String moedaConversao, double valorBase, double valorConversao) {
        String log = String.format("%s | Moeda base: %s | Valor base: %.2f | Moeda conversão: %s | Valor conversão: %.2f \n",
                dataOcorrido.toString(),
                moedaBase,
                valorBase,
                moedaConversao,
                valorConversao
        );

        this.addLine(log);
    }
}
