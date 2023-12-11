package codigo;

import java.io.*;
import java.util.*;

public class MVan implements Manutencao {
    private static final double KM_MANUTENCAO_PERIODICA = 10000;
    private static final double KM_TROCA_PNEUS = 12000;
    private static final double VALOR_PERIODICA = 250.0;
    private static final double VALOR_TROCA_PNEU = 500.0;
    private double kmUltimaManutencaoPeriodica = 0;
    private double kmUltimaTrocaPneus = 0;

    @Override
    public boolean precisaManutencaoPeriodica(double kmAtual) {
        return kmAtual - kmUltimaManutencaoPeriodica >= KM_MANUTENCAO_PERIODICA;
    }

    @Override
    public boolean precisaTrocaPneus(double kmAtual) {
        return kmAtual - kmUltimaTrocaPneus >= KM_TROCA_PNEUS;
    }

    @Override
    public void registrarManutencaoPeriodica(double kmAtual) {
        this.kmUltimaManutencaoPeriodica = kmAtual;
    }

    @Override
    public void registrarTrocaPneus(double kmAtual) {
        this.kmUltimaTrocaPneus = kmAtual;
    }

    @Override
    public double calcularCusto(double kmAtual) {
        double custoManutencao = 0;

        if(kmAtual >= KM_MANUTENCAO_PERIODICA){
            custoManutencao += VALOR_PERIODICA;
        } else if(kmAtual >= KM_TROCA_PNEUS){
            custoManutencao += VALOR_TROCA_PNEU;
        }

        return custoManutencao;
    }
}

