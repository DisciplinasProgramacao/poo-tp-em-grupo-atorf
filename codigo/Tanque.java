package codigo;

import java.io.*;
import java.util.*;

/**
 * Classe que representa o tanque de combustível de um veículo.
 * Mantém informações sobre a capacidade máxima do tanque, a capacidade atual e
 * um histórico de abastecimentos.
 */
public class Tanque {

	private static double CONSUMO;
	private Combustivel tipoCombustivel;
	private double capacidadeMaxima;
	private double capacidadeAtual;
	private double totalReabastecido;
	private List<Abastecimento> historicoAbastecimentos;

	/**
	 * Construtor para a classe Tanque.
	 *
	 * @param capacidadeMaxima A capacidade máxima do tanque.
	 * @param capacidadeAtual  A capacidade atual do tanque.
	 */
	public Tanque(Combustivel tipoCombustivel, double capacidadeMaxima, double capacidadeAtual) {
		this.tipoCombustivel = tipoCombustivel;
		this.capacidadeMaxima = capacidadeMaxima;
		this.capacidadeAtual = capacidadeAtual;
	}

	public static double getCONSUMO() {
		return CONSUMO;
	}

	public double getCapacidadeMaxima() {
		return capacidadeMaxima;
	}

	public double getCapacidadeAtual() {
		return capacidadeAtual;
	}

	public Combustivel getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(Combustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	/**
	 * Calcula a autonomia máxima do veículo com base na capacidade máxima do tanque
	 * e no consumo.
	 *
	 * @return A autonomia máxima do veículo.
	 */
	public double autonomiaMaxima() {
		double autonomiaMaxima = capacidadeMaxima / CONSUMO;
		return autonomiaMaxima;
	}

	/**
	 * Calcula a autonomia atual do veículo com base na capacidade atual do tanque e
	 * no consumo.
	 *
	 * @return A autonomia atual do veículo.
	 */
	public double autonomiaAtual() {
		double autonomiaAtual = capacidadeAtual / CONSUMO;
		return autonomiaAtual;
	}

		/**
	 * Verifica se é possível abastecer a quantidade especificada de combustível no
	 * tanque do veículo.
	 *
	 * @param litrosAbastecimento A quantidade de combustível a ser abastecida.
	 * @return true se for possível abastecer essa quantidade, false caso contrário.
	 */
	public boolean podeAbastecer(double litrosAbastecimento) {
		return capacidadeAtual + litrosAbastecimento <= capacidadeMaxima;
	}


	/**
	 * Abastece o tanque com uma quantidade especificada de litros de combustível.
	 * Se a quantidade de litros exceder a capacidade máxima do tanque, o
	 * abastecimento não é realizado.
	 *
	 * @param litros A quantidade de combustível a ser adicionada ao tanque.
	 * @return A nova capacidade atual do tanque após o abastecimento.
	 */
	public double abastecer(double litros) {
		if (capacidadeAtual + litros <= capacidadeMaxima) {
			capacidadeAtual += litros; // Atualizando a capacidade atual do tanque
		}

		totalReabastecido = litros;
		return totalReabastecido;
	}

	/**
	 * Registra um abastecimento no histórico, incluindo a quantidade de litros e o
	 * preço por litro.
	 * Atualiza a capacidade atual do tanque com base no abastecimento.
	 *
	 * @param litros        A quantidade de combustível abastecida.
	 * @param precoPorLitro O preço por litro do combustível.
	 */
	public void registrarAbastecimento(double litros, double precoPorLitro) {
		if (capacidadeAtual + litros <= capacidadeMaxima) {
			capacidadeAtual += litros;
			historicoAbastecimentos.add(new Abastecimento(litros, precoPorLitro));
		}
	}

	/**
	 * Calcula o custo total de todos os abastecimentos realizados.
	 *
	 * @return O custo total dos abastecimentos.
	 */
	public double custoTotalAbastecimentos() {
		double total = 0;
		for (Abastecimento abastecimento : historicoAbastecimentos) {
			total += abastecimento.getLitros() * abastecimento.getPrecoPorLitro();
		}
		return total;
	}
}
