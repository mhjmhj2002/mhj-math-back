package com.mhj.math.util;

import java.util.List;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.Inteiro;
import com.mhj.math.enums.Sinal;
import com.mhj.math.operacao.Divisao;
import com.mhj.math.operacao.Fracao;

public class OperacaoUtil {

	public static Inteiro quadrado(Inteiro numero) {
		return new Inteiro(Double.valueOf(Math.pow(numero.getValor(), 2)).intValue());
	}

	public static Integer multiplicacao(List<Integer> numeros) {
		Integer resultado = 1;
		for (Integer numero : numeros) {
			resultado = resultado * numero;
		}
		return resultado;
	}

	public static Descricao getNumeroComSinal(Inteiro numero, boolean exibeSinal) {

		String sinal = "";

		if (exibeSinal) {
			if (Integer.valueOf(0).compareTo(numero.getValor()) > 0) {// negativo
				sinal = "- ";
				return new Descricao(sinal + Math.abs(numero.getValor()));
			} else {
				sinal = "+ ";
				return new Descricao(sinal + Math.abs(numero.getValor()));
			}
		}

		return new Descricao(sinal + numero.getValor());

	}

	public static Inteiro multiplicacao(Inteiro num1, Inteiro num2) {
		return new Inteiro(num1.getValor() * num2.getValor());
	}

	public static Inteiro calculaSomaSubtracao(Inteiro num1, Inteiro num2) {
		return new Inteiro(num1.getValor() + num2.getValor());
	}

	public static Divisao divisao(Inteiro dividendo, Inteiro divisor) {
		Divisao retorno = new Divisao(dividendo, divisor);
		retorno.resolve();
		return retorno;
	}

	public static Fracao simplificarFracao(Fracao fracao) {
		Inteiro mdc = mdc(fracao.getNumerador(), fracao.getDenominador());
		return new Fracao(OperacaoUtil.divisao(fracao.getNumerador(), mdc).getQuociente(), OperacaoUtil.divisao(fracao.getDenominador(), mdc).getQuociente());
	}

	public static Inteiro mdc(Inteiro numero1, Inteiro numero2) {
		if ((numero1.getValor() % numero2.getValor() == 0)) {
			return new Inteiro(Math.abs(numero2.getValor()));
		} else {
			return mdc(numero2, (OperacaoUtil.resto(numero1, numero2)));
		}
	}

	public static Inteiro resto(Inteiro numero1, Inteiro numero2) {
		return new Inteiro(numero1.getValor() % numero2.getValor());
	}

	public static Fracao quadrado(Fracao fracao) {
		Fracao retorno = new Fracao(new Inteiro(1), new Inteiro(1));
		retorno.setNumerador(new Inteiro(Double.valueOf(Math.pow(fracao.getNumerador().getValor(), 2)).intValue()));
		retorno.setDenominador(new Inteiro(Double.valueOf(Math.pow(fracao.getDenominador().getValor(), 2)).intValue()));
		return retorno;
	}

	public static Fracao soma(Fracao fracao1, Fracao fracao2) {
		Fracao retorno = new Fracao(new Inteiro(1), new Inteiro(1));
		retorno.setNumerador(OperacaoUtil.calculaSomaSubtracao(OperacaoUtil.multiplicacao(fracao1.getNumerador(), fracao2.getDenominador()), OperacaoUtil.multiplicacao(fracao1.getNumerador(), fracao2.getNumerador())));
		retorno.setDenominador(OperacaoUtil.multiplicacao(fracao1.getDenominador(), fracao2.getDenominador()));
		return retorno;
	}

	public static Fracao subtracao(Fracao fracao1, Fracao fracao2) {
		Fracao retorno = new Fracao(new Inteiro(1), new Inteiro(1));
		retorno.setNumerador(OperacaoUtil.calculaSomaSubtracao(OperacaoUtil.multiplicacao(fracao1.getNumerador(), fracao2.getDenominador()), OperacaoUtil.multiplicacao(OperacaoUtil.multiplicacao(fracao1.getNumerador(), fracao2.getNumerador()),new Inteiro(-1))));
		retorno.setDenominador(OperacaoUtil.multiplicacao(fracao1.getDenominador(), fracao2.getDenominador()));
		return retorno;
	}

	public static Fracao multiplicacao(Fracao fracao1, Fracao fracao2) {
		Fracao retorno = new Fracao(new Inteiro(1), new Inteiro(1));
		retorno.setNumerador(OperacaoUtil.multiplicacao(fracao1.getNumerador(), fracao2.getNumerador()));
		retorno.setDenominador(OperacaoUtil.multiplicacao(fracao1.getDenominador(), fracao2.getDenominador()));
		return retorno;
	}

	public static boolean fracaoEhReal(Fracao fracao) {
		return Integer.valueOf(1).equals(fracao.getDenominador().getValor());
	}

	public static Fracao calculaSomaSubtracao(Fracao fracao1, Fracao fracao2) {
		if (new Inteiro(0).compareTo(fracao2.getNumerador()) > 0) {
			return subtracao(fracao1, fracao2);
		} else {
			return soma(fracao1, fracao2);
		}
	}

	public static Sinal getSinalNumero(Inteiro numero) {
		if (Integer.valueOf(0).compareTo(numero.getValor()) > 0) {
			return Sinal.NEGATIVO;
		}
		return Sinal.POSITIVO;
	}

	public static Inteiro raiz2(Inteiro numero) {
		return new Inteiro(Double.valueOf(Math.sqrt(numero.getValor())).intValue());
	}
	
	public static boolean ehPrimo(Inteiro numero) {
		Integer num = numero.getValor();
		if (num < 2) {
			return false;
		}
		
		if (num < 4) {
			return true;
		}
		
		Integer metade = (num / 2) + 1;
		
		for (int i = 2; i < metade; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static Inteiro getMaior(Inteiro num1, Inteiro num2){
		return new Inteiro(num1.getValor() > num2.getValor() ? num1.getValor() : num2.getValor());
	}
	
	public static Inteiro getMenor(Integer num1, Integer num2){
		return new Inteiro(num1 < num2 ? num1 : num2);
	}

	public static boolean ehPar(Inteiro numero) {
		return numero.getValor() % 2 == 0;
	}

	public static boolean ehDivisor(Inteiro numero1, Inteiro numero2) {
		return resto(numero1, numero2).getValor() == 0;
	}

	public static boolean validarDenominadorIgual(List<Fracao> fracoes) {
		Inteiro denominadorIgual = fracoes.get(0).getDenominador();
		
		for (Fracao fracao : fracoes) {
			if (!denominadorIgual.getValor().equals(fracao.getDenominador().getValor())) {
				return false;
			}
		}
		return true;
	}
	
	public Inteiro calculaMDC(Inteiro numero1, Inteiro numero2) {
		Inteiro resto = null;
		while (numero2.getValor() != 0) {
			resto = new Inteiro(numero1.getValor() % numero2.getValor());
			numero1 = numero2;
			numero2 = resto;
		}
		return resto;
	}

	public static Inteiro soma(Inteiro numero1, Inteiro numero2) {
		return new Inteiro(numero1.getValor() + numero2.getValor());
	}
	
	public static boolean validarValor(Inteiro numero, Integer valor) {
		return valor.equals(numero.getValor());
	}


}
