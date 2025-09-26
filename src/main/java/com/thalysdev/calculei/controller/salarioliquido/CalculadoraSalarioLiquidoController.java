package com.thalysdev.calculei.controller.salarioliquido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalysdev.calculei.model.salarioliquido.CalculadoraSalarioLiquidoRequest;
import com.thalysdev.calculei.model.salarioliquido.CalculadoraSalarioLiquidoResponse;
import com.thalysdev.calculei.service.salarioliquido.CalculadoraSalarioLiquido;

@RestController
@RequestMapping(value = "/api/calculadora-salario-liquido", produces = MediaType.APPLICATION_JSON_VALUE)
public class CalculadoraSalarioLiquidoController {

	@Autowired
	private CalculadoraSalarioLiquido calculadoraSalarioLiquido;

	@PostMapping
	public ResponseEntity<CalculadoraSalarioLiquidoResponse> calculateNetSalary(@RequestBody CalculadoraSalarioLiquidoRequest request) {

		return new ResponseEntity<CalculadoraSalarioLiquidoResponse>(this.calculadoraSalarioLiquido.calcularSalarioLiquido(request), HttpStatus.OK);
	}
}
