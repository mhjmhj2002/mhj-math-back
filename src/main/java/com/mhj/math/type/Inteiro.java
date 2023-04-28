package com.mhj.math.type;

import com.mhj.math.type.interfaces.Valor;

public class Inteiro implements Valor, Comparable<Inteiro>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5299122804527888256L;
	Integer valor;

	public Inteiro(Integer valor) {
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	@Override
	public String getHtml() {
		return this.valor.toString();
	}

	public static Inteiro valueOf(String valor) {
		return new Inteiro(Integer.valueOf(valor));
	}

	public Descricao toDescricao() {
		return new Descricao(this.valor.toString());
	}

	@Override
	public String getTexto() {
		return this.valor.toString();
	}

	public Inteiro getValorSemSinal() {
		return new Inteiro(Math.abs(valor));
	}

	@Override
	public int compareTo(Inteiro valor) {
		return this.valor.compareTo(valor.getValor());
	}

}
