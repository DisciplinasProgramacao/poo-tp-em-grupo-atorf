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

	/**
	 * Construtor para a enumeração Combustivel.
	 * 
	 * @param consumoMedio O consumo médio do combustível.
	 * @param precoLitro   O preço por litro do combustível.
	 */
	Combustivel(double consumoMedio, double precoLitro) {
		this.consumoMedio = consumoMedio;
		this.precoLitro = precoLitro;
	}

	/**
	 * Retorna o consumo médio do combustível.
	 * 
	 * @return O consumo médio do combustível.
	 */
	public double getConsumoMedio() {
		return consumoMedio;
	}

	/**
	 * Retorna o preço por litro do combustível.
	 * 
	 * @return O preço por litro do combustível.
	 */
	public double getPrecoLitro() {
		return precoLitro;
	}
}
