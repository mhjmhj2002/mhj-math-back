package com.mhj.math.build;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.data.interfaces.Data;
import com.mhj.math.enums.Letra;
import com.mhj.math.enums.LineSeparator;
import com.mhj.math.enums.Operando;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.enums.Sinal;
import com.mhj.math.enums.SinalDelta;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.metodo.Bhaskara;
import com.mhj.math.operacao.Divisao;
import com.mhj.math.operacao.EquacaoGrau2;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.util.OperacaoUtil;

@Component
public class EquacaoGrau2Build extends Build{

	EquacaoGrau2 equacaoGrau2;
	
	public EquacaoGrau2Build() {
		super();
		equacaoGrau2 = new EquacaoGrau2();
	}

	@Override
	protected void validarParametros() throws BusinessException, RegraException {
//		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	@Override
	protected void titulo() {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.titulo.1", null, locale)));
		operacao.getRetorno().add(new Descricao(equacaoGrau2.getMetodo().getNome()));
		operacao.getRetorno().add(Simbolo.ESPACO);
		
		List<Data> formula = equacaoGrau2.getMetodo().getFormula();
		operacao.getRetorno().addAll(formula);
	}

	@Override
	protected void regras() throws BusinessException, RegraException {
//		throw new UnsupportedOperationException("Operacao nao permitida");
	}

	@Override
	protected void resolucao() throws BusinessException, RegraException {
		equacao();
		resolverDelta();
		resolverX1();
		resolverX2();
		verificaco();		
	}

	private void equacao() {
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.equacao.1", null, locale)));
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
		operacao.getRetorno().add(Letra.X);
		operacao.getRetorno().add(Simbolo.AO_2);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getBInteiro(), true));
		operacao.getRetorno().add(Letra.X);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(equacaoGrau2.getResultado());
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);
	}

	private void resolverDelta() throws BusinessException {

		if (!(this.equacaoGrau2.getMetodo() instanceof Bhaskara)) {
			throw new BusinessException(messageSource.getMessage("EquacaoGrau2Build.resolverDelta.1", null, locale));
		}

		Inteiro quadrado = OperacaoUtil.quadrado(equacaoGrau2.getBInteiro());
		Inteiro ac = OperacaoUtil.multiplicacao(equacaoGrau2.getAInteiro(), equacaoGrau2.getCInteiro());
		Inteiro menos4ac = OperacaoUtil.multiplicacao(new Inteiro(-4), ac);
		Inteiro delta = OperacaoUtil.calculaSomaSubtracao(quadrado, menos4ac);
		equacaoGrau2.setDelta(delta);

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.resolverDelta.2", null, locale)));
		operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
		operacao.getRetorno().add(Simbolo.DELTA);
		operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(Simbolo.DELTA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Letra.b);
		operacao.getRetorno().add(Simbolo.AO_2);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Sinal.NEGATIVO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(new Inteiro(4));
		operacao.getRetorno().add(Letra.a);
		operacao.getRetorno().add(Letra.c);
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.resolverDelta.3", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(Simbolo.DELTA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getBInteiro(), false));
		operacao.getRetorno().add(Simbolo.AO_2);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Sinal.NEGATIVO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(new Inteiro(4));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.MULTIPLICACAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.MULTIPLICACAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), false));
		operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(Simbolo.DELTA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(quadrado, false));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.SUBTRACAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(new Inteiro(4));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.MULTIPLICACAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(ac, false));
		operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(Simbolo.DELTA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(quadrado, false));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(menos4ac, true));
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(Simbolo.DELTA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getDeltaInteiro(), false));
		
		if (SinalDelta.NEGATIVO.equals(equacaoGrau2.getSinalDelta())) {
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.resolverDelta.4", null, locale)));
		}else if (SinalDelta.ZERO.equals(equacaoGrau2.getSinalDelta())) {
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.resolverDelta.5", null, locale)));
		}

		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);

//		Lembre-se:
//			Quando delta (Δ) é positivo, há duas raízes reais e distintas.
//			Quando delta (Δ) é zero, há apenas uma raiz real.
//			Quando delta (Δ) é negativo, não há raízes reais.
//			Com delta (Δ) negativo, não significa que não há gráfico da função, mas sim que ele não tem contato com nenhum dos eixos.
	}

	private void resolverX1() {
		
		if (SinalDelta.NEGATIVO.equals(equacaoGrau2.getSinalDelta())) {
			return;
		}
		
		String xVal = "X1";
		
		Inteiro raiz = OperacaoUtil.raiz2(equacaoGrau2.getDeltaInteiro());
		Inteiro multiplicacaoDivisor = OperacaoUtil.multiplicacao(new Inteiro(2),equacaoGrau2.getAInteiro());
		Inteiro menosB = OperacaoUtil.multiplicacao(equacaoGrau2.getBInteiro(), new Inteiro(-1));
		Inteiro somaMenosBRaiz = OperacaoUtil.calculaSomaSubtracao(menosB,raiz);
		Divisao divisao = OperacaoUtil.divisao(somaMenosBRaiz, multiplicacaoDivisor);
		if (divisao.getResto().getValor() == 0) {
			equacaoGrau2.setX1(OperacaoUtil.getNumeroComSinal(divisao.getQuociente(), false));
		}else{
			equacaoGrau2.setX1Fracao(OperacaoUtil.simplificarFracao(new Fracao(somaMenosBRaiz, multiplicacaoDivisor)));
		}

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.resolverX1.1", null, locale)));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Sinal.NEGATIVO);
		operacao.getRetorno().add(Letra.b);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Sinal.POSITIVO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.RAIZ);
		operacao.getRetorno().add(Simbolo.DELTA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(new Inteiro(2));
		operacao.getRetorno().add(Letra.a);
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.resolverX1.2", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(menosB, false));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.SOMA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.RAIZ);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getDeltaInteiro(), false));
		operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(new Inteiro(2));
		operacao.getRetorno().add(Operando.MULTIPLICACAO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(menosB, false));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.SOMA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(raiz, false));
		operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(multiplicacaoDivisor, false));
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(somaMenosBRaiz, false));
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(multiplicacaoDivisor, false));
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		
		if (equacaoGrau2.getX1() != null) {
			operacao.getRetorno().add(equacaoGrau2.getX1());
		}else{
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX1Fracao().getNumerador(), false));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX1Fracao().getDenominador(), false));
		}
		
		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);

	}

	private void resolverX2() {
		
		if (SinalDelta.NEGATIVO.equals(equacaoGrau2.getSinalDelta()) || SinalDelta.ZERO.equals(equacaoGrau2.getSinalDelta())) {
			return;
		}
		
		String xVal = "X2";
		Inteiro raiz = OperacaoUtil.raiz2(equacaoGrau2.getDeltaInteiro());
		Inteiro multiplicacaoDivisor = OperacaoUtil.multiplicacao(new Inteiro(2), equacaoGrau2.getAInteiro());
		Inteiro menosB = OperacaoUtil.multiplicacao(equacaoGrau2.getBInteiro(), new Inteiro(-1));
		Inteiro somaMenosBRaiz = OperacaoUtil.calculaSomaSubtracao(menosB, OperacaoUtil.multiplicacao(raiz, new Inteiro(-1)));
		Divisao divisao = OperacaoUtil.divisao(somaMenosBRaiz, multiplicacaoDivisor);
		if (divisao.getResto().getValor() == 0) {
			equacaoGrau2.setX2(OperacaoUtil.getNumeroComSinal(divisao.getQuociente(), false));
		}else{
			equacaoGrau2.setX2Fracao(OperacaoUtil.simplificarFracao(new Fracao(somaMenosBRaiz, multiplicacaoDivisor)));
		}

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.resolverX2.1", null, locale)));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Sinal.NEGATIVO);
		operacao.getRetorno().add(Letra.b);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Sinal.NEGATIVO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.RAIZ);
		operacao.getRetorno().add(Simbolo.DELTA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(new Inteiro(2));
		operacao.getRetorno().add(Letra.a);
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.resolverX2.2", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(menosB, false));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.SUBTRACAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.RAIZ);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getDeltaInteiro(), false));
		operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(new Inteiro(2));
		operacao.getRetorno().add(Operando.MULTIPLICACAO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(menosB, false));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.SUBTRACAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(raiz, false));
		operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(multiplicacaoDivisor, false));
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(somaMenosBRaiz, false));
		operacao.getRetorno().add(Operando.DIVISAO);
		operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(multiplicacaoDivisor, false));
		operacao.getRetorno().add(LineSeparator.BREAK);

		operacao.getRetorno().add(new Descricao(xVal));
		operacao.getRetorno().add(Simbolo.ESPACO);
		operacao.getRetorno().add(Simbolo.IGUAL);
		operacao.getRetorno().add(Simbolo.ESPACO);
		if (equacaoGrau2.getX2() != null) {
			operacao.getRetorno().add(equacaoGrau2.getX2());
		}else{
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX2Fracao().getNumerador(), false));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX2Fracao().getDenominador(), false));
		}

		operacao.getRetorno().add(LineSeparator.BREAK);
		operacao.getRetorno().add(LineSeparator.BREAK);

	}

	private void verificaco() {
		
		if (SinalDelta.NEGATIVO.equals(equacaoGrau2.getSinalDelta())) {
			return;
		}
		
		operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.verificaco.1", null, locale)));
		operacao.getRetorno().add(LineSeparator.BREAK);
		
		equacao();
		verificacoX1();
		verificacoX2();

	}
	
	private void verificacoX1() {		
		if (equacaoGrau2.getX1Fracao() != null) {
			//termo1
			Fracao aF = null;
			Fracao bF = new Fracao(equacaoGrau2.getBInteiro(), new Inteiro(1));

			Fracao quadradoXF = OperacaoUtil.quadrado(equacaoGrau2.getX1Fracao());
			Fracao quadradoXFSimpl = OperacaoUtil.simplificarFracao(quadradoXF);
			Inteiro quadradoXR = null;
			
			Fracao termo1F = null;
			Fracao termo1FSimpl = null;
			Inteiro termo1R = null;
			
			if (OperacaoUtil.fracaoEhReal(quadradoXFSimpl)) {
				quadradoXR = quadradoXFSimpl.getNumerador();
				termo1R = OperacaoUtil.multiplicacao(quadradoXR, equacaoGrau2.getAInteiro());
			}else{
				aF = new Fracao(equacaoGrau2.getAInteiro(), new Inteiro(1));
				termo1F = OperacaoUtil.multiplicacao(aF, quadradoXFSimpl);
				termo1FSimpl = OperacaoUtil.simplificarFracao(termo1F);
				if (OperacaoUtil.fracaoEhReal(termo1FSimpl)) {
					termo1R = termo1FSimpl.getNumerador();
				}
			}			
			
			//termo2
			Fracao termo2F = OperacaoUtil.multiplicacao(new Fracao(equacaoGrau2.getBInteiro(), new Inteiro(1)), equacaoGrau2.getX1Fracao());
			Fracao termo2FSimpl = OperacaoUtil.simplificarFracao(termo2F);
			Inteiro termo2R = null;
			
			if (OperacaoUtil.fracaoEhReal(termo2FSimpl)) {
				termo2R = termo2FSimpl.getNumerador();
			}
			
			//operacao entre termo1 e termo2
			
			Fracao somaTermo1Termo2F = null;
			Fracao somaTermo1Termo2FSimpl = null;
			Inteiro somaTermo1Termo2R = null;
			
			if (termo1R != null && termo2R != null) {
				somaTermo1Termo2R = OperacaoUtil.calculaSomaSubtracao(termo1R, termo2R);
			}else{
				somaTermo1Termo2F = OperacaoUtil.calculaSomaSubtracao(termo1FSimpl, termo2FSimpl);
				somaTermo1Termo2FSimpl = OperacaoUtil.simplificarFracao(somaTermo1Termo2F);
				if (OperacaoUtil.fracaoEhReal(somaTermo1Termo2FSimpl)) {
					somaTermo1Termo2R = somaTermo1Termo2FSimpl.getNumerador();
				}
			}
			
			Fracao equacaoF = null;
			Fracao equacaoFSimpl = null;
			Inteiro equacaoR = null;
			
			if (somaTermo1Termo2R != null) {
				equacaoR = OperacaoUtil.calculaSomaSubtracao(somaTermo1Termo2R, equacaoGrau2.getCInteiro());
			}else{
				equacaoF = OperacaoUtil.calculaSomaSubtracao(somaTermo1Termo2F, new Fracao(equacaoGrau2.getCInteiro(), new Inteiro(1)));
				equacaoFSimpl = OperacaoUtil.simplificarFracao(equacaoF);
				if (OperacaoUtil.fracaoEhReal(equacaoFSimpl)) {
					equacaoR = equacaoFSimpl.getNumerador();
				}
			}
			
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.verificacoX1.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX1Fracao().getNumerador(), false));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX1Fracao().getDenominador(), false));
			operacao.getRetorno().add(Simbolo.AO_2);
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getBInteiro(), true));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX1Fracao().getNumerador(), false));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX1Fracao().getDenominador(), false));
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			//termo1
			if (quadradoXR != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
				operacao.getRetorno().add(Operando.MULTIPLICACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(quadradoXR, false));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(aF.getNumerador(), false));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(aF.getDenominador(), false));
				operacao.getRetorno().add(Operando.MULTIPLICACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(quadradoXF.getNumerador(), false));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(quadradoXF.getDenominador(), false));
			}
			
			//termo2
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(bF.getNumerador(), true));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(bF.getDenominador(), false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX1Fracao().getNumerador(), false));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX1Fracao().getDenominador(), false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			//termo1
			if (termo1R != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo1R, false));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo1F.getNumerador(), false));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo1F.getDenominador(), false));
			}			
			
			//termo2
			operacao.getRetorno().add(Simbolo.ESPACO);
			if (termo2R != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo2R, true));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo2F.getNumerador(), true));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo2F.getDenominador(), false));
			}
			
			//termo3
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			if (somaTermo1Termo2R != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(somaTermo1Termo2R, false));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(somaTermo1Termo2F.getNumerador(), true));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(somaTermo1Termo2F.getDenominador(), false));
			}
			
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			if (equacaoR != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoR, false));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoF.getNumerador(), true));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoF.getDenominador(), false));
			}
			
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
		}else{
			Inteiro xQuadrado = OperacaoUtil.quadrado(equacaoGrau2.getX1Inteiro());
			Inteiro ax = OperacaoUtil.multiplicacao(equacaoGrau2.getAInteiro(), xQuadrado);
			Inteiro bx = OperacaoUtil.multiplicacao(equacaoGrau2.getBInteiro(), equacaoGrau2.getX1Inteiro());
			Inteiro axbx = OperacaoUtil.calculaSomaSubtracao(ax, bx);
			Inteiro equacao = OperacaoUtil.calculaSomaSubtracao(axbx, equacaoGrau2.getCInteiro());
			
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.verificacoX1.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(equacaoGrau2.getX1());
			operacao.getRetorno().add(Simbolo.AO_2);
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getBInteiro(), false));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(equacaoGrau2.getX1());
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(xQuadrado, false));
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(bx, true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(ax, false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(bx, true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(axbx, false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacao, false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(LineSeparator.BREAK);
		}
	}
	
	private void verificacoX2() {
		
		if (SinalDelta.NEGATIVO.equals(equacaoGrau2.getSinalDelta()) || SinalDelta.ZERO.equals(equacaoGrau2.getSinalDelta())) {
			return;
		}
		
		if (equacaoGrau2.getX2Fracao() != null) {
			//termo1
			Fracao aF = new Fracao(equacaoGrau2.getAInteiro(), new Inteiro(1));
			Fracao bF = new Fracao(equacaoGrau2.getBInteiro(), new Inteiro(1));

			Fracao quadradoXF = OperacaoUtil.quadrado(equacaoGrau2.getX2Fracao());
			Fracao quadradoXFSimpl = OperacaoUtil.simplificarFracao(quadradoXF);
			Inteiro quadradoXR = null;
			
			Fracao termo1F = null;
			Fracao termo1FSimpl = null;
			Inteiro termo1R = null;
			
			if (OperacaoUtil.fracaoEhReal(quadradoXFSimpl)) {
				quadradoXR = quadradoXFSimpl.getNumerador();
				termo1R = OperacaoUtil.multiplicacao(quadradoXR, equacaoGrau2.getAInteiro());
			}else{
				aF = new Fracao(equacaoGrau2.getAInteiro(), new Inteiro(1));
				termo1F = OperacaoUtil.multiplicacao(aF, quadradoXFSimpl);
				termo1FSimpl = OperacaoUtil.simplificarFracao(termo1F);
				if (OperacaoUtil.fracaoEhReal(termo1FSimpl)) {
					termo1R = termo1FSimpl.getNumerador();
				}
			}			
			
			//termo2
			Fracao termo2F = OperacaoUtil.multiplicacao(new Fracao(equacaoGrau2.getBInteiro(), new Inteiro(1)), equacaoGrau2.getX2Fracao());
			Fracao termo2FSimpl = OperacaoUtil.simplificarFracao(termo2F);
			Inteiro termo2R = null;
			
			if (OperacaoUtil.fracaoEhReal(termo2FSimpl)) {
				termo2R = termo2FSimpl.getNumerador();
			}
			
			//operacao entre termo1 e termo2
			
			Fracao somaTermo1Termo2F = null;
			Fracao somaTermo1Termo2FSimpl = null;
			Inteiro somaTermo1Termo2R = null;
			
			if (termo1R != null && termo2R != null) {
				somaTermo1Termo2R = OperacaoUtil.calculaSomaSubtracao(termo1R, termo2R);
			}else{
				somaTermo1Termo2F = OperacaoUtil.calculaSomaSubtracao(termo1FSimpl, termo2FSimpl);
				somaTermo1Termo2FSimpl = OperacaoUtil.simplificarFracao(somaTermo1Termo2F);
				if (OperacaoUtil.fracaoEhReal(somaTermo1Termo2FSimpl)) {
					somaTermo1Termo2R = somaTermo1Termo2FSimpl.getNumerador();
				}
			}
			
			Fracao equacaoF = null;
			Fracao equacaoFSimpl = null;
			Inteiro equacaoR = null;
			
			if (somaTermo1Termo2R != null) {
				equacaoR = OperacaoUtil.calculaSomaSubtracao(somaTermo1Termo2R, equacaoGrau2.getCInteiro());
			}else{
				equacaoF = OperacaoUtil.calculaSomaSubtracao(somaTermo1Termo2F, new Fracao(equacaoGrau2.getCInteiro(), new Inteiro(1)));
				equacaoFSimpl = OperacaoUtil.simplificarFracao(equacaoF);
				if (OperacaoUtil.fracaoEhReal(equacaoFSimpl)) {
					equacaoR = equacaoFSimpl.getNumerador();
				}
			}
			
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.verificacoX2.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX2Fracao().getNumerador(), false));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX2Fracao().getDenominador(), false));
			operacao.getRetorno().add(Simbolo.AO_2);
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getBInteiro(), true));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX2Fracao().getNumerador(), false));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX2Fracao().getDenominador(), false));
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			//termo1
			if (quadradoXR != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
				operacao.getRetorno().add(Operando.MULTIPLICACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(quadradoXR, false));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(aF.getNumerador(), false));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(aF.getDenominador(), false));
				operacao.getRetorno().add(Operando.MULTIPLICACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(quadradoXF.getNumerador(), false));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(quadradoXF.getDenominador(), false));
			}
			
			//termo2
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(bF.getNumerador(), true));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(bF.getDenominador(), false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX2Fracao().getNumerador(), false));
			operacao.getRetorno().add(Simbolo.FRACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getX2Fracao().getDenominador(), false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			//termo1
			if (termo1R != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo1R, false));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo1F.getNumerador(), false));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo1F.getDenominador(), false));
			}			
			
			//termo2
			operacao.getRetorno().add(Simbolo.ESPACO);
			if (termo2R != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo2R, true));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo2F.getNumerador(), true));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(termo2F.getDenominador(), false));
			}
			
			//termo3
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			if (somaTermo1Termo2R != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(somaTermo1Termo2R, false));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(somaTermo1Termo2F.getNumerador(), true));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(somaTermo1Termo2F.getDenominador(), false));
			}
			
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			if (equacaoR != null) {
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoR, false));
			}else{
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoF.getNumerador(), true));
				operacao.getRetorno().add(Simbolo.FRACAO);
				operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoF.getDenominador(), false));
			}
			
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
		}else{
			Inteiro xQuadrado = OperacaoUtil.quadrado(equacaoGrau2.getX2Inteiro());
			Inteiro ax = OperacaoUtil.multiplicacao(equacaoGrau2.getAInteiro(), xQuadrado);
			Inteiro bx = OperacaoUtil.multiplicacao(equacaoGrau2.getBInteiro(), equacaoGrau2.getX2Inteiro());
			Inteiro axbx = OperacaoUtil.calculaSomaSubtracao(ax, bx);
			Inteiro equacao = OperacaoUtil.calculaSomaSubtracao(axbx, equacaoGrau2.getCInteiro());
			
			operacao.getRetorno().add(new Descricao(messageSource.getMessage("EquacaoGrau2Build.verificacoX2.1", null, locale)));
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(equacaoGrau2.getX2());
			operacao.getRetorno().add(Simbolo.AO_2);
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getBInteiro(), false));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(equacaoGrau2.getX2());
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(Simbolo.PARENTESE_ABRE);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getAInteiro(), false));
			operacao.getRetorno().add(Operando.MULTIPLICACAO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(xQuadrado, false));
			operacao.getRetorno().add(Simbolo.PARENTESE_FECHA);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(bx, true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(ax, false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(bx, true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(axbx, false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacaoGrau2.getCInteiro(), true));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			
			operacao.getRetorno().add(OperacaoUtil.getNumeroComSinal(equacao, false));
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(Simbolo.IGUAL);
			operacao.getRetorno().add(Simbolo.ESPACO);
			operacao.getRetorno().add(equacaoGrau2.getResultado());
			operacao.getRetorno().add(LineSeparator.BREAK);
			operacao.getRetorno().add(LineSeparator.BREAK);
		}
	}

	public void setEquacaoGrau2(EquacaoGrau2 equacaoGrau2) {
		this.equacaoGrau2 = equacaoGrau2;
	}
	
	public void addMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
