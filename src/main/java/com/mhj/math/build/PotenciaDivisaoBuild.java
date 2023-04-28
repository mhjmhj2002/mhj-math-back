package com.mhj.math.build;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mhj.math.enums.LineSeparator;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Potenciacao;
import com.mhj.math.type.Descricao;

@Component
public class PotenciaDivisaoBuild extends PotenciaBuild {

	@Override
	protected void validarParametros() throws BusinessException, RegraException {
//		throw new UnsupportedOperationException("Operacao invalida");
	}

	@Override
	protected void titulo() throws BusinessException {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("FracaoSomaBuild.titulo.1", null, locale)));

		exibirPotencias(potencias);

		operacao.getRetorno().add(LineSeparator.BREAK);

	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		validarExpoenteIgual();
		validarBaseIgual();

	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		throw new UnsupportedOperationException("Operacao invalida");

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

	private void validarBaseIgual() {
		// TODO Auto-generated method stub
		
	}

	private void validarExpoenteIgual() {
		// TODO Auto-generated method stub
		
	}

}
