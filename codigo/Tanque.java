package codigo;

import java.io.*;
import java.util.*;

public class Tanque {

	private static double CONSUMO;
	private double capacidadeMaxima;
	private double capacidadeAtual;

	public Tanque(double capacidadeMaxima, double capacidadeAtual) {
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

	public double abastecer(double litros) {
		if (capacidadeAtual + litros <= capacidadeMaxima) {
			double totalAbastecido = capacidadeAtual + litros;
			return totalAbastecido;
		} else {
			return capacidadeAtual;
		}
	}

	public double autonomiaMaxima() {
		double autonomiaMaxima=capacidadeMaxima/CONSUMO;
		return autonomiaMaxima;
	}

	public double autonomiaAtual() {
		double autonomiaAtual=capacidadeAtual/CONSUMO;
		return autonomiaAtual;
	}

}
