package codigo;

/**
 * Classe que representa uma frota de veículos.
 */
public class Frota {

    private int tamanhoFrota;
    private Veiculo[] veiculos;

    /**
     * Construtor da classe Frota.
     * @param tamanhoFrota O tamanho máximo da frota.
     */
    public Frota(int tamanhoFrota) {
        this.tamanhoFrota = tamanhoFrota;
        this.veiculos = new Veiculo[tamanhoFrota];
    }

    /**
     * Gera um relatório sobre a frota.
     * @return Uma string contendo o relatório.
     */
    public String relatorio() {
        StringBuilder sb = new StringBuilder();
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                sb.append(veiculo.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Localiza um veículo na frota pela placa.
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
     * @return A quilometragem total.
     */
    public double quilometragemTotal() {
        double totalKm = 0;
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                totalKm += veiculo.kmTotal();
            }
        }
        return totalKm;
    }

    /**
     * Encontra o veículo com a maior quilometragem total na frota.
     * @return O veículo com a maior quilometragem total.
     */
    public Veiculo maiorKmTotal() {
        Veiculo maior = null;
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                if (maior == null || veiculo.kmTotal() > maior.kmTotal()) {
                    maior = veiculo;
                }
            }
        }
        return maior;
    }

    /**
     * Encontra o veículo com a maior quilometragem média na frota.
     * @return O veículo com a maior quilometragem média.
     */
    public Veiculo maiorKmMedia() {
		Veiculo maiorMedia = null;
		double maiorKmMedia = 0.0;
	
		for (int i = 0; i < tamanhoFrota; i++) {
			if (veiculos[i] != null) {
				double kmMediaAtual = veiculos[i].kmMedia();  // Assumindo que kmMedia() será implementada na Classe Veiculo
	
				if (maiorMedia == null || kmMediaAtual > maiorKmMedia) {
					maiorMedia = veiculos[i];
					maiorKmMedia = kmMediaAtual;
				}
			}
		}
		return maiorMedia;
	}
	
}
