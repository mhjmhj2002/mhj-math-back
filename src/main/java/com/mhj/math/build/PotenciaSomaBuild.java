package com.mhj.math.build;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.mhj.math.data.Descricao;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Potenciacao;
import com.mhj.math.util.OperacaoUtil;

@Component
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PotenciaSomaBuild extends PotenciaBuild {

	@Override
	protected void validarParametros() throws BusinessException, RegraException {

	}

	@Override
	protected void titulo() throws BusinessException {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("PotenciaSomaBuild.titulo.1", null, locale)));

		exibirPotencias(potencias);

		operacao.getRetorno().add(LineSeparator.BREAK);

	}

	@Override
	protected void regras() throws BusinessException, RegraException {
		for (Potenciacao potencia : potencias) {
			validarExpoenteZero(potencia);
			validarExpoenteUm(potencia);
		}
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		Potenciacao potencia = potencias.get(0);
		montarPorExtenso(potencia, potencia.getBase().getValor() < 0);

		for (int i = 1; i < potencias.size(); i++) {
			potencia = potencias.get(i);
			montarPorExtenso(potencia, true);
			
		}
	}
	
	private void montarPorExtenso(Potenciacao potencia, boolean mostraSinal) {
		if (OperacaoUtil.validarValor(potencia.getExpoente(), 0)) {
			String numero = mostraSinal ? "+1" : "1";
			if (potencia.getBase().getValor() < 0) {
				numero = "-1";
			}
			operacao.getRetorno().add(new Descricao(numero + " "));
			return;
		}
		if (OperacaoUtil.validarValor(potencia.getExpoente(), 1)) {
			Integer valor;
			String sinal = "";
			if (mostraSinal) {
				if (potencia.getBase().getValor() < 0) {
					sinal = "-";
				} else {
					sinal = "+";
				}
				valor = potencia.getBase().getValor();
			} else {
				if (potencia.getBase().getValor() < 0) {
					sinal = "-";
				}
				valor = potencia.getBase().getValor();
			}
			operacao.getRetorno().add(new Descricao(sinal + valor + " "));
			return;
		}
		Integer expoente = potencia.getExpoente().getValor();
		StringBuilder linha = new StringBuilder();
		if (mostraSinal) {
			if (potencia.getBase().getValor() < 0) {
				linha.append("");
			} else {
				linha.append("+");
			}
		}
		linha.append(potencia.getBase().getValor());
		for (int i = 1; i < expoente; i++) {
			linha.append("*");
			if (potencia.getBase().getValor() < 0) {
				linha.append("");
			} 
			linha.append(potencia.getBase().getValor());
		}
		operacao.getRetorno().add(new Descricao(linha.toString()));
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

}
