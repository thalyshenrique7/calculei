package com.thalysdev.calculei.service.salarioliquido;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.thalysdev.calculei.utils.BigDecimalUtils;

@Component
public class CalculadoraInss {

	private static final BigDecimal FAIXA_SALARIAL_UM = new BigDecimal("1518.00");
	private static final BigDecimal FAIXA_SALARIAL_DOIS = new BigDecimal("2793.88");
	private static final BigDecimal FAIXA_SALARIAL_TRES = new BigDecimal("4190.83");
	private static final BigDecimal FAIXA_SALARIAL_QUATRO = new BigDecimal("8157.41");

	private static final BigDecimal ALIQUOTA_FAIXA_SALARIAL_UM = new BigDecimal("0.075");
	private static final BigDecimal ALIQUOTA_FAIXA_SALARIAL_DOIS = new BigDecimal("0.09");
	private static final BigDecimal ALIQUOTA_FAIXA_SALARIAL_TRES = new BigDecimal("0.12");
	private static final BigDecimal ALIQUOTA_FAIXA_SALARIAL_QUATRO = new BigDecimal("0.14");

	public BigDecimal calcularInss(BigDecimal baseCalculoSalario) {

		BigDecimal inssADescontar = BigDecimal.ZERO;

		BigDecimal inssADescontarPrimeiraFaixa = BigDecimalUtils
				.ajustarEscala(BigDecimalUtils.multiplicar(FAIXA_SALARIAL_UM, ALIQUOTA_FAIXA_SALARIAL_UM), BigDecimalUtils.PRECISAO_MONETARIA);

		inssADescontar = inssADescontar.add(inssADescontarPrimeiraFaixa);

		BigDecimal inssADescontarSegundaFaixa = BigDecimal.ZERO;

		if (BigDecimalUtils.maiorQue(baseCalculoSalario, FAIXA_SALARIAL_UM)) {

			BigDecimal valorADescontar = BigDecimalUtils.menorQue(baseCalculoSalario, FAIXA_SALARIAL_DOIS) ? baseCalculoSalario : FAIXA_SALARIAL_DOIS;

			inssADescontarSegundaFaixa = valorADescontar.subtract(FAIXA_SALARIAL_UM);

			inssADescontarSegundaFaixa = BigDecimalUtils.ajustarEscala(
					BigDecimalUtils.multiplicar(inssADescontarSegundaFaixa, ALIQUOTA_FAIXA_SALARIAL_DOIS), BigDecimalUtils.PRECISAO_MONETARIA);

			inssADescontar = inssADescontar.add(inssADescontarSegundaFaixa);
		}

		BigDecimal inssADescontarTerceiraFaixa = BigDecimal.ZERO;

		if (BigDecimalUtils.maiorQue(baseCalculoSalario, FAIXA_SALARIAL_DOIS)) {

			BigDecimal valorADescontar = BigDecimalUtils.menorQue(baseCalculoSalario, FAIXA_SALARIAL_TRES) ? baseCalculoSalario : FAIXA_SALARIAL_TRES;

			inssADescontarTerceiraFaixa = valorADescontar.subtract(FAIXA_SALARIAL_DOIS);

			inssADescontarTerceiraFaixa = BigDecimalUtils.ajustarEscala(
					BigDecimalUtils.multiplicar(inssADescontarTerceiraFaixa, ALIQUOTA_FAIXA_SALARIAL_TRES), BigDecimalUtils.PRECISAO_MONETARIA);

			inssADescontar = inssADescontar.add(inssADescontarTerceiraFaixa);
		}

		BigDecimal inssADescontarQuartaFaixa = BigDecimal.ZERO;

		if (BigDecimalUtils.maiorQue(baseCalculoSalario, FAIXA_SALARIAL_TRES)) {

			BigDecimal valorADescontar = BigDecimalUtils.menorQue(baseCalculoSalario, FAIXA_SALARIAL_QUATRO) ? baseCalculoSalario
					: FAIXA_SALARIAL_QUATRO;

			inssADescontarQuartaFaixa = valorADescontar.subtract(FAIXA_SALARIAL_TRES);

			inssADescontarQuartaFaixa = BigDecimalUtils.ajustarEscala(
					BigDecimalUtils.multiplicar(inssADescontarQuartaFaixa, ALIQUOTA_FAIXA_SALARIAL_QUATRO), BigDecimalUtils.PRECISAO_MONETARIA);

			inssADescontar = inssADescontar.add(inssADescontarQuartaFaixa);
		}

		return inssADescontar;
	}
}
