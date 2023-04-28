package com.mhj.math.operacao;

import java.util.List;

import com.mhj.math.enums.FiltroOperacoes;
import com.mhj.math.type.Inteiro;
import com.mhj.math.type.interfaces.Data;

public class Soma extends OperacaoBasica{

	public Soma(List<Data> retorno, List<FiltroOperacoes> filtroOperacoes, List<Inteiro> numeros) {
		super(retorno, filtroOperacoes, numeros);
	}

}
