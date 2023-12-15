
package codigo;

/**
 * Classe que representa uma rota feita por um veículo.
 * Armazena informações sobre a quilometragem percorrida e a data em que a rota
 * foi realizada.
 */
public class Rota {

    private double quilometragem;
    private Data data;

    /**
     * Construtor para a classe Rota.
     * 
     * @param quilometragem A quilometragem percorrida na rota.
     * @param data          A data em que a rota foi realizada.
     */
    public Rota(double quilometragem, Data data) {
        if (quilometragem < 25000) {
            this.quilometragem = quilometragem;
            this.data = data;
        } else {
            System.out.println("ERRO: Rota superior ao limite");
        }
    }
    

    /**
     * Retorna a quilometragem percorrida na rota.
     *
     * @return A quilometragem da rota.
     */
    public double getQuilometragem() {
        return quilometragem;
    }

    /**
     * Retorna a data em que a rota foi realizada.
     *
     * @return A data da rota.
     */
    public Data getData() {
        return data;
    }

    /**
     * Gera um relatório formatado da rota.
     *
     * @return Uma string contendo informações formatadas sobre a rota.
     */

    public String relatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("==========================");
        sb.append("Data da Rota: ").append(data.dataFormatada());
        sb.append("\nQuilometragem: ").append(quilometragem).append(" km");

        return sb.toString();
    }
}