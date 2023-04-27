package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Expressao;

public enum SinalDelta implements Expressao {
	ZERO(0),
	POSITIVO(1),
	NEGATIVO(2);
	
	Integer valor;

	private SinalDelta(Integer valor) {
		this.valor = valor;
	}

	@Override
	public String getHtml() {
		return this.valor.toString();
	}

	@Override
	public String getTexto() {
		return null;
	}

}
