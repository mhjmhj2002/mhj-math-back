package com.mhj.math.build;

import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;

public class MultiplicacaoBuild extends OperacaoBasicaBuild {
	
	private static final String operacaoNaoPermitida = "Operacao nao permitida";

	public MultiplicacaoBuild() {
		super();
	}

	@Override
	protected void titulo() throws BusinessException {
		throw new UnsupportedOperationException(operacaoNaoPermitida);
	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		throw new UnsupportedOperationException(operacaoNaoPermitida);
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		throw new UnsupportedOperationException(operacaoNaoPermitida);
	}

}
