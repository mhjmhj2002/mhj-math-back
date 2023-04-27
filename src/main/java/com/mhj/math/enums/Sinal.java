package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Expressao;

public enum Sinal implements Expressao {
	
	POSITIVO(1,"+","+"),
	NEGATIVO(2,"-","-");
	
	private int codigo;
	
	private String html;
	
	private String texto;

	private Sinal(int codigo, String html, String texto) {
		this.codigo = codigo;
		this.html = html;
		this.texto = texto;
	}

	public int getCodigo() {
		return codigo;
	}

	@Override
	public String getHtml() {
		return html;
	}

	public String getDescricao() {
		return texto;
	}
	


	public static Sinal getByCodigo(final int codigo) {
		for (Sinal e : values()) {
			if (e.getCodigo() == codigo) {
				return e;
			}
		}
		return null;
	}

	public static Sinal getByHtml(final String html) {
		for (Sinal e : values()) {
			if (e.getHtml().equals(html)) {
				return e;
			}
		}
		return null;
	}

	public static Sinal getByTexto(final String texto) {
		for (Sinal e : values()) {
			if (e.getDescricao().equals(texto)) {
				return e;
			}
		}
		return null;
	}

	@Override
	public String getTexto() {
		return texto;
	}

}
