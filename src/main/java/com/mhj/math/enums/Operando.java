package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Expressao;

public enum Operando implements Expressao {
	
	SOMA(1,"+","+"),
	SUBTRACAO(2,"-","-"),
	MULTIPLICACAO(3,"*","*"),
	DIVISAO(4,"/","/");
	
	private int codigo;
	
	private String html;
	
	private String texto;

	private Operando(int codigo, String html, String texto) {
		this.codigo = codigo;
		this.html = html;
		this.texto = texto;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return texto;
	}
	


	public static Operando getByCodigo(final int codigo) {
		for (Operando e : values()) {
			if (e.getCodigo() == codigo) {
				return e;
			}
		}
		return null;
	}

	public static Operando getByHtml(final String html) {
		for (Operando e : values()) {
			if (e.getHtml().equals(html)) {
				return e;
			}
		}
		return null;
	}

	public static Operando getByTexto(final String texto) {
		for (Operando e : values()) {
			if (e.getDescricao().equals(texto)) {
				return e;
			}
		}
		return null;
	}

	@Override
	public String getHtml() {
		return this.html;
	}

	@Override
	public String getTexto() {
		return texto;
	}
	
	

}
