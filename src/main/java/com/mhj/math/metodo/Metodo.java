package com.mhj.math.metodo;

import java.io.Serializable;
import java.util.List;

import com.mhj.math.type.interfaces.Data;

public abstract class Metodo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7267871966277385293L;

	public abstract String getNome();

	public abstract List<Data> getFormula();

}
