package com.mhj.math.type;

import java.math.BigDecimal;

import com.mhj.math.type.interfaces.Valor;

public class Decimal implements Valor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8586997340474974487L;
	BigDecimal valor;

	public Decimal(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	@Override
	public String getHtml() {
		return this.valor.toString();
	}

	public static BigDecimal valueOf(String valor) {
		return new BigDecimal(valor);
	}

	public int compareTo(Decimal valor) {
		return this.valor.compareTo(valor.getValor());
	}

	public Descricao toDescricao() {
		return new Descricao(this.valor.toString());
	}

	@Override
	public String getTexto() {
		return this.valor.toString();
	}

	public BigDecimal getValorSemSinal() {
		return BigDecimal.valueOf(Math.abs(valor.doubleValue()));
	}

}
