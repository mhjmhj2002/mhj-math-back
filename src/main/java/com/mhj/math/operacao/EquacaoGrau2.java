package com.mhj.math.operacao;

import com.mhj.math.enums.Letra;
import com.mhj.math.enums.Sinal;
import com.mhj.math.enums.SinalDelta;
import com.mhj.math.metodo.Metodo;
import com.mhj.math.type.Descricao;
import com.mhj.math.type.Inteiro;

import lombok.Data;

@Data
public class EquacaoGrau2 {

	private Letra variavel;

	private Descricao a;

	private Descricao b;

	private Descricao c;

	private Sinal sinalA;

	private Sinal sinalB;

	private Sinal sinalC;

	private Descricao resultado;

	private Sinal sinalResultado;

	private Metodo metodo;

	private Inteiro delta;

	private Descricao x1;

	private Descricao x2;

	private Fracao x1Fracao;

	private Fracao x2Fracao;

	private SinalDelta sinalDelta;

	public Inteiro getAInteiro() {
		return Inteiro.valueOf(sinalA.getHtml() + a.getDescricao());
	}

	public Inteiro getBInteiro() {
		return Inteiro.valueOf(sinalB.getHtml() + b.getDescricao());
	}

	public Inteiro getCInteiro() {
		return Inteiro.valueOf(sinalC.getHtml() + c.getDescricao());
	}

	public Descricao getDelta() {
		return delta != null ? delta.toDescricao() : null;
	}

	public void setDelta(Inteiro delta) {
		this.delta = delta;
		Inteiro zero = new Inteiro(0);
		if (zero.compareTo(delta) < 0) {
			sinalDelta = SinalDelta.POSITIVO;
		} else if (zero.compareTo(delta) > 0) {
			sinalDelta = SinalDelta.NEGATIVO;
		} else {
			sinalDelta = SinalDelta.ZERO;
		}
	}

	public Inteiro getDeltaInteiro() {
		return delta;
	}

	public Inteiro getX1Inteiro() {
		return Inteiro.valueOf(x1.getDescricao());
	}

	public Inteiro getX2Inteiro() {
		return Inteiro.valueOf(x2.getDescricao());
	}

	public Fracao getX1Fracao() {
		return x1Fracao;
	}

	public void setX1Fracao(Fracao x1Fracao) {
		this.x1Fracao = x1Fracao;
	}

	public Fracao getX2Fracao() {
		return x2Fracao;
	}

	public void setX2Fracao(Fracao x2Fracao) {
		this.x2Fracao = x2Fracao;
	}

}
