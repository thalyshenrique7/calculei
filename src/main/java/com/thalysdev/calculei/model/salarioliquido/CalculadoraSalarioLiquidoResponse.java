package com.thalysdev.calculei.model.salarioliquido;

import java.math.BigDecimal;

import com.thalysdev.calculei.utils.BigDecimalUtils;

public class CalculadoraSalarioLiquidoResponse {

	private BigDecimal rendimentos;
	private BigDecimal salarioLiquido;
	private BigDecimal beneficios;
	private BigDecimal horasExtras;
	private BigDecimal descontos;
	private BigDecimal inss;
	private BigDecimal irrf;

	public BigDecimal getRendimentos() {

		return BigDecimalUtils.getZeroSeNulo(rendimentos);
	}

	public void setRendimentos(BigDecimal rendimentos) {

		this.rendimentos = rendimentos;
	}

	public BigDecimal getSalarioLiquido() {

		return BigDecimalUtils.getZeroSeNulo(salarioLiquido);
	}

	public void setSalarioLiquido(BigDecimal salarioLiquido) {

		this.salarioLiquido = salarioLiquido;
	}

	public BigDecimal getBeneficios() {

		return BigDecimalUtils.getZeroSeNulo(beneficios);
	}

	public void setBeneficios(BigDecimal beneficios) {

		this.beneficios = beneficios;
	}

	public BigDecimal getHorasExtras() {

		return BigDecimalUtils.getZeroSeNulo(horasExtras);
	}

	public void setHorasExtras(BigDecimal horasExtras) {

		this.horasExtras = horasExtras;
	}

	public BigDecimal getDescontos() {

		return BigDecimalUtils.getZeroSeNulo(descontos);
	}

	public void setDescontos(BigDecimal descontos) {

		this.descontos = descontos;
	}

	public BigDecimal getInss() {

		return BigDecimalUtils.getZeroSeNulo(inss);
	}

	public void setInss(BigDecimal inss) {

		this.inss = inss;
	}

	public BigDecimal getIrrf() {

		return BigDecimalUtils.getZeroSeNulo(irrf);
	}

	public void setIrrf(BigDecimal irrf) {

		this.irrf = irrf;
	}

}
