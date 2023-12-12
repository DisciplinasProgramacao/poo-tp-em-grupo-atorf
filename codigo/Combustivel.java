package codigo;

import java.io.*;
import java.util.*;

/**
 * Enumeração para diferentes tipos de combustíveis.
 * Cada tipo de combustível tem um consumo médio e um preço por litro
 * associados.
 */
public enum Combustivel {

	ALCOOL(7, 3.29),
	DIESEL(10, 5.19),
	GASOLINA(4, 6.09);

	private final double consumoMedio;
	private final double precoLitro;

	Combustivel(double consumoMedio, double precoLitro) {
		this.consumoMedio = consumoMedio;
		this.precoLitro = precoLitro;
	}

	public double getConsumoMedio() {
		return consumoMedio;
	}

	public double getPrecoLitro() {
		return precoLitro;
	}
}
