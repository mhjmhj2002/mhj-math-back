package com.mhj.math.operacao;

import com.mhj.math.type.Inteiro;

public class Potenciacao {
	
	private Inteiro base;
	
	private Inteiro expoente;
	
	private Inteiro potencia;

	public Potenciacao(Inteiro base, Inteiro expoente) {
		super();
		this.base = base;
		this.expoente = expoente;
	}

	public Inteiro getBase() {
		return base;
	}

	public void setBase(Inteiro base) {
		this.base = base;
	}

	public Inteiro getExpoente() {
		return expoente;
	}

	public void setExpoente(Inteiro expoente) {
		this.expoente = expoente;
	}

	public Inteiro getPotencia() {
		return potencia;
	}

	public void setPotencia(Inteiro potencia) {
		this.potencia = potencia;
	}

}
