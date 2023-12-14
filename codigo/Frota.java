package codigo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma frota de veículos.
 * Esta classe gerencia uma coleção de veículos, permitindo operações como
 * adição de veículos,
 * geração de relatórios e outras funcionalidades relacionadas ao gerenciamento
 * da frota.
 */
public class Frota {

    private List<Veiculo> veiculos;

    /**
     * Construtor da classe Frota.
     * Inicializa a lista de veículos com a capacidade baseada no tamanho máximo
     * fornecido.
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
            sb.append(veiculo.getPlaca()).append(" : ");
            sb.append(veiculo.getTipoVeiculo()).append(" : ");
            sb.append(veiculo.getTipoCombustivel()).append(" : ");
            sb.append(veiculo.getQuilometragem()).append("KM");
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Localiza um veículo na frota pelo número da placa.
     * Este método percorre a lista de veículos na frota e retorna o veículo cuja
     * placa corresponde
     * à placa fornecida. Se nenhum veículo for encontrado com a placa especificada,
     * retorna null.
     *
     * @param placa A placa do veículo a ser localizado.
     * @return O veículo encontrado ou null se não for encontrado.
     */
    public Veiculo localizarVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null && veiculo.getPlaca() != null && veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    /**
     * Calcula a quilometragem total da frota.
     * Este método soma a quilometragem de todos os veículos na frota e retorna o
     * total.
     *
     * @return A quilometragem total da frota.
     */
    public double quilometragemTotal() {
        double totalKm = 0;
        for (Veiculo veiculo : veiculos) {
            totalKm += veiculo.getQuilometragem();
        }
        return totalKm;
    }

    /**
     * Encontra o veículo com a maior quilometragem total na frota.
     * Este método percorre todos os veículos na frota e retorna o veículo com a
     * maior quilometragem total.
     * Se a frota estiver vazia, retorna null.
     *
     * @return O veículo com a maior quilometragem total, ou null se a frota estiver
     *         vazia.
     */
    public Veiculo maiorKmTotal() {
        Veiculo maior = null;
        for (Veiculo veiculo : veiculos) {
            if (maior == null || veiculo.getQuilometragem() > maior.getQuilometragem()) {
                maior = veiculo;
            }
        }
        return maior;
    }

    /**
     * Encontra o veículo com a maior quilometragem média na frota.
     * Este método percorre todos os veículos na frota e retorna o veículo com a
     * maior quilometragem média.
     * A quilometragem média pode ser uma medida de uso regular do veículo.
     * Se a frota estiver vazia, retorna null.
     *
     * @return O veículo com a maior quilometragem média, ou null se a frota estiver
     *         vazia.
     */
    public Veiculo maiorKmMedia() {
        Veiculo maiorMedia = null;
        double maiorKmMedia = 0.0;

        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                double kmMediaAtual = veiculo.kmMedia(); // Assumindo que kmMedia() será implementada na Classe
                                                                  // Veiculo

                if (maiorMedia == null || kmMediaAtual > maiorKmMedia) {
                    maiorMedia = veiculo;
                    maiorKmMedia = kmMediaAtual;
                }
            }
        }
        return maiorMedia;
    }

    /**
     * Adiciona um veículo à frota.
     * Este método insere um veículo na lista de veículos da frota.
     * Pode ser usado para expandir a frota com novos veículos.
     *
     * @param veiculo O veículo a ser adicionado à frota.
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    /**
     * Remove um veículo da frota.
     * Este método tenta remover um veículo específico da lista de veículos da
     * frota.
     * Retorna verdadeiro se o veículo for removido com sucesso, caso contrário,
     * retorna falso.
     *
     * @param veiculo O veículo a ser removido da frota.
     * @return Verdadeiro se o veículo for removido com sucesso, falso caso
     *         contrário.
     */
    public boolean removerVeiculo(Veiculo veiculo) {
        return veiculos.remove(veiculo);
    }

    /**
     * Retorna a quantidade de veículos na frota.
     * Este método conta e retorna o número de veículos atualmente presentes na
     * frota.
     *
     * @return O número de veículos na frota.
     */
    public int quantidadeVeiculos() {
        return veiculos.size();
    }

    /**
     * Retorna uma array de veículos presentes na frota.
     * Este método permite acesso à lista completa de veículos na frota,
     * retornando-os como uma array.
     *
     * @return Array de veículos presentes na frota.
     */
    public Veiculo[] getVeiculos() {
        return veiculos.toArray(new Veiculo[0]);
    }
}
