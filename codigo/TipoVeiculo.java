package codigo;

import java.io.*;
import java.util.*;

public enum TipoVeiculo {

	CARRO(50, 10.000, 10.000),
	CAMINHAO(60, 10.000, 12.000),
	FURGAO(80, 10.000, 12.000),
	VAN(250, 20.000, 20.000);

	private final double tamanhoTanque;
	private final double distManutencaoPeriodica;
	private final double distTrocarPneu;

	TipoVeiculo(double tamanhoTanque, double distManutencaoPeriodica, double distTrocarPneu){
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
