package com.mhj.math.build;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.Operando;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.util.OperacaoUtil;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class FracaoMultiplicacaoBuild extends FracaoBuild {
	
	@Autowired
	FracaoSimplificacaoBuild fracaoSimplificacaoBuild;
	
	public FracaoMultiplicacaoBuild(){
		super();
	}

	public FracaoMultiplicacaoBuild(List<Fracao> fracoes) {
		super(fracoes);
	}

	@Override
	protected void validarParametros() throws BusinessException {
//		throw new UnsupportedOperationException("Operacao invalida");
	}

	@Override
	protected void titulo() throws BusinessException {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoMultiplicacaoBuild.titulo.1", null, locale)));
		
		exibirFracoes(fracoes);
		
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);
		
	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		throw new UnsupportedOperationException("Operacao invalida");
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoMultiplicacaoBuild.resolucao.1", null, locale)));
		Inteiro multiplicacaoNumeradores = multiplicarNumeradores();

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoMultiplicacaoBuild.resolucao.2", null, locale)));
		Inteiro multiplicacaoDenominadores = multiplicarDenominadores();
		
		fracaoSimplificacaoBuild.setFracao(new Fracao(multiplicacaoNumeradores, multiplicacaoDenominadores));
		fracaoSimplificacaoBuild.setLocale(locale);
		fracaoSimplificacaoBuild.setOperacao(operacao);
		
		fracaoSimplificacaoBuild.resolver();
	}

	private Inteiro multiplicarDenominadores() {
		Inteiro retorno = new Inteiro(1);
		for (Fracao fracao : fracoes) {
			retorno = OperacaoUtil.multiplicacao(retorno, fracao.getDenominador());
			operacao.getRetorno().add(fracao.getDenominador());
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
		}
		operacao.getRetorno().remove(operacao.getRetorno().size()-1);
		operacao.getRetorno().add(LineSeparator.BREAK);
		return retorno;
	}

	private Inteiro multiplicarNumeradores() {
		Inteiro retorno = new Inteiro(1);
		for (Fracao fracao : fracoes) {
			retorno = OperacaoUtil.multiplicacao(retorno, fracao.getNumerador());
			operacao.getRetorno().add(fracao.getNumerador());
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
		}
		operacao.getRetorno().remove(operacao.getRetorno().size()-1);
		operacao.getRetorno().add(LineSeparator.BREAK);
		return retorno;
	}	 

}
