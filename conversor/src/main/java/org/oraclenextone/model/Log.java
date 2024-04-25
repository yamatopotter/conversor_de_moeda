package org.oraclenextone.model;
import java.time.LocalDateTime;

public class Log extends FileManager {
    public Log(String filename) {
        super(filename);
    }

    public void addLog(LocalDateTime dataOcorrido, String moedaBase, String moedaConversao, double valorBase, double valorConversao) {
        String log = String.format("%s | Moeda base: %s | Valor base: %.2f | Moeda conversão: %s | Valor conversão: %.2f \n",
                dataOcorrido.toString(),
                moedaBase,
                valorBase,
                moedaConversao,
                valorConversao
        );

        this.addLine(log);
    }

    public void addLog(LocalDateTime dataOcorrido, String error) {
        String log = String.format("%s | Erro %s \n",
                dataOcorrido.toString(),
                error
        );

        this.addLine(log);
    }
}
