package codigo;

import java.io.*;
import java.util.*;

/**
 * Enumeração para diferentes tipos de veículos.
 * Cada tipo de veículo possui um tamanho de tanque específico e distâncias
 * determinadas para manutenção periódica e troca de pneus.
 */
public enum TipoVeiculo {

	CARRO(50, 10.000, 10.000),
	CAMINHAO(60, 10.000, 12.000),
	FURGAO(80, 10.000, 12.000),
	VAN(250, 20.000, 20.000);

	private final double tamanhoTanque;
	private final double distManutencaoPeriodica;
	private final double distTrocarPneu;

	/**
	 * Construtor para o enum TipoVeiculo.
	 *
	 * @param tamanhoTanque           Tamanho do tanque de combustível do veículo
	 *                                (em litros).
	 * @param distManutencaoPeriodica Distância para realização de manutenção
	 *                                periódica (em quilômetros).
	 * @param distTrocarPneu          Distância para troca de pneus (em
	 *                                quilômetros).
	 */
	TipoVeiculo(double tamanhoTanque, double distManutencaoPeriodica, double distTrocarPneu) {
		this.tamanhoTanque = tamanhoTanque;
		this.distManutencaoPeriodica = distManutencaoPeriodica;
		this.distTrocarPneu = distTrocarPneu;
	}

	public double getTamanhoTanque() {
		return tamanhoTanque;
	}

	public double getDistManutencaoPeriodica() {
		return distManutencaoPeriodica;
	}

	public double getDistTrocarPneu() {
		return distTrocarPneu;
	}

}
