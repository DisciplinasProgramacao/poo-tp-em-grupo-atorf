package codigo;

import java.io.*;
import java.util.*;

/**
 * Classe que representa um veículo da frota.
 */
public class Veiculo {

	private static int MAX_ROTAS = 10;
	private static double CONSUMO;
	private String placa;
	private Rota[] rotas;
	private int quantRotas;
	private Tanque tanque;
	private double totalReabastecido;
	private double quilometragem;
	private TipoVeiculo tipoVeiculo;
	private Combustivel tipoCombustivel;
	public Manutencao getManutencao() {
		return manutencao;
	}

	private Manutencao manutencao;

	/**
	 * Construtor da classe Veiculo
	 * 
	 * @param placaNova
	 */


	// Construtor que aceita um tipo específico de Manutencao
	public Veiculo(String placa, TipoVeiculo tipoVeiculo, Combustivel tipoCombustivel, Manutencao manutencao, Tanque tanque) {
		this.placa = placa;
		this.tipoVeiculo = tipoVeiculo;
		this.tanque = tanque;
		this.rotas = new Rota[MAX_ROTAS];
		this.tipoCombustivel = tipoCombustivel;
		this.manutencao = manutencao;
		this.tanque = tanque;
	}

	public Veiculo(String placa2, TipoVeiculo tipoVeiculo2, Tanque tq, Manutencao manutencao2) {
	}

	public Combustivel getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(Combustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

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
	 * Método par adicionar uma nova rota.
	 * 
	 * @param rota
	 * @return True se a rota foi adicionada, ou false caso o limite de rotas for
	 *         atingido.
	 */

	 public boolean addRota(Rota rota) {
		if (quantRotas < MAX_ROTAS) {
			rotas[quantRotas] = rota;
			quantRotas++;
			atualizarQuilometragem(rota.getQuilometragem()); // Atualiza a quilometragem total
			return true;
		} else {
			return false;
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
	public double abastecer(double litros) {
		if (litros < tanque.getCapacidadeMaxima() - tanque.getCapacidadeAtual()) {
			double abastecendoTanque = tanque.abastecer(litros);
			totalReabastecido += abastecendoTanque;
			return totalReabastecido;
		} else {
			double tanqueCheio = tanque.getCapacidadeMaxima() - tanque.getCapacidadeAtual();
			double totalAbastecido = tanque.abastecer(tanqueCheio);
			totalReabastecido += totalAbastecido;
			return totalReabastecido;
		}
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
		double totalKmVeiculo = 0;
		int rotasValidas = 0;
		for (Rota rota : rotas) {
			if (rota != null) {
				totalKmVeiculo += rota.getQuilometragem();
				rotasValidas++;
			}
		}
		return rotasValidas > 0 ? totalKmVeiculo / rotasValidas : 0;
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
	 * Esse metódo será utilizado para calcular as despesas totais do veículo,
	 * considerando o seu tipo de combustivel, qual manutenção ele irá realizar e
	 * sua quilometragem.
	 * 
	 * @param tipoCombustivel
	 * @param tipoManutencao
	 * @param kmAtual
	 * @return O valor das despesas totais contando o custo do combustivel e da
	 *         manutenção
	 */

	 public double calcularDespesaTotal() {
		double precoCombustivel = this.tipoCombustivel.getPrecoLitro();
		double consumoCombustivel = this.tipoCombustivel.getConsumoMedio();
		double custoCombustivel = (this.quilometragem / consumoCombustivel) * precoCombustivel;
		double custoManutencao = this.manutencao.calcularCusto(this.quilometragem);
		return custoCombustivel + custoManutencao;
	}
	

	/**
	 * Verifica se é possível abastecer a quantidade especificada de combustível.
	 *
	 * @param litrosAbastecimento a quantidade de combustível a ser abastecida.
	 * @return true se for possível abastecer essa quantidade, false caso contrário.
	 */
	public boolean podeAbastecer(double litrosAbastecimento) {
		return tanque.getCapacidadeAtual() + litrosAbastecimento <= tanque.getCapacidadeMaxima();
	}

	public void atualizarQuilometragem(double quilometragemRota) {
		this.quilometragem += quilometragemRota;
	}
	
	@Override
	public String toString() {
		return "Veiculo{" +
				"placa='" + placa + '\'' +
				", quilometragem=" + kmTotal() +
				'}';
	}

}
