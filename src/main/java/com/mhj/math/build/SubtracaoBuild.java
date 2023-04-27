package com.mhj.math.build;

import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;

public class SubtracaoBuild extends OperacaoBasicaBuild {

	public SubtracaoBuild() {
		super();
	}

	@Override
	protected void titulo() throws BusinessException {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

}
