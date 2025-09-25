package com.thalysdev.calculei.model.salarioliquido;

import java.math.BigDecimal;

public class CalculadoraSalarioLiquidoResponse {

	private BigDecimal rendimentos;
	private BigDecimal salarioLiquido;
	private BigDecimal beneficios;
	private BigDecimal horasExtras;
	private BigDecimal descontos;
	private BigDecimal inss;
	private BigDecimal irrf;

	public BigDecimal getRendimentos() {

		return rendimentos;

	}

	public void setRendimentos(BigDecimal rendimentos) {

		this.rendimentos = rendimentos;

	}

	public BigDecimal getSalarioLiquido() {

		return salarioLiquido;

	}

	public void setSalarioLiquido(BigDecimal salarioLiquido) {

		this.salarioLiquido = salarioLiquido;

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

	public BigDecimal getDescontos() {

		return descontos;

	}

	public void setDescontos(BigDecimal descontos) {

		this.descontos = descontos;

	}

	public BigDecimal getInss() {

		return inss;

	}

	public void setInss(BigDecimal inss) {

		this.inss = inss;

	}

	public BigDecimal getIrrf() {

		return irrf;

	}

	public void setIrrf(BigDecimal irrf) {

		this.irrf = irrf;

	}

}
