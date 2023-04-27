package com.mhj.math.enums;

public enum FiltroOperacoes {
	
	SOMA(1),
	SUBTRACAO(2),
	MULTIPLICACAO(2),
	DIVISAO(2),
	SIMPLIFICACAO(2),
	FRACAO(2);
	
	private Integer codigo;

	private FiltroOperacoes(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

}
