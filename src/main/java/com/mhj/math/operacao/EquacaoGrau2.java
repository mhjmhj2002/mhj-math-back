package com.mhj.math.operacao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.Letra;
import com.mhj.math.enums.Sinal;
import com.mhj.math.enums.SinalDelta;
import com.mhj.math.metodo.Metodo;

public class EquacaoGrau2 {

	@JsonIgnore
	private Letra variavel;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.a}")
	private Descricao a;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.b}")
	private Descricao b;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.c}")
	private Descricao c;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.sinalA}")
	private Sinal sinalA;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.sinalB}")
	private Sinal sinalB;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.sinalC}")
	private Sinal sinalC;

	@JsonIgnore
	private Descricao resultado;

	@JsonIgnore
	private Sinal sinalResultado;

	@JsonIgnore
	private Metodo metodo;

	@JsonIgnore
	private Inteiro delta;

	@JsonIgnore
	private Descricao x1;

	@JsonIgnore
	private Descricao x2;

	@JsonIgnore
	private Fracao x1Fracao;

	@JsonIgnore
	private Fracao x2Fracao;

	@JsonIgnore
	private SinalDelta sinalDelta;

	public EquacaoGrau2() {
		super();
	}

	public EquacaoGrau2(Letra variavel, Descricao a, Descricao b, Descricao c, Sinal sinalA, Sinal sinalB, Sinal sinalC,
			Descricao resultado, Sinal sinalResultado) {
		super();
		this.variavel = variavel;
		this.a = a;
		this.b = b;
		this.c = c;
		this.sinalA = sinalA;
		this.sinalB = sinalB;
		this.sinalC = sinalC;
		this.resultado = resultado;
		this.sinalResultado = sinalResultado;
	}

	public Letra getVariavel() {
		return variavel;
	}

	public void setVariavel(Letra variavel) {
		this.variavel = variavel;
	}

	public Descricao getA() {
		return a;
	}

	public void setA(Descricao a) {
		this.a = a;
	}

	public Descricao getB() {
		return b;
	}

	public void setB(Descricao b) {
		this.b = b;
	}

	public Descricao getC() {
		return c;
	}

	public void setC(Descricao c) {
		this.c = c;
	}

	public Sinal getSinalA() {
		return sinalA;
	}

	public void setSinalA(Sinal sinalA) {
		this.sinalA = sinalA;
	}

	public Sinal getSinalB() {
		return sinalB;
	}

	public void setSinalB(Sinal sinalB) {
		this.sinalB = sinalB;
	}

	public Sinal getSinalC() {
		return sinalC;
	}

	public void setSinalC(Sinal sinalC) {
		this.sinalC = sinalC;
	}

	public Descricao getResultado() {
		return resultado;
	}

	public void setResultado(Descricao resultado) {
		this.resultado = resultado;
	}

	public Sinal getSinalResultado() {
		return sinalResultado;
	}

	public void setSinalResultado(Sinal sinalResultado) {
		this.sinalResultado = sinalResultado;
	}

	public Inteiro getAInteiro() {
		return Inteiro.valueOf(sinalA.getHtml() + a.getDescricao());
	}

	public Inteiro getBInteiro() {
		return Inteiro.valueOf(sinalB.getHtml() + b.getDescricao());
	}

	public Inteiro getCInteiro() {
		return Inteiro.valueOf(sinalC.getHtml() + c.getDescricao());
	}

	public Metodo getMetodo() {
		return metodo;
	}

	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
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

	public Descricao getX1() {
		return x1;
	}

	public void setX1(Descricao x1) {
		this.x1 = x1;
	}

	public Descricao getX2() {
		return x2;
	}

	public void setX2(Descricao x2) {
		this.x2 = x2;
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

	public SinalDelta getSinalDelta() {
		return sinalDelta;
	}

}
