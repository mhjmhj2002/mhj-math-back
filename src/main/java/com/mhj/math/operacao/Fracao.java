package com.mhj.math.operacao;

import java.io.Serializable;

import com.mhj.math.type.Inteiro;

public class Fracao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4058981238787876898L;
	private Inteiro numerador;
	private Inteiro denominador;
	private Inteiro potencia;

	public Fracao(Inteiro numerador, Inteiro denominador) {
		super();
		this.numerador = numerador;
		this.denominador = denominador;
	}

	public Fracao(Inteiro numerador, Inteiro denominador, Inteiro potencia) {
		super();
		this.numerador = numerador;
		this.denominador = denominador;
		this.potencia = potencia;
	}

	public Inteiro getNumerador() {
		return numerador;
	}

	public void setNumerador(Inteiro dividendo) {
		this.numerador = dividendo;
	}

	public Inteiro getDenominador() {
		return denominador;
	}

	public void setDenominador(Inteiro divisor) {
		this.denominador = divisor;
	}

	public Inteiro getPotencia() {
		return potencia;
	}

	public void setPotencia(Inteiro potencia) {
		this.potencia = potencia;
	}
}
