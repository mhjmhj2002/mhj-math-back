package com.mhj.math.build;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.MathjaxProperty;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.enums.MathjaxValue;
import com.mhj.math.enums.Operando;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Divisao;
import com.mhj.math.operacao.MMC;
import com.mhj.math.type.Descricao;
import com.mhj.math.type.Inteiro;
import com.mhj.math.type.PropertyComposta;
import com.mhj.math.type.TagComposta;
import com.mhj.math.type.ValueComposta;
import com.mhj.math.util.MathjaxUtil;
import com.mhj.math.util.OperacaoUtil;

@Component
public class MMCBuild extends Build {

	private MMC mmc;
	List<MMC> decomposicoes;
	Inteiro resultado;

	public MMCBuild() {
		super();
		decomposicoes = new ArrayList<>();
	}

	@Override
	protected void validarParametros() throws BusinessException {
		if (mmc == null) {
			throw new BusinessException(messageSource.getMessage("MMCBuild.validarParametros.1", null, locale));
		}
		if (mmc.getNumeros() == null || mmc.getNumeros().isEmpty()) {
			throw new BusinessException(messageSource.getMessage("MMCBuild.validarParametros.1", null, locale));
		}
	}

	@Override
	protected void titulo() throws BusinessException {
		if (mmc.isIrredutivel()) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("MMCBuild.titulo.1", null, locale)));
		} else {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("MMCBuild.titulo.2", null, locale)));
		}

		for (Inteiro numero : mmc.getNumeros()) {
			operacao.getRetorno().add(numero);
			operacao.getRetorno().add(Simbolo.VIRGULA);
			operacao.getRetorno().add(Simbolo.ESPACO);
		}
		operacao.getRetorno().remove(operacao.getRetorno().size() - 1);
		operacao.getRetorno().remove(operacao.getRetorno().size() - 1);
		operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);

	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		verificarNumerosIguais();
		verificarPrimos();

	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		calcularDecomposicoes(mmc);
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("MMCBuild.resolucao.1", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);

		abreMath();

		List<Inteiro> resultados = montaMatriz();

		fechaMath();

		if (resultados.size() > 1) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("MMCBuild.resolucao.2", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			for (Inteiro inteiro : resultados) {
				operacao.getRetorno().add(inteiro);
				operacao.getRetorno().add(Operando.MULTIPLICACAO);
			}
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(resultado);
			operacao.getRetorno().add(LineSeparator.BREAK);
		}
		
		operacao.getRetorno().add(new Descricao("MMC"));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(resultado);
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);

	}

	private void verificarPrimos() throws RegraException {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("MMCBuild.verificarPrimos.1", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);

		boolean primos = true;

		for (Inteiro numero : mmc.getNumeros()) {
			if (!OperacaoUtil.ehPrimo(numero)) {
				primos = false;
				break;
			}
		}

		if (primos) {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("MMCBuild.verificarPrimos.2", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(new Descricao("MMC"));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);

			Inteiro multiplicacao = new Inteiro(1);

			for (Inteiro numero : mmc.getNumeros()) {
				operacao.getRetorno().add(numero);
				operacao.getRetorno().add(Simbolo.ESPACO);
				operacao.getRetorno().add(Operando.MULTIPLICACAO);
				operacao.getRetorno().add(Simbolo.ESPACO);
				multiplicacao = OperacaoUtil.multiplicacao(multiplicacao, numero);
			}

			operacao.getRetorno().remove(operacao.getRetorno().size() - 1);
			operacao.getRetorno().remove(operacao.getRetorno().size() - 1);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(multiplicacao);
			
			resultado = multiplicacao; 

			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(LineSeparator.BREAK);
					
			throw new RegraException();
		} else {
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("MMCBuild.verificarPrimos.3", null, locale)));
		}
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	private void calcularDecomposicoes(MMC mmc) throws BusinessException {

		if (mmc.isIrredutivel()) {
			if (calculoDecomposicoesFinalizado(mmc)) {
				return;
			}
			decomposicoes.add(mmc);
		} else {
			decomposicoes.add(mmc);
			if (calculoDecomposicoesFinalizado(mmc)) {
				return;
			}
		}

		List<Inteiro> numeros = new ArrayList<>();

		for (Inteiro numero : mmc.getNumeros()) {
			if (numero.getValor() < 1) {
				throw new BusinessException(messageSource.getMessage("MMCBuild.calcularDecomposicoes.1", null, locale));
			}

			if (numero.getValor() == 1) {
				numeros.add(numero);
				continue;
			}

			if (numero.getValor() < mmc.getDivisor().getValor()) {
				numeros.add(numero);
				continue;
			}

			if (OperacaoUtil.resto(numero, mmc.getDivisor()).getValor() == 0) {
				Divisao divisao = OperacaoUtil.divisao(numero, mmc.getDivisor());
				numeros.add(divisao.getQuociente());
				continue;
			} else {
				numeros.add(numero);
			}
		}

		MMC calculo;
		if (mmc.isIrredutivel()) {
			calculo = new MMC(numeros, mmc.getResultado(), true);
		} else {
			calculo = new MMC(numeros, mmc.getResultado());
		}
		calcularDecomposicoes(calculo);

	}

	private boolean calculoDecomposicoesFinalizado(MMC mmc) {
		List<Inteiro> numeros = mmc.getNumeros();
		for (Inteiro inteiro : numeros) {
			if (mmc.isIrredutivel()) {
				if (inteiro.getValor() < 2) {
					return true;
				} else {
					return false;
				}
			} else if (inteiro.getValor() > 1) {
				return false;
			}
		}
		return true;
	}

	public Inteiro getResultado() {
		return resultado;
	}

	private List<Inteiro> montaMatriz() {
		List<Inteiro> resultados = new ArrayList<>();
		List<ValueComposta> values = new ArrayList<>();
		List<PropertyComposta> properties = new ArrayList<>();
		int qtdeNumeros = mmc.getNumeros().size();

		ValueComposta value = new ValueComposta(MathjaxValue.PT, new Inteiro(qtdeNumeros));
		values.add(value);
		PropertyComposta property = new PropertyComposta(MathjaxProperty.ROW_SPACING, values);
		properties.add(property);

		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.EM, new Inteiro(1));
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.COLUMN_SPACING, values);
		properties.add(property);

		TagComposta tag = new TagComposta(MathjaxTag.MTABLE_OPEN, properties);
		operacao.getRetorno().addAll(MathjaxUtil.montarTagComposta(tag));

		properties = new ArrayList<>();

		operacao.getRetorno().add(MathjaxTag.MTD_OPEN);

		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.CENTER);
		for (int i = 0; i < qtdeNumeros; i++) {
			values.add(value);
		}
		values.add(value);
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.COLUMN_ALIGN, values);
		properties.add(property);

		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.PT, new Inteiro(qtdeNumeros));
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.ROW_SPACING, values);
		properties.add(property);

		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.EM, new Inteiro(1));
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.COLUMN_SPACING, values);
		properties.add(property);

		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.SOLID);
		values.add(value);
		value = new ValueComposta(MathjaxValue.NONE);
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.ROW_LINES, values);
		properties.add(property);

		values = new ArrayList<>();

		for (int i = 0; i < qtdeNumeros; i++) {
			if (i == qtdeNumeros - 1) {
				value = new ValueComposta(MathjaxValue.SOLID);
				values.add(value);
			} else {
				value = new ValueComposta(MathjaxValue.NONE);
				values.add(value);
			}
		}
		value = new ValueComposta(MathjaxValue.NONE);
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.COLUMN_LINES, values);
		properties.add(property);

		tag = new TagComposta(MathjaxTag.MTABLE_OPEN, properties);
		operacao.getRetorno().addAll(MathjaxUtil.montarTagComposta(tag));

		for (MMC decomposicao : decomposicoes) {
			operacao.getRetorno().add(MathjaxTag.MTR_OPEN);
			for (Inteiro numero : decomposicao.getNumeros()) {
				operacao.getRetorno().add(MathjaxTag.MTD_OPEN);
				operacao.getRetorno().add(MathjaxTag.MN_OPEN);
				operacao.getRetorno().add(numero);
				operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
				operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);
			}
			operacao.getRetorno().add(MathjaxTag.MTD_OPEN);
			operacao.getRetorno().add(MathjaxTag.MN_OPEN);
			operacao.getRetorno().add(decomposicao.getDivisor());
			operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
			operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);

			resultados.add(decomposicao.getDivisor());
			resultado = decomposicao.getResultado();

			operacao.getRetorno().add(MathjaxTag.MTR_CLOSE);

		}
		operacao.getRetorno().add(MathjaxTag.MTABLE_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MTD_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MTABLE_CLOSE);
		return resultados;
	}

	public void setMmc(MMC mmc) {
		this.mmc = mmc;
	}

	private void verificarNumerosIguais() throws RegraException {
		
		Inteiro num = mmc.getNumeros().get(0);

		for (Inteiro numero : mmc.getNumeros()) {
			if (!numero.getValor().equals(num.getValor())) {
				return;
			}
		}

			operacao.getRetorno().add(new Descricao(messageSource.getMessage("MMCBuild.verificarNumerosIguais.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(new Descricao("MMC"));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(num);
			
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			this.resultado = num;

			throw new RegraException();
	}

}
