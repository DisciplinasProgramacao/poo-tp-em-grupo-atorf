package codigo;

import java.io.*;
import java.util.*;

/**
 * Classe que implementa a interface Manutencao para caminhões.
 * Esta classe define a lógica de manutenção específica para caminhões,
 * incluindo critérios para manutenção periódica e troca de PECAS,
 * além de calcular os custos de manutenção.
 */
public class MCaminhao implements Manutencao {
    private static final double KM_MANUTENCAO_PERIODICA = 20000;
    private static final double KM_TROCA_PECAS = 20000;
    private static final double VALOR_PERIODICA = 500.0;
    private static final double VALOR_TROCA_PECAS = 800.0;
    private double kmUltimaManutencaoPeriodica = 0;
    private double kmUltimaTrocaPecas = 0;

    /**
     * Determina se o veículo precisa de manutenção periódica.
     *
     * @param kmAtual A quilometragem atual do veículo.
     * @return Verdadeiro se o veículo precisar de manutenção periódica, falso caso
     *         contrário.
     */
    @Override
    public boolean precisaManutencaoPeriodica(double kmAtual) {
        return kmAtual - kmUltimaManutencaoPeriodica >= KM_MANUTENCAO_PERIODICA;
    }

    /**
     * Determina se o veículo precisa de troca de pneus.
     *
     * @param kmAtual A quilometragem atual do veículo.
     * @return Verdadeiro se o veículo precisar de troca de pneus, falso caso
     *         contrário.
     */
    @Override
    public boolean precisaTrocaPecas(double kmAtual) {
        return kmAtual - kmUltimaTrocaPecas >= KM_TROCA_PECAS;
    }

    /**
     * Registra a realização de manutenção periódica no veículo.
     *
     * @param kmAtual A quilometragem atual na qual a manutenção é realizada.
     */
    @Override
    public void registrarManutencaoPeriodica(double kmAtual) {
        this.kmUltimaManutencaoPeriodica = kmAtual;
    }

    /**
     * Registra a realização de manutenção periódica no veículo.
     *
     * @param kmAtual A quilometragem atual na qual a manutenção é realizada.
     */
    @Override
    public void registrarTrocaPecas(double kmAtual) {
        this.kmUltimaTrocaPecas = kmAtual;
    }

    /**
     * Calcula o custo da manutenção com base na quilometragem atual do veículo.
     *
     * @param kmAtual A quilometragem atual do veículo.
     * @return O custo calculado da manutenção.
     */
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

    /**
     * Fornece informações sobre a manutenção realizada no veículo.
     *
     * @return Uma string descrevendo a manutenção realizada.
     */
    public String informarManutencao() {
        StringBuilder info = new StringBuilder("\n==========================");
        info.append("\nÚltima manutenção periódica: ").append(kmUltimaManutencaoPeriodica).append(" km\n");
        info.append("Próxima manutenção periódica: ").append(kmUltimaManutencaoPeriodica + KM_MANUTENCAO_PERIODICA)
                .append(" km\n");
        info.append("Última troca de PECAS: ").append(kmUltimaTrocaPecas).append(" km\n");
        info.append("Próxima troca de PECAS: ").append(kmUltimaTrocaPecas + KM_TROCA_PECAS).append(" km\n");
        return info.toString();
    }
}
