package com.mhj.math.operacao;

import java.util.List;

import com.mhj.math.data.Inteiro;
import com.mhj.math.data.interfaces.Data;
import com.mhj.math.enums.FiltroOperacoes;

public class Soma extends OperacaoBasica{

	public Soma(List<Data> retorno, List<FiltroOperacoes> filtroOperacoes, List<Inteiro> numeros) {
		super(retorno, filtroOperacoes, numeros);
	}

}
