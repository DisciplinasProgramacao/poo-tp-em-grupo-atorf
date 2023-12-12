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
        double custoTotal = 0;
        
        // Calcular custo de manutenção periódica
        if (kmAtual - kmUltimaManutencaoPeriodica >= KM_MANUTENCAO_PERIODICA) {
            custoTotal += VALOR_PERIODICA;
        }

        // Calcular custo de troca de pneus
        if (kmAtual - kmUltimaTrocaPneus >= KM_TROCA_PNEUS) {
            custoTotal += VALOR_TROCA_PNEU;
        }

        return custoTotal;
    }

    public String informarManutencao() {
        StringBuilder info = new StringBuilder();
        info.append("Última manutenção periódica: ").append(kmUltimaManutencaoPeriodica).append(" km\n");
        info.append("Próxima manutenção periódica: ").append(kmUltimaManutencaoPeriodica + KM_MANUTENCAO_PERIODICA).append(" km\n");
        info.append("Última troca de pneus: ").append(kmUltimaTrocaPneus).append(" km\n");
        info.append("Próxima troca de pneus: ").append(kmUltimaTrocaPneus + KM_TROCA_PNEUS).append(" km\n");
        return info.toString();
    }

}

