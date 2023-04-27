package com.mhj.math.dto;

import java.io.Serializable;
import java.util.List;

public class FracaoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<Integer> numeros;
	
	Integer numero1;
	
	Integer numero2;

	public List<Integer> getNumeros() {
		return numeros;
	}

	public void setNumeros(List<Integer> numeros) {
		this.numeros = numeros;
	}

	public Integer getNumero1() {
		return numero1;
	}

	public void setNumero1(Integer numero1) {
		this.numero1 = numero1;
	}

	public Integer getNumero2() {
		return numero2;
	}

	public void setNumero2(Integer numero2) {
		this.numero2 = numero2;
	}

}
