package codigo;

import java.io.*;
import java.util.*;

/**
 * Interface para definir o contrato de manutenção de veículos.
 * Esta interface especifica métodos para determinar a necessidade de manutenção,
 * calcular custos de manutenção, registrar diferentes tipos de manutenção e informar sobre a manutenção realizada.
 */
public interface Manutencao {
    /**
     * Determina se o veículo precisa de manutenção periódica.
     *
     * @param kmAtual A quilometragem atual do veículo.
     * @return Verdadeiro se o veículo precisar de manutenção periódica, falso caso contrário.
     */
    boolean precisaManutencaoPeriodica(double kmAtual);

    /**
     * Determina se o veículo precisa de troca de pneus.
     *
     * @param kmAtual A quilometragem atual do veículo.
     * @return Verdadeiro se o veículo precisar de troca de pneus, falso caso contrário.
     */
    boolean precisaTrocaPecas(double kmAtual);

    /**
     * Calcula o custo da manutenção com base na quilometragem atual do veículo.
     *
     * @param kmAtual A quilometragem atual do veículo.
     * @return O custo calculado da manutenção.
     */
    double calcularCusto(double kmAtual);

    /**
     * Registra a realização de manutenção periódica no veículo.
     *
     * @param kmAtual A quilometragem atual na qual a manutenção é realizada.
     */
    void registrarManutencaoPeriodica(double kmAtual);

    /**
     * Registra a realização de troca de pneus no veículo.
     *
     * @param kmAtual A quilometragem atual na qual a troca de pneus é realizada.
     */
    void registrarTrocaPecas(double kmAtual);

    /**
     * Fornece informações sobre a manutenção realizada no veículo.
     *
     * @return Uma string descrevendo a manutenção realizada.
     */
    String informarManutencao();
}
