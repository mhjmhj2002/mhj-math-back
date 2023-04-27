package com.mhj.math.operacao;

import java.util.List;

import com.mhj.math.data.Inteiro;
import com.mhj.math.data.interfaces.Data;
import com.mhj.math.enums.FiltroOperacoes;

public class OperacaoBasica extends Operacao{
	
	protected List<Inteiro> numeros;

	public OperacaoBasica(List<Data> retorno, List<FiltroOperacoes> filtroOperacoes, List<Inteiro> numeros) {
		super(retorno, filtroOperacoes);
		this.numeros = numeros;
	}
	
}
