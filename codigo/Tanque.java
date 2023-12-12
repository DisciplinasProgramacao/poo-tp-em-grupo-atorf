package codigo;

import java.io.*;
import java.util.*;

public class Tanque {

	private static double CONSUMO;
	private double capacidadeMaxima;
	private double capacidadeAtual;
	private List<Abastecimento> historicoAbastecimentos;


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
			capacidadeAtual += litros; // Atualizando a capacidade atual do tanque
		}
		return capacidadeAtual;
	}
	

	public double autonomiaMaxima() {
		double autonomiaMaxima=capacidadeMaxima/CONSUMO;
		return autonomiaMaxima;
	}

	public double autonomiaAtual() {
		double autonomiaAtual=capacidadeAtual/CONSUMO;
		return autonomiaAtual;
	}

	public void registrarAbastecimento(double litros, double precoPorLitro) {
        if (capacidadeAtual + litros <= capacidadeMaxima) {
            capacidadeAtual += litros;
            historicoAbastecimentos.add(new Abastecimento(litros, precoPorLitro));
        }
    }

	public double custoTotalAbastecimentos() {
        double total = 0;
        for (Abastecimento abastecimento : historicoAbastecimentos) {
            total += abastecimento.getLitros() * abastecimento.getPrecoPorLitro();
        }
        return total;
    }

	public class Abastecimento {
        private double litros;
        private double precoPorLitro;

        public Abastecimento(double litros, double precoPorLitro) {
            this.litros = litros;
            this.precoPorLitro = precoPorLitro;
        }

        public double getLitros() {
            return litros;
        }

        public double getPrecoPorLitro() {
            return precoPorLitro;
        }
    }
}
