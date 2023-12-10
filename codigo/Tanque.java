package codigo;

import java.io.*;
import java.util.*;

public class Tanque {

	private static double CONSUMO;
	private double capacidadeMaxima;
	private double capacidadeAtual;

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
			System.out.println("Carro abastecido.");
			double totalAbastecido = capacidadeAtual + litros;
			return totalAbastecido;
		} else {
			System.out.println("Carro não pode ser abastecido com essa quantidade de litros.");
			return capacidadeAtual;
		}
	}

	public double autonomiaMaxima() {
		double autonomiaMaxima=capacidadeMaxima/CONSUMO;
		System.out.println("Aautonomia máxima do carro é de: " + autonomiaMaxima);
		return autonomiaMaxima;
	}

	public double autonomiaAtual() {
		double autonomiaAtual=capacidadeAtual/CONSUMO;
		System.out.println("Aautonomia máxima do carro é de: " + autonomiaAtual);
		return autonomiaAtual;
	}

}
