package com.thalysdev.calculei.service.salarioliquido;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thalysdev.calculei.model.salarioliquido.CalculadoraSalarioLiquidoRequest;
import com.thalysdev.calculei.model.salarioliquido.CalculadoraSalarioLiquidoResponse;
import com.thalysdev.calculei.utils.BigDecimalUtils;
import com.thalysdev.calculei.validation.salarioliquido.CalculadoraSalarioLiquidoValidador;

@Component
public class CalculadoraSalarioLiquido {

	private final static BigDecimal HORAS_MENSAIS_TRABALHADAS_PADRAO = new BigDecimal("220.00");
	private final static BigDecimal HORAS_EXTRAS_MULTIPLICADOR = new BigDecimal("1.5");

	@Autowired
	private CalculadoraInss calculadoraInss;

	@Autowired
	private CalculadoraIrrf calculadoraIrrf;

	public CalculadoraSalarioLiquidoResponse calcularSalarioLiquido(CalculadoraSalarioLiquidoRequest request) {

		CalculadoraSalarioLiquidoValidador.validarValoresCalculadoraSalarioLiquido(request);

		CalculadoraSalarioLiquidoResponse response = new CalculadoraSalarioLiquidoResponse();
		response.setBeneficios(request.getBeneficios());
		response.setDescontos(request.getDescontos());

		BigDecimal baseCalculoSalario = request.getSalarioBruto();

		BigDecimal horasExtrasAReceber = BigDecimal.ZERO;

		if (BigDecimalUtils.maiorQueZero(request.getHorasExtras())) {

			horasExtrasAReceber = calcularHorasExtras(request.getHorasExtras(), baseCalculoSalario);
			response.setHorasExtras(horasExtrasAReceber);

			baseCalculoSalario = baseCalculoSalario.add(horasExtrasAReceber);

		}

		BigDecimal rendimentos = request.getSalarioBruto().add(request.getBeneficios()).add(horasExtrasAReceber);
		response.setRendimentos(rendimentos);

		BigDecimal inssADescontar = this.calculadoraInss.calcularInss(baseCalculoSalario);
		response.setInss(inssADescontar);

		baseCalculoSalario = atualizarBaseCalculoSalario(baseCalculoSalario, inssADescontar, request);

		BigDecimal irrfADescontar = this.calculadoraIrrf.calcularIrrf(baseCalculoSalario);
		response.setIrrf(irrfADescontar);

		BigDecimal totalDescontos = inssADescontar.add(irrfADescontar).add(request.getDescontos());
		response.setDescontos(totalDescontos);

		BigDecimal salarioLiquido = this.getSalarioLiquidoAReceber(request, response);
		response.setSalarioLiquido(salarioLiquido);

		return response;
	}

	private BigDecimal calcularHorasExtras(BigDecimal horasExtras, BigDecimal baseCalculoSalario) {

		BigDecimal horasExtrasAReceber = BigDecimalUtils.ajustarEscala(BigDecimalUtils.dividir(baseCalculoSalario, HORAS_MENSAIS_TRABALHADAS_PADRAO),
				2);

		return BigDecimalUtils.multiplicar(horasExtrasAReceber, horasExtras).multiply(HORAS_EXTRAS_MULTIPLICADOR);
	}

	private BigDecimal atualizarBaseCalculoSalario(BigDecimal baseCalculoSalario, BigDecimal inssADescontar, CalculadoraSalarioLiquidoRequest request) {

		return baseCalculoSalario.subtract(inssADescontar).subtract(request.getValorDependentesAReceber()).subtract(request.getDescontos());
	}

	private BigDecimal getSalarioLiquidoAReceber(CalculadoraSalarioLiquidoRequest request, CalculadoraSalarioLiquidoResponse response) {

		return request.getSalarioBruto()
				.add(request.getBeneficios())
				.add(response.getHorasExtras())
				.subtract(response.getInss())
				.subtract(response.getIrrf())
				.subtract(request.getDescontos());
	}
}
