package codigo;

/**
 * Classe interna que representa um abastecimento do tanque.
 * Armazena informações sobre a quantidade de litros abastecidos e o preço por
 * litro.
 */
public class Abastecimento {
    private double litros;
    private double precoPorLitro;

    /**
     * Construtor para a classe Abastecimento.
     * 
     * @param litros        A quantidade de litros abastecidos.
     * @param precoPorLitro O preço por litro do combustível abastecido.
     */
    public Abastecimento(double litros, double precoPorLitro) {
        this.litros = litros;
        this.precoPorLitro = precoPorLitro;
    }

    /**
     * Retorna a quantidade de litros abastecidos.
     * 
     * @return A quantidade de litros abastecidos.
     */
    public double getLitros() {
        return litros;
    }

    /**
     * Retorna o preço por litro do combustível abastecido.
     * 
     * @return O preço por litro do combustível.
     */
    public double getPrecoPorLitro() {
        return precoPorLitro;
    }
}