package com.mhj.math.build;

import java.util.List;

import com.mhj.math.data.Inteiro;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;

public abstract class OperacaoBasicaBuild extends Build {
	
	List<Inteiro> inteiros;
	
	public OperacaoBasicaBuild(){
	}

	@Override
	protected final void validarParametros() throws BusinessException{
		if (inteiros == null || inteiros.isEmpty() || inteiros.size() < 2) {
			throw new BusinessException("É obrigatório ao menos 2 numeros para realizar a operação");
		}
	}

	@Override
	protected abstract void titulo() throws BusinessException;

	@Override
	protected abstract void regras() throws BusinessException, RegraException;

	@Override
	protected abstract void resolucao() throws BusinessException, RegraException;

}
