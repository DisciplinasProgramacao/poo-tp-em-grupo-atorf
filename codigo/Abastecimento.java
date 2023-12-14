package codigo;

/**
 * Classe interna que representa um abastecimento do tanque.
 * Armazena informações sobre a quantidade de litros abastecidos e o preço por
 * litro.
 */
public class Abastecimento {
    private double litros;
    private double precoPorLitro;

    public Abastecimento(double litros, double precoPorLitro) {
        this.litros = litros;
        this.precoPorLitro = precoPorLitro;
    }

    public double getLitros() {
        return litros;
    }

    public double getPrecoPorLitro() {
        return precoPorLitro;
    }
}