package com.mhj.math.build;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mhj.math.enums.LineSeparator;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Potenciacao;
import com.mhj.math.type.Descricao;

@Component
public class PotenciaMultiplicacaoBuild extends PotenciaBuild {

	@Override
	protected void validarParametros() throws BusinessException, RegraException {
//		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	@Override
	protected void titulo() throws BusinessException {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoMultiplicacaoBuild.titulo.1", null, locale)));

		exibirPotencias(potencias);

		operacao.getRetorno().add(LineSeparator.BREAK);

	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		//a^0 = 1
//		validarExpoenteZero();
		//a^1 = a
//		validarExpoenteUm();
		//a^-x = 1/a^x => 2^-3 = 1/2^3 = 1/8
		validarExpoenteNegativo();
		//a^m . a^n  =  a^m + n => 2^2 . 2^3 = 2^2+3 = 2^5 = 32
		validarMesmaBase();
		//a^m . b^m = (a.b)^m => 2^3 . 2^2 = 2^5 = 32
		validarMesmoExpoente();
		//(a^m)^n = a^m.n = (3^2)^2 = 3^4 = 81
		validar();
		//(a/b)^n = a^n / b^n => (4/3)2 = 4^2/3^2 = 16/9
		validarFracaoPotencia();
		//bases e expoentes diferentes nao ha regra

	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		ordenarExpoentesIguais();
		ordenarBasesIguais();
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	private void exibirPotencias(List<Potenciacao> potencias) {
		Potenciacao potencia = potencias.get(0);

		abreMath();

		if (potencia.getBase().getValor() < 0) {
			montaPotencia(potencia, true);
		} else {
			montaPotencia(potencia, false);
		}

		for (int i = 1; i < potencias.size(); i++) {
			potencia = potencias.get(i);
			montaPotencia(potencia, true);

		}

		fechaMath();

	}

	private void validarFracaoPotencia() {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	private void validar() {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	private void validarMesmoExpoente() {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	private void validarMesmaBase() {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	private void ordenarBasesIguais() {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	private void ordenarExpoentesIguais() {
		throw new UnsupportedOperationException("Operacao nao permitida");
	}

}
