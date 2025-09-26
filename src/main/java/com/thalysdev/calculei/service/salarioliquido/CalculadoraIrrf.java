package com.thalysdev.calculei.service.salarioliquido;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.thalysdev.calculei.utils.BigDecimalUtils;

@Component
public class CalculadoraIrrf {

	private static final BigDecimal PRIMEIRA_FAIXA_IRRF = new BigDecimal("2259.00");
	private static final BigDecimal SEGUNDA_FAIXA_IRRF = new BigDecimal("2826.65");
	private static final BigDecimal TERCEIRA_FAIXA_IRRF = new BigDecimal("3751.05");
	private static final BigDecimal QUARTA_FAIXA_IRRF = new BigDecimal("4664.68");

	private static final BigDecimal ALIQUOTA_SEGUNDA_FAIXA_IRRF = new BigDecimal("0.075");
	private static final BigDecimal ALIQUOTA_TERCEIRA_FAIXA_IRRF = new BigDecimal("0.15");
	private static final BigDecimal ALIQUOTA_QUARTA_FAIXA_IRRF = new BigDecimal("0.225");
	private static final BigDecimal ALIQUOTA_QUINTA_FAIXA_IRRF = new BigDecimal("0.275");

	private static final BigDecimal DEDUCAO_SEGUNDA_FAIXA_IRRF = new BigDecimal("169.44");
	private static final BigDecimal DEDUCAO_TERCEIRA_FAIXA_IRRF = new BigDecimal("381.44");
	private static final BigDecimal DEDUCAO_QUARTA_FAIXA_IRRF = new BigDecimal("662.77");
	private static final BigDecimal DEDUCAO_QUINTA_FAIXA_IRRF = new BigDecimal("896.00");

	public BigDecimal calcularIrrf(BigDecimal salarioBase) {

		BigDecimal valorTotalIrrf = BigDecimal.ZERO;

		if (BigDecimalUtils.maiorQue(salarioBase, PRIMEIRA_FAIXA_IRRF)) {

			valorTotalIrrf = BigDecimalUtils.ajustarEscala(
					BigDecimalUtils.multiplicar(salarioBase, ALIQUOTA_SEGUNDA_FAIXA_IRRF).subtract(DEDUCAO_SEGUNDA_FAIXA_IRRF),
					BigDecimalUtils.PRECISAO_MONETARIA);
		}

		if (BigDecimalUtils.maiorQue(salarioBase, SEGUNDA_FAIXA_IRRF)) {

			valorTotalIrrf = BigDecimal.ZERO;

			valorTotalIrrf = BigDecimalUtils.ajustarEscala(
					BigDecimalUtils.multiplicar(salarioBase, ALIQUOTA_TERCEIRA_FAIXA_IRRF).subtract(DEDUCAO_TERCEIRA_FAIXA_IRRF),
					BigDecimalUtils.PRECISAO_MONETARIA);
		}

		if (BigDecimalUtils.maiorQue(salarioBase, TERCEIRA_FAIXA_IRRF)) {

			valorTotalIrrf = BigDecimal.ZERO;

			valorTotalIrrf = BigDecimalUtils.ajustarEscala(
					BigDecimalUtils.multiplicar(salarioBase, ALIQUOTA_QUARTA_FAIXA_IRRF).subtract(DEDUCAO_QUARTA_FAIXA_IRRF),
					BigDecimalUtils.PRECISAO_MONETARIA);
		}

		if (BigDecimalUtils.maiorQue(salarioBase, QUARTA_FAIXA_IRRF)) {

			valorTotalIrrf = BigDecimal.ZERO;

			valorTotalIrrf = BigDecimalUtils.ajustarEscala(
					BigDecimalUtils.multiplicar(salarioBase, ALIQUOTA_QUINTA_FAIXA_IRRF).subtract(DEDUCAO_QUINTA_FAIXA_IRRF),
					BigDecimalUtils.PRECISAO_MONETARIA);
		}

		return valorTotalIrrf;
	}

}
