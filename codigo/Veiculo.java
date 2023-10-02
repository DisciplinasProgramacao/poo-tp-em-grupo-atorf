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
	 * 
	 * @param placa
	 * @param quantRotas
	 * @param tanque
	 * @param totalReabastecido
	 * @param rotas
	 */

	public Veiculo(String placa, int quantRotas, Tanque tanque, double totalReabastecido, Rota[] rotas) {
		this.placa = placa;
		this.quantRotas = quantRotas;
		this.tanque = tanque;
		this.totalReabastecido = totalReabastecido;
		this.rotas = rotas;
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

	public double autonomiaMaxima() {
		return tanque.autonomiaMaxima() * CONSUMO;
	}

	/**
	 * Método que irá retornar a autonomia atual.
	 * 
	 * @return A autonomia atual.
	 */
	public double autonomiaAtual() {
		return tanque.autonomiaAtual() * CONSUMO;
	}

	/**
	 * Com esse metódo iremos passar a quantidade
	 * 
	 * @param litros
	 * @return A quantidade do tanque abastecido
	 */
	public double abastecer(double litros) {
		double abastecendoTanque = tanque.abastecer(litros);
		totalReabastecido += abastecendoTanque;
		return totalReabastecido;
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
			totalKmVeiculo += rotas.getQuilometragem();
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
			return kmTotal() /quantRotas;
		}
	}

	/**
	 * Irá passar como parâmetro uma rota e essa será percorrida caso o limite não
	 * tenha sido atingido.
	 * 
	 * @param rota
	 * @throws Exception Se o limite de rotas for atingido.
	 */
	public void percorrerRota(Rota rota) throws Exception {
		if (quantRotas < MAX_ROTAS) {
			rotas[quantRotas] = rota;
			quantRotas++;
		} else {
			throw new Exception("Limite de rotas atingido.");
		}
	}

}
