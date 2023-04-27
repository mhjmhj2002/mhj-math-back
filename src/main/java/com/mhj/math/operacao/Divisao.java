package com.mhj.math.operacao;

import com.mhj.math.data.Inteiro;

public class Divisao {
	
	private Inteiro dividendo;
	
	private Inteiro divisor;
	
	private Inteiro quociente;
	
	private Inteiro resto;

	public Divisao(Inteiro dividendo, Inteiro divisor) {
		super();
		this.dividendo = dividendo;
		this.divisor = divisor;
	}

	public Inteiro getDividendo() {
		return dividendo;
	}

	public Inteiro getDivisor() {
		return divisor;
	}

	public Inteiro getQuociente() {
		return quociente;
	}

	public void setQuociente(Inteiro quociente) {
		this.quociente = quociente;
	}

	public Inteiro getResto() {
		return resto;
	}

	public void setResto(Inteiro resto) {
		this.resto = resto;
	}
	
	public void resolve(){
		resto = new Inteiro(dividendo.getValor() % divisor.getValor());
		quociente = new Inteiro(dividendo.getValor() / divisor.getValor());
	}

}
