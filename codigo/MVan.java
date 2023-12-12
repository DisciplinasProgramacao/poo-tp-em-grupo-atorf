package codigo;

import java.io.*;
import java.util.*;


/**
 * Classe que implementa a interface Manutencao para vans.
 * Esta classe define a lógica de manutenção específica para vans, incluindo critérios para manutenção periódica e troca de PECAS,
 * além de calcular os custos de manutenção.
 */
public class MVan implements Manutencao {
    private static final double KM_MANUTENCAO_PERIODICA = 10000;
    private static final double KM_TROCA_PECAS = 12000;
    private static final double VALOR_PERIODICA = 250.0;
    private static final double VALOR_TROCA_PECAS = 500.0;
    private double kmUltimaManutencaoPeriodica = 0;
    private double kmUltimaTrocaPecas = 0;

    @Override
    public boolean precisaManutencaoPeriodica(double kmAtual) {
        return kmAtual - kmUltimaManutencaoPeriodica >= KM_MANUTENCAO_PERIODICA;
    }

    @Override
    public boolean precisaTrocaPecas(double kmAtual) {
        return kmAtual - kmUltimaTrocaPecas >= KM_TROCA_PECAS;
    }

    @Override
    public void registrarManutencaoPeriodica(double kmAtual) {
        this.kmUltimaManutencaoPeriodica = kmAtual;
    }

    @Override
    public void registrarTrocaPecas(double kmAtual) {
        this.kmUltimaTrocaPecas = kmAtual;
    }

    @Override
    public double calcularCusto(double kmAtual) {
        double custoTotal = 0;
        
        // Calcular custo de manutenção periódica
        if (kmAtual - kmUltimaManutencaoPeriodica >= KM_MANUTENCAO_PERIODICA) {
            custoTotal += VALOR_PERIODICA;
        }

        // Calcular custo de troca de PECAS
        if (kmAtual - kmUltimaTrocaPecas >= KM_TROCA_PECAS) {
            custoTotal += VALOR_TROCA_PECAS;
        }

        return custoTotal;
    }

    public String informarManutencao() {
        StringBuilder info = new StringBuilder();
        info.append("Última manutenção periódica: ").append(kmUltimaManutencaoPeriodica).append(" km\n");
        info.append("Próxima manutenção periódica: ").append(kmUltimaManutencaoPeriodica + KM_MANUTENCAO_PERIODICA).append(" km\n");
        info.append("Última troca de PECAS: ").append(kmUltimaTrocaPecas).append(" km\n");
        info.append("Próxima troca de PECAS: ").append(kmUltimaTrocaPecas + KM_TROCA_PECAS).append(" km\n");
        return info.toString();
    }

}

