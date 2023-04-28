package com.mhj.math.build;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.Letra;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.enums.Operando;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Divisao;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.MDC;
import com.mhj.math.util.OperacaoUtil;

@Component
public class FracaoSimplificacaoBuild extends FracaoBuild {

	@Autowired
	MDCBuild mdcBuild;

	private Fracao fracao;
	private Divisao divisao;
	private boolean validarNumeradorMaiorComResto = false;

	public FracaoSimplificacaoBuild() {
		super();
	}

	public FracaoSimplificacaoBuild(Fracao fracao) {
		super(null);
		this.fracao = fracao;
		divisao = null;
	}

	@Override
	protected void validarParametros() throws BusinessException, RegraException {
		validarFracaoIrredutivel();
	}

	@Override
	protected void titulo() {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.titulo.1", null, locale)));
		abreMath();
		montaFracao(fracao, false);
		fechaMath();
		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	@Override
	protected void regras() throws BusinessException, RegraException {

		verificarNumeradorIgual();
		
		verificarNumeradorMaiorSemResto();
		
		validarFracaoBase10();

		if (validarNumeradorMaiorComResto) {
			verificarNumeradorMaiorComResto();
		}
		
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.1", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao(
				messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.2", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.3", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);

		carregarMdc();
		
		try {
			mdcBuild.resolver();
		} catch (RegraException e) {
		}

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.4", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(fracao.getNumerador());
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(mdcBuild.getResultado());
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Letra.e);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(fracao.getDenominador());
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(mdcBuild.getResultado());
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.resolucao.5", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);

		fracao = new Fracao(OperacaoUtil.divisao(fracao.getNumerador(), mdcBuild.getResultado()).getQuociente(),
				OperacaoUtil.divisao(fracao.getDenominador(), mdcBuild.getResultado()).getQuociente());

		abreMath();
		if (divisao != null) {
			operacao.getRetorno().add(MathjaxTag.MO_OPEN);
			operacao.getRetorno().add(divisao.getQuociente());
			operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
		}
		montaFracao(fracao.getNumerador(), fracao.getDenominador());
		fechaMath();

		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	private void verificarNumeradorMaiorComResto() throws RegraException {
		if (fracao.getNumerador().getValor() > fracao.getDenominador().getValor()) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.verificarNumeradorMaiorComResto.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			divisao = OperacaoUtil.divisao(fracao.getNumerador(), fracao.getDenominador());
			abreMath();
			operacao.getRetorno().add(MathjaxTag.MO_OPEN);
			operacao.getRetorno().add(divisao.getQuociente());
			operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
			montaFracao(divisao.getResto(), fracao.getDenominador());
			fechaMath();
			fracao = new Fracao(divisao.getResto(), fracao.getDenominador());

		}
	}

	private void validarFracaoIrredutivel() throws RegraException {
		if (OperacaoUtil.mdc(fracao.getNumerador(), fracao.getDenominador()).getValor() == 1) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.validarFracaoIrredutivel.1", null, locale)));
			abreMath();
			if (divisao != null) {
				operacao.getRetorno().add(MathjaxTag.MO_OPEN);
				operacao.getRetorno().add(divisao.getQuociente());
				operacao.getRetorno().add(MathjaxTag.MO_CLOSE);
			}
			montaFracao(fracao.getNumerador(), fracao.getDenominador());
			fechaMath();
			throw new RegraException();
		}
	}

	public void setFracao(Fracao fracao) {
		this.fracao = fracao;
	}

	private void carregarMdc() {
		mdcBuild.setLocale(locale);
		mdcBuild.setOperacao(operacao);
		List<Inteiro> denominadores = new ArrayList<>();
		denominadores.add(fracao.getNumerador());
		denominadores.add(fracao.getDenominador());
		MDC mdc = new MDC(denominadores, new Inteiro(1));
		mdcBuild.setMdc(mdc);
		
	}

	private void verificarNumeradorIgual() throws RegraException {
		if (fracao.getNumerador().getValor() == fracao.getDenominador().getValor()) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.verificarNumeradorIgual.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			divisao = OperacaoUtil.divisao(fracao.getNumerador(), fracao.getDenominador());
			operacao.getRetorno().add(fracao.getNumerador());
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Operando.DIVISAO);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(fracao.getDenominador());
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(divisao.getQuociente());
			throw new RegraException();
		}
	}

	private void verificarNumeradorMaiorSemResto() throws RegraException {
		if ((fracao.getNumerador().getValor() > fracao.getDenominador().getValor())
				&& (OperacaoUtil.resto(fracao.getNumerador(), fracao.getDenominador()).getValor().equals(0))) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.verificarNumeradorMaiorSemResto.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			divisao = OperacaoUtil.divisao(fracao.getNumerador(), fracao.getDenominador());
			operacao.getRetorno().add(fracao.getNumerador());
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Operando.DIVISAO);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(fracao.getDenominador());
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(divisao.getQuociente());
			throw new RegraException();
		}
	}

	private boolean validarFracaoBase10() {
		if (fracao.getNumerador().getValor() % 10 == 0 && fracao.getDenominador().getValor() % 10 == 0) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSimplificacaoBuild.validarFracaoBase10.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			Divisao divNumerador = OperacaoUtil.divisao(fracao.getNumerador(), new Inteiro(10));
			Divisao divDenominador = OperacaoUtil.divisao(fracao.getDenominador(), new Inteiro(10));
			abreMath();
			montaFracao(divNumerador.getQuociente(), divDenominador.getQuociente());
			fechaMath();
			fracao = new Fracao(divNumerador.getQuociente(), divDenominador.getQuociente());
			while (validarFracaoBase10());
			return true;
		}
		return false;
	}

	public void setValidarNumeradorMaiorComResto(boolean validarNumeradorMaiorComResto) {
		this.validarNumeradorMaiorComResto = validarNumeradorMaiorComResto;
	}

}
