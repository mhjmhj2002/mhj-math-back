package com.mhj.math.operacao;

import java.util.List;

import com.mhj.math.data.Inteiro;

public abstract class Comum {
	protected Inteiro divisor;
	protected Inteiro resultado;
	protected List<Inteiro> numeros;

	public Comum(Inteiro resultado, List<Inteiro> numeros) {
		super();
		this.resultado = resultado;
		this.numeros = numeros;
	}

	public Inteiro getDivisor() {
		return divisor;
	}

	public Inteiro getResultado() {
		return resultado;
	}

	public List<Inteiro> getNumeros() {
		return numeros;
	}

	public void setNumeros(List<Inteiro> numeros) {
		this.numeros = numeros;
	}

}
