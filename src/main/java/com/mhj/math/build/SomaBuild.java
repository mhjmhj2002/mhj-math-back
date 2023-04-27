package com.mhj.math.build;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.MathjaxProperty;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.enums.Sinal;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.util.OperacaoUtil;

public class SomaBuild extends OperacaoBasicaBuild {
	
	Sinal sinal = Sinal.POSITIVO;

	public SomaBuild() {
		super();
	}

	@Override
	protected void titulo() throws BusinessException {
		operacao.getRetorno().add(new Descricao("Resolvendo Operação: "));
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(inteiros.get(0), false));
		for (int i = 1; i < inteiros.size(); i++) {
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(inteiros.get(i), true));
		}
		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		montaOperacao();

	}
	
	public void montaOperacao(){
		operacao.getRetorno().add(Simbolo.MENOR);
		operacao.getRetorno().add(new Descricao("MATH"));
		operacao.getRetorno().add(MathjaxProperty.XMLNS);
		operacao.getRetorno().add(MathjaxProperty.DISPLAY);
		operacao.getRetorno().add(Simbolo.MAIOR);
		
		for (Inteiro inteiro : inteiros) {
			operacao.getRetorno().add(MathjaxTag.MROW_OPEN);
			operacao.getRetorno().add(MathjaxTag.MN_OPEN);
			operacao.getRetorno().add(inteiro);
			operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
			operacao.getRetorno().add(MathjaxTag.MROW_CLOSE);
		}
	}

}
