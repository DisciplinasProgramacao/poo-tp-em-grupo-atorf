package codigo;

import java.io.*;
import java.util.*;

/**
 * Classe que representa um veículo da frota.
 * Armazena informações como placa, rotas realizadas, tipo de veículo,
 * combustível utilizado e manutenção.
 */
public class Veiculo {

	private static int MAX_ROTAS = 50;
	private String placa;
	private Rota[] rotas;
	private int quantRotas;
	private Tanque tanque;
	private double quilometragem;
	private TipoVeiculo tipoVeiculo;
	private Combustivel tipCombustivel;

	private Manutencao manutencao;

	/**
	 * Construtor da classe Veiculo
	 * 
	 * @param placaNova
	 */
	public Veiculo(String placa, TipoVeiculo tipoVeiculo, Combustivel tipoCombustivel, Manutencao manutencao,
			Tanque tanque, double quilometragem) {
		this.placa = placa;
		this.tipoVeiculo = tipoVeiculo;
		this.tanque = new Tanque(tipoCombustivel, tipoVeiculo.getTamanhoTanque(), 0);
		this.rotas = new Rota[MAX_ROTAS];
		this.manutencao = manutencao;
		this.tanque = tanque;
		this.quilometragem = quilometragem;
		this.tipCombustivel = tipoCombustivel;
	}

	/**
	 * Retorna o objeto de manutenção associado ao veículo.
	 *
	 * @return O objeto de manutenção do veículo.
	 */
	public Manutencao getManutencao() {
		return manutencao;
	}

	/**
	 * Retorna a quilometragem total do veículo.
	 *
	 * @return A quilometragem do veículo.
	 */
	public double getQuilometragem() {
		return quilometragem;
	}

	/**
	 * Retorna o tanque do veiculo.
	 * 
	 * @return O tanque do veiculo.
	 */
	public Tanque getTanque() {
		return tanque;
	}

	/**
	 * Define o tamanho do tanque do veiculo.
	 * 
	 * @param tanque
	 */
	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}

	/**
	 * Retorna as rotas que o veiculo possui.
	 * 
	 * @return As rotas do veiculo,
	 */
	public Rota[] getRotas() {
		return rotas;
	}

	/**
	 * Define a quilometragem total do veículo.
	 *
	 * @param quilometragem A nova quilometragem do veículo.
	 */
	public void setQuilometragem(double quilometragem) {
		this.quilometragem = quilometragem;
	}

	/**
	 * Retorna o tipo de combustivel do veiculo.
	 * 
	 * @return Tipo de combustivel do veiculo.
	 */
	public Combustivel getTipoCombustivel() {
		return tanque.getTipoCombustivel();
	}

	/**
	 * Retorna o tipo de veículo da frota.
	 * 
	 * @return Retorna o tipo do veiculo.
	 */
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	/**
	 * Define o tipo de veiculo.
	 * 
	 * @param tipoVeiculo
	 */
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	/**
	 * Método get irá retornar a placa do veículo, que posteriormente será utilizada
	 * em outos metódos.
	 * 
	 * @return String com a placa do veículo.
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * Adiciona uma nova rota ao veículo.
	 * Atualiza a quilometragem total do veículo com base na rota adicionada. Além
	 * disso, esse metodo irá calcular se foi preciso abastecer o veiculo durante a
	 * rota e ele retorna o valor que foi gasto.
	 *
	 * @param rota A rota a ser adicionada.
	 * @return String informando se a rota foi adicionada corretamente e, caso
	 *         necessário, quanto foi gasto para abastecer o veiculo.
	 */

	public String addRota(Rota rota) {
		StringBuilder sb = new StringBuilder();

		if (quantRotas < MAX_ROTAS) {
			double quilometragemPercorrida = rota.getQuilometragem();
			double consumo = tanque.getTipoCombustivel().getConsumoMedio() * quilometragemPercorrida;

			// Verifica se há combustível suficiente para percorrer a rota
			if (consumo > tanque.getCapacidadeAtual()) {
				double litrosNecessarios = consumo - tanque.getCapacidadeAtual();
				double precoLitro = tanque.getTipoCombustivel().getPrecoLitro();
				double custoAbastecimento = litrosNecessarios * precoLitro;

				tanque.abastecer(litrosNecessarios);
				tanque.setCapacidadeAtual(tanque.getCapacidadeAtual() + litrosNecessarios);

				sb.append("Rota adicionada com sucesso!");
				sb.append("\nAbastecimento automático realizado para percorrer a rota. \nGasto: R$ ")
						.append(custoAbastecimento);
			} else {
				// Atualiza a quilometragem total do veículo com a quilometragem da rota
				this.quilometragem += quilometragemPercorrida;

				// Atualiza a capacidade do tanque após percorrer a rota
				tanque.setCapacidadeAtual(tanque.getCapacidadeAtual() - consumo);

				sb.append("Rota adicionada com sucesso!");
			}

			rotas[quantRotas] = rota;
			quantRotas++;

			return sb.toString(); // Rota adicionada com sucesso
		} else {
			sb.append("Limite de rotas atingido.");
			return sb.toString(); // Limite de rotas atingido
		}
	}

	/**
	 * Método que irá retornar a autonomia máxima.
	 * 
	 * @return A autonomia máxima.
	 */

	private double autonomiaMaxima() {
		return tanque.autonomiaMaxima();
	}

	/**
	 * Método que irá retornar a autonomia atual.
	 * 
	 * @return A autonomia atual.
	 */
	private double autonomiaAtual() {
		return tanque.autonomiaAtual();
	}

	/**
	 * Esse método bastece o veículo com a quantidade de litros especificada.
	 * Ele verifica se a quantidade a ser abastecida ultrapassa a capacidade total
	 * do tanque, nesse caso, o veículo será abastecido até sua capacidade máxima.
	 * 
	 * @param litros
	 * @return A quantidade do tanque abastecido
	 */
	public String abastecer(double litrosAbastecimento) {
		Tanque tanqueVeiculo = this.tanque;
		return tanqueVeiculo.abastecer(litrosAbastecimento).toString();
	}

	/**
	 * Esse método irá retornar a quilometragem percorrida no mês atual.
	 * 
	 * @return Quilometragem percorrida no mês atual.
	 */
	public double kmNoMes() {
		Data dataMes = Data.getDataAtualizada();
		double totalKmMes = 0;

		for (Rota rota : rotas) {
			Data dataRota = rota.getData();
			if (dataRota.getMes() == dataMes.getMes()) {
				totalKmMes += rota.getQuilometragem();
			}
		}
		return totalKmMes;
	}

	/**
	 * Esse método irá retornar a quilometragem total percorrida pelo veículo.
	 * 
	 * @return Quilometragem total percorrida pelo veículo.
	 */
	public double kmTotal() {
		double totalKmVeiculo = 0;
		if (rotas != null) {
			for (Rota rota : rotas) {
				if (rota != null) {
					totalKmVeiculo += rota.getQuilometragem();
				}
			}
		}
		return totalKmVeiculo;
	}

	/**
	 * O método irá retonar a quilometragem média do veículo.
	 * 
	 * @return Quilometragem médida do veículo.
	 */

	public double kmMedia() {
		double kmTotal = this.kmTotal();
		int totalRotas = this.quantRotas;

		if (totalRotas == 0) {
			return 0; // Evita divisão por zero
		}

		return kmTotal / totalRotas;
	}

	/**
	 * Irá passar como parâmetro uma rota e essa será percorrida caso o limite não
	 * tenha sido atingido.
	 * 
	 * @param rota
	 * @throws Exception Se o limite de rotas for atingido.
	 */
	private void percorrerRota(Rota rota) throws Exception {
		if (quantRotas < MAX_ROTAS) {
			rotas[quantRotas] = rota;
			quantRotas++;
		} else {
			throw new Exception("Limite de rotas atingido.");
		}
	}

	/**
	 * Calcula as despesas totais do veículo, considerando o custo do combustível e
	 * da manutenção.
	 *
	 * @return O valor total das despesas, incluindo combustível e manutenção.
	 */

	public double calcularDespesaTotal() {
		double precoCombustivel = tanque.getTipoCombustivel().getPrecoLitro();
		double consumoCombustivel = tanque.getTipoCombustivel().getConsumoMedio();
		double custoCombustivel = (this.quilometragem / consumoCombustivel) * precoCombustivel;
		double custoManutencao = this.manutencao.calcularCusto(this.quilometragem);
		return custoCombustivel + custoManutencao;
	}

	/**
	 * Metodo para retonar o relatorio com todas as depesas do veiculo, sejam os
	 * gastos com combustivel e os custos com manutenção.
	 * 
	 * @return Retorna as despesas do veículo.
	 */
	public String relatorioDespesas() {
		StringBuilder sb = new StringBuilder("\n==========================");
		sb.append("\nRelatorio de despesas do veiculo " + getPlaca());

		double precoCombustivel = tanque.getTipoCombustivel().getPrecoLitro();
		double consumoCombustivel = tanque.getTipoCombustivel().getConsumoMedio();
		double custoCombustivel = (this.quilometragem / consumoCombustivel) * precoCombustivel;
		double custoManutencao = this.manutencao.calcularCusto(this.quilometragem);

		sb.append("\nCusto com combustivel: " + custoCombustivel);
		sb.append("\nCusto com manutenção: " + custoManutencao);
		return sb.toString();
	}

	/**
	 * Verifica se é possível abastecer a quantidade especificada de combustível no
	 * tanque do veículo.
	 *
	 * @param litrosAbastecimento A quantidade de combustível a ser abastecida.
	 * @return true se for possível abastecer essa quantidade, false caso contrário.
	 */
	public boolean podeAbastecer(double litrosAbastecimento) {
		return tanque.getCapacidadeAtual() + litrosAbastecimento <= tanque.getCapacidadeMaxima();
	}

	/**
	 * Atualiza a quilometragem total do veículo, adicionando a quilometragem de uma
	 * rota recente.
	 * Também verifica se alguma manutenção é necessária com base na nova
	 * quilometragem.
	 *
	 * @param quilometragemRota A quilometragem da rota adicionada ao veículo.
	 */
	public void atualizarQuilometragem(double quilometragemRota) {
		this.quilometragem += quilometragemRota;
		verificarManutencao(); // Verifica se alguma manutenção é necessária
	}

	/**
	 * Verifica se o veículo precisa de manutenção periódica ou troca de pneus e
	 * registra a manutenção se necessário.
	 */
	public void verificarManutencao() {
		if (manutencao.precisaManutencaoPeriodica(quilometragem)) {
			manutencao.registrarManutencaoPeriodica(quilometragem);
		}
		if (manutencao.precisaTrocaPecas(quilometragem)) {
			manutencao.registrarTrocaPecas(quilometragem);
		}
	}

	/**
	 * Fornece uma representação textual do veículo, incluindo tipo, placa e
	 * quilometragem.
	 *
	 * @return Uma string descrevendo o veículo.
	 */
	@Override
	public String toString() {
		return "==========================" + "\n" + getTipoVeiculo() + " \nPortador da placa" +
				": " + placa + '\n' +
				"Quilometragem: " + getQuilometragem() + "km "
				+ "\nTanque: " + tanque.getCapacidadeAtual() + "L";
	}

}
