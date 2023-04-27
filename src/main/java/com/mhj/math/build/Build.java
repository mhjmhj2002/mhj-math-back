package com.mhj.math.build;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.util.MathProperties;
import com.mhj.math.util.MathjaxUtil;
import com.mhj.math.util.OperacaoUtil;

public abstract class Build {

	@Autowired
	MessageSource messageSource;
	
	Operacao operacao;

	Locale locale;

	public Build() {
	}

	public Operacao resolver() throws BusinessException, RegraException{
			validarOperacao();
			validarParametros();
			titulo();
			regras();
			resolucao();
		return this.operacao;
	}
	
	protected abstract void validarParametros() throws BusinessException, RegraException;
	
	protected abstract void titulo() throws BusinessException;
	
	protected abstract void regras() throws BusinessException, RegraException;
	
	protected abstract void resolucao() throws BusinessException, RegraException;

	private void validarOperacao() throws BusinessException {
		if (operacao == null) {
			throw new BusinessException(MathProperties.getPropertyString("Build.validarOperacao.1"));
		}
		if (operacao.getRetorno() == null) {
			throw new BusinessException(MathProperties.getPropertyString("Build.validarOperacao.2"));
		}
		if (operacao.getFiltroOperacoes() == null) {
			throw new BusinessException(MathProperties.getPropertyString("Build.validarOperacao.3"));
		}
	}
	
	public void abreMath(){
		operacao.getRetorno().addAll(MathjaxUtil.abreMath());
	}
	
	public void fechaMath(){
		operacao.getRetorno().add(MathjaxTag.MATH_CLOSE);
	}
	
	public void fechaTable(){
		operacao.getRetorno().add(MathjaxTag.MATH_CLOSE);
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	protected void montaSinal(Inteiro numero){
		operacao.getRetorno().add(MathjaxTag.MO_OPEN);
		operacao.getRetorno().add(OperacaoUtil.getSinalNumero(numero));
		operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
	}
}
