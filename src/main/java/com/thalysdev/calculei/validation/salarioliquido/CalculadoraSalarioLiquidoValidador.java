package com.thalysdev.calculei.validation.salarioliquido;

import java.math.BigDecimal;

import com.thalysdev.calculei.exception.salarioliquido.CalculadoraSalarioLiquidoException;
import com.thalysdev.calculei.model.salarioliquido.CalculadoraSalarioLiquidoRequest;
import com.thalysdev.calculei.utils.BigDecimalUtils;

public class CalculadoraSalarioLiquidoValidador {

	public static void validarValoresCalculadoraSalarioLiquido(CalculadoraSalarioLiquidoRequest request) {

		BigDecimal baseCalculoSalario = request.getSalarioBruto();
		if (BigDecimalUtils.menorOuIgualQue(baseCalculoSalario, BigDecimal.ZERO))
			throw new CalculadoraSalarioLiquidoException("Salário bruto deve ser maior que 0.");

	}

}
