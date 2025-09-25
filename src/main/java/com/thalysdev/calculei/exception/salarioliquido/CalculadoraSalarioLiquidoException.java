package com.thalysdev.calculei.exception.salarioliquido;

public class CalculadoraSalarioLiquidoException extends RuntimeException {

	private static final long serialVersionUID = 985098201651404172L;

	public CalculadoraSalarioLiquidoException(String mensagem) {

		super(mensagem);

	}

	public CalculadoraSalarioLiquidoException(String mensagem, Exception exception) {

		super(mensagem, exception);

	}

}
