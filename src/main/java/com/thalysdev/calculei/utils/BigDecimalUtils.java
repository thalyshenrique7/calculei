package com.thalysdev.calculei.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class BigDecimalUtils {

	public static final Integer PRECISAO_MONETARIA = 2;

	public static final BigDecimal CEM = new BigDecimal("100.00");

	public static String paraString(BigDecimal valor, int casasDecimais, boolean usarSeparadorDeMilhar) {

		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		nf.setMinimumFractionDigits(casasDecimais);
		nf.setGroupingUsed(usarSeparadorDeMilhar);

		BigDecimal resultado = valor.setScale(casasDecimais, RoundingMode.HALF_DOWN);

		return nf.format(resultado);
	}

	public static BigDecimal ajustarEscala(BigDecimal valor, int escala) {

		return getZeroSeNulo(valor).setScale(escala, RoundingMode.HALF_UP);
	}

	public static boolean menorQue(BigDecimal valor, BigDecimal outro) {

		return comparar(valor, outro) == -1;
	}

	public static boolean menorOuIgualQue(BigDecimal valor, BigDecimal outro) {

		int resultado = comparar(valor, outro);

		return resultado == -1 || resultado == 0;
	}

	public static boolean maiorQueZero(BigDecimal valor) {

		return maiorQue(valor, BigDecimal.ZERO);
	}

	public static boolean menorQueZero(BigDecimal valor) {

		return menorQue(valor, BigDecimal.ZERO);
	}

	public static boolean maiorQue(BigDecimal valor, BigDecimal outro) {

		return comparar(valor, outro) == 1;
	}

	public static boolean maiorOuIgualQue(BigDecimal valor, BigDecimal outro) {

		int resultado = comparar(valor, outro);

		return resultado == 1 || resultado == 0;
	}

	public static boolean diferenteDeZero(BigDecimal valor) {

		return !ehZero(valor);
	}

	public static boolean ehZero(BigDecimal valor) {

		return ehIgual(valor, BigDecimal.ZERO);
	}

	public static BigDecimal getZeroSeNulo(BigDecimal valor) {

		return (valor == null) ? BigDecimal.ZERO : valor;
	}

	/**
	 * Usado para calcular percentuais. Fórmula: (valor1 * valor2) / 100;
	 *
	 * Exemplo 1: percentual(10, 1) = 0.1 Exemplo 2: percentual(1, 10) = 0.1
	 *
	 * @return valor percentual aplicado
	 */
	public static BigDecimal percentual(BigDecimal valor1, BigDecimal valor2) {

		return dividirPorCem(multiplicar(valor1, valor2));
	}

	public static BigDecimal multiplicar(BigDecimal valor, BigDecimal multiplicador) {

		return getZeroSeNulo(valor).multiply(getZeroSeNulo(multiplicador));
	}

	public static boolean naoEhIgual(BigDecimal valor1, BigDecimal valor2) {

		return !ehIgual(valor1, valor2);
	}

	public static BigDecimal dividir(BigDecimal dividendo, BigDecimal divisor) {

		return dividir(dividendo, divisor, 4);
	}

	public static BigDecimal dividir(BigDecimal dividendo, BigDecimal divisor, int escala) {

		return getZeroSeNulo(dividendo).divide(divisor, escala, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal dividirComEscala10(BigDecimal dividendo, BigDecimal divisor) {

		return dividendo.divide(divisor, 10, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal dividirPorCem(BigDecimal dividendo) {

		return dividir(dividendo, CEM);
	}

	public static BigDecimal dividirPorCem(BigDecimal dividendo, int escala) {

		return dividir(dividendo, CEM, escala);
	}

	public static boolean ehIgual(BigDecimal valor1, BigDecimal valor2) {

		return comparar(valor1, valor2) == 0;
	}

	public static BigDecimal deString(String valor) {

		return deString(valor, PRECISAO_MONETARIA);
	}

	public static BigDecimal deString(String valor, Integer casasDecimais) {

		if (valor == null || valor.isBlank())
			return null;

		if (valor.contains(","))
			valor = valor.replace(",", "");

		if (valor.contains("."))
			valor = valor.replace(".", ",");

		BigDecimal valorBigDecimal = null;

		try {

			NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
			nf.setMinimumFractionDigits(casasDecimais);

			valorBigDecimal = BigDecimal.valueOf(nf.parse(valor).doubleValue());

		} catch (ParseException e) {

			throw new RuntimeException("Erro ao converter valor: " + valor);

		}

		return valorBigDecimal;
	}

	private static int comparar(BigDecimal valor, BigDecimal outro) {

		if (valor == null)
			valor = BigDecimal.ZERO;

		if (outro == null)
			outro = BigDecimal.ZERO;

		return valor.compareTo(outro);
	}
}
