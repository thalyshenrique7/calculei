package com.thalysdev.calculei.model.salarioliquido;

import java.math.BigDecimal;

import com.thalysdev.calculei.utils.BigDecimalUtils;

public class CalculadoraSalarioLiquidoRequest {

	private static final BigDecimal BASE_CALCULO_DEPENDENTE_PADRAO_2025 = new BigDecimal("189.59");

	private BigDecimal salarioBruto;
	private BigDecimal descontos;
	private BigDecimal beneficios;
	private BigDecimal horasExtras;

	private boolean deveCalcularDependentes;
	private Integer quantidadeDependentes;

	public BigDecimal getSalarioBruto() {

		return salarioBruto;

	}

	public void setSalarioBruto(BigDecimal salarioBruto) {

		this.salarioBruto = salarioBruto;

	}

	public BigDecimal getDescontos() {

		return descontos;

	}

	public void setDescontos(BigDecimal descontos) {

		this.descontos = descontos;

	}

	public BigDecimal getBeneficios() {

		return beneficios;

	}

	public void setBeneficios(BigDecimal beneficios) {

		this.beneficios = beneficios;

	}

	public BigDecimal getHorasExtras() {

		return horasExtras;

	}

	public void setHorasExtras(BigDecimal horasExtras) {

		this.horasExtras = horasExtras;

	}

	public boolean isDeveCalcularDependentes() {

		return deveCalcularDependentes;

	}

	public void setDeveCalcularDependentes(boolean deveCalcularDependentes) {

		this.deveCalcularDependentes = deveCalcularDependentes;

	}

	public Integer getQuantidadeDependentes() {

		if (quantidadeDependentes == null)
			return 0;

		return quantidadeDependentes;

	}

	public void setQuantidadeDependentes(Integer quantidadeDependentes) {

		this.quantidadeDependentes = quantidadeDependentes;

	}

	public static BigDecimal getBaseCalculoDependentePadrao2025() {

		return BASE_CALCULO_DEPENDENTE_PADRAO_2025;

	}

	public BigDecimal getValorDependentesAReceber() {

		BigDecimal valorDependentesAReceber = deveCalcularDependentes
				? BigDecimalUtils.multiplicar(BASE_CALCULO_DEPENDENTE_PADRAO_2025,
						BigDecimal.valueOf(getQuantidadeDependentes()))
				: BigDecimal.ZERO;

		return valorDependentesAReceber;

	}

}
