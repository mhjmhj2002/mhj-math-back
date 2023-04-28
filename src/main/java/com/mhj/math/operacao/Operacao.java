package com.mhj.math.operacao;

import java.util.List;

import com.mhj.math.enums.FiltroOperacoes;
import com.mhj.math.type.interfaces.Data;

public class Operacao {

	List<Data> retorno;
	
	List<FiltroOperacoes> filtroOperacoes;
	
	public Operacao(List<Data> retorno, List<FiltroOperacoes> filtroOperacoes) {
		super();
		this.retorno = retorno;
		this.filtroOperacoes = filtroOperacoes;
	}

	public List<Data> getRetorno() {
		return retorno;
	}

	public void setRetorno(List<Data> retorno) {
		this.retorno = retorno;
	}

	public List<FiltroOperacoes> getFiltroOperacoes() {
		return filtroOperacoes;
	}

	public void setFiltroOperacoes(List<FiltroOperacoes> filtroOperacoes) {
		this.filtroOperacoes = filtroOperacoes;
	}

}
