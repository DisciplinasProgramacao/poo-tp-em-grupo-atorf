package codigo;

import java.io.*;
import java.util.*;

/**
 * Classe que representa um veículo da frota.
 */
public class Veiculo {

	private static int MAX_ROTAS;
	private static double CONSUMO;
	private String placa;
	private Rota[] rotas;
	private int quantRotas;
	private Tanque tanque;
	private double totalReabastecido;

	/**
	 * Construtor da classe Veiculo
	 * @param placaNova
	 */

	 public Veiculo(String placa, int quantRotas, Tanque tanque, double totalReabastecido) {
		this.placa = placa;
		this.quantRotas = quantRotas;
		this.tanque = tanque;
		this.totalReabastecido = totalReabastecido;
		this.rotas = new Rota[quantRotas];
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
		boolean tmp = false;
		if (quantRotas < MAX_ROTAS) {
			rotas[quantRotas] = rota;
			quantRotas++;
			return tmp;
		} else {
			return tmp;
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
		for (Rota rota : rotas) {
			totalKmVeiculo += rota.getQuilometragem();
		}
		return totalKmVeiculo;
	}

	/**
	 * O método irá retonar a quilometragem média do veículo.
	 * 
	 * @return Quilometragem médida do veículo.
	 */
	public double kmMedia() {
		if (quantRotas == 0) {
			return 0;
		} else {
			return kmTotal() / quantRotas;
		}
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

	public double calculaDespesas(Combustivel tipoCombustivel, Manutencao tipoManutencao, double kmAtual) {
		double custoCombustivel = tipoCombustivel.getPrecoLitro();

        double consumoMedio = tipoCombustivel.getConsumoMedio();
        double quantidadeCombustivel = kmAtual / consumoMedio;
        double custoTotalCombustivel = quantidadeCombustivel * custoCombustivel;

        double custoManutencao = tipoManutencao.calcularCusto(kmAtual);

        double custoTotal = custoTotalCombustivel + custoManutencao;

        return custoTotal;
		
	}

}
