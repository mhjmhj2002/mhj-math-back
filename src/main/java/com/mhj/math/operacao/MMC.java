package com.mhj.math.operacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mhj.math.data.Inteiro;
import com.mhj.math.util.OperacaoUtil;

public class MMC extends Comum{

	private boolean irredutivel;
	
	public MMC(){
		super(new Inteiro(1) , new ArrayList<>());
	}
	
	public MMC(List<Inteiro> numeros, Inteiro resultado){
		super(resultado, numeros);
		this.irredutivel = false;
		adicionarDivisor();
		calcularMmc();
	}
	
	public MMC(List<Inteiro> numeros, Inteiro mmc, boolean irredutivel){
		super(mmc, numeros);
		this.irredutivel = irredutivel;
		adicionarDivisor();
		calcularMmc();
	}

	private void calcularMmc() {
		resultado = OperacaoUtil.multiplicacao(resultado, divisor);
	}

	private void adicionarDivisor() {
		Collections.sort(numeros);
		
		for (Inteiro numero : numeros) {
			if (numero.getValor() == 1) {
				divisor = new Inteiro(1);
				continue;
			}			
			if (OperacaoUtil.ehDivisor(numero, new Inteiro(2))) {
				divisor = new Inteiro(2);
				break;
			}
			if (OperacaoUtil.ehDivisor(numero, new Inteiro(3))) {
				divisor = new Inteiro(3); 
				break;
			}
			if (OperacaoUtil.ehDivisor(numero, new Inteiro(5))) {
				divisor = new Inteiro(5);
				break;
			}
			if (OperacaoUtil.ehDivisor(numero, new Inteiro(7))) {
				divisor = new Inteiro(7);
				break;
			}
			divisor = numero;
			break;
		}
	}

	public boolean isIrredutivel() {
		return irredutivel;
	}

}
