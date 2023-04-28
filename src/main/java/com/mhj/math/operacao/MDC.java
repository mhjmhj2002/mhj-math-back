package com.mhj.math.operacao;

import java.util.Collections;
import java.util.List;

import com.mhj.math.type.Inteiro;
import com.mhj.math.util.OperacaoUtil;

public class MDC extends Comum{
	
	private boolean divisivelPorTodos;
	private boolean resolvido;
	
	public MDC(List<Inteiro> numeros, Inteiro resultado){
		super(resultado, numeros);
		this.numeros = numeros;
		this.resultado = resultado;
		this.divisivelPorTodos = true;
		this.resolvido = true;
		adicionarDivisor();
		calcularMdc();
	}

	private void calcularMdc() {
		if (divisivelPorTodos) {
			resultado = OperacaoUtil.multiplicacao(resultado, divisor);
		}
	}

	private void adicionarDivisor() {
		Collections.sort(numeros);
		
		for (Inteiro numero : numeros) {
			if (numero.getValor() == 1) {
				divisor = new Inteiro(1);
				continue;
			}
			if (OperacaoUtil.ehDivisor(numero, new Inteiro(2))) {
				divisor = new Inteiro(2);
				break;
			}
			if (OperacaoUtil.ehDivisor(numero, new Inteiro(3))) {
				divisor = new Inteiro(3); 
				break;
			}
			if (OperacaoUtil.ehDivisor(numero, new Inteiro(5))) {
				divisor = new Inteiro(5);
				break;
			}
			if (OperacaoUtil.ehDivisor(numero, new Inteiro(7))) {
				divisor = new Inteiro(7);
				break;
			}
			divisor = numero;
			break;
		}
		
		for (Inteiro numero : numeros) {
			if (!OperacaoUtil.ehDivisor(numero, divisor)) {
				divisivelPorTodos = false;
				break;
			}
		}
		
		for (Inteiro numero : numeros) {
			if (numero.getValor() != 1) {
				resolvido = false;
				break;
			}
		}
		
		if (resolvido) {
			divisivelPorTodos = false;
		}
	}

	public boolean isDivisivelPorTodos() {
		return divisivelPorTodos;
	}

}
