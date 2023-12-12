package codigo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma frota de veículos.
 */
public class Frota {

    private List<Veiculo> veiculos;

    /**
     * Construtor da classe Frota.
     * 
     * @param tamanhoFrota O tamanho máximo da frota.
     */
    public Frota(int tamanhoFrota) {
        this.veiculos = new ArrayList<>(tamanhoFrota);
    }

    /**
     * Gera um relatório sobre a frota.
     * 
     * @return Uma string contendo o relatório.
     */
    public String relatorio() {
        StringBuilder sb = new StringBuilder("\nVeiculos da Frota: ");
        sb.append("\n");
        for (Veiculo veiculo : veiculos) {
            sb.append(veiculo.getPlaca()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Localiza um veículo na frota pela placa.
     * 
     * @param placa A placa do veículo a ser localizado.
     * @return O veículo encontrado ou null se não for encontrado.
     */
    public Veiculo localizarVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }
    

    /**
     * Calcula a quilometragem total da frota.
     * 
     * @return A quilometragem total.
     */
    public double quilometragemTotal() {
        double totalKm = 0;
        for (Veiculo veiculo : veiculos) {
            totalKm += veiculo.kmTotal();
        }
        return totalKm;
    }

    /**
     * Encontra o veículo com a maior quilometragem total na frota.
     * 
     * @return O veículo com a maior quilometragem total.
     */
    public Veiculo maiorKmTotal() {
        Veiculo maior = null;
        for (Veiculo veiculo : veiculos) {
            if (maior == null || veiculo.kmTotal() > maior.kmTotal()) {
                maior = veiculo;
            }
        }
        return maior;
    }

    /**
     * Encontra o veículo com a maior quilometragem média na frota.
     * 
     * @return O veículo com a maior quilometragem média.
     */
    public Veiculo maiorKmMedia() {
        Veiculo maiorMedia = null;
        double maiorKmMedia = 0.0;

        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                double kmMediaAtual = veiculo.kmMedia(); // Assumindo que kmMedia() será implementada na Classe Veiculo

                if (maiorMedia == null || kmMediaAtual > maiorKmMedia) {
                    maiorMedia = veiculo;
                    maiorKmMedia = kmMediaAtual;
                }
            }
        }
        return maiorMedia;
    }

    /**
     * Metodo para adicionar um veiculo a frota
     * @param veiculo
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    /**
     * Metodo para remover um veiculo da frota
     * @param veiculo
     * @return
     */
    public boolean removerVeiculo(Veiculo veiculo) {
        return veiculos.remove(veiculo);
    }

    /**
     * Metodo para obter a quantidade de veiculos na frota
     * @return
     */
    public int quantidadeVeiculos() {
        return veiculos.size();
    }

    public Veiculo[] getVeiculos() {
        return veiculos.toArray(new Veiculo[0]);
    }
}
