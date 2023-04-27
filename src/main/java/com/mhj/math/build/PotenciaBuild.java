package com.mhj.math.build;

import java.util.List;

import com.mhj.math.data.Descricao;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Potenciacao;
import com.mhj.math.util.OperacaoUtil;

public abstract class PotenciaBuild extends Build {

	List<Potenciacao> potencias;

	public PotenciaBuild() {
		super();
	}

	public PotenciaBuild(List<Potenciacao> potencias) {
		super();
		this.potencias = potencias;
	}

	public List<Potenciacao> getPotencias() {
		return potencias;
	}
	
	public void setPotencias(List<Potenciacao> potencias) {
		this.potencias = potencias;
	}

	public void montaPotencia(Potenciacao potencia, boolean mostraSinal){
		montaPotencia(potencia, mostraSinal, true);
	}
	
	public void montaPotencia(Potenciacao potencia, boolean mostraSinal, boolean addNumerador){
		operacao.getRetorno().add(MathjaxTag.MSUP_OPEN);
		operacao.getRetorno().add(MathjaxTag.MROW_OPEN);
		
		if (mostraSinal) {
			if (potencia.getBase().getValor() > 0) {
				montaSinal(potencia.getBase());
			}
		} 
		
		operacao.getRetorno().add(MathjaxTag.MN_OPEN);
		operacao.getRetorno().add(potencia.getBase());
		operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
		
		operacao.getRetorno().add(MathjaxTag.MROW_CLOSE);
	
		operacao.getRetorno().add(MathjaxTag.MN_OPEN);
		operacao.getRetorno().add(potencia.getExpoente());
		operacao.getRetorno().add(MathjaxTag.MN_CLOSE);
		operacao.getRetorno().add(MathjaxTag.MSUP_CLOSE);
	}

	protected void validarExpoenteUm(Potenciacao potencia) {
		if (OperacaoUtil.validarValor(potencia.getExpoente(), 1)) {
			
			operacao.getRetorno().add(new Descricao("Potencia de expoente 1, então: "));
			
			abreMath();
			
			montaPotencia(potencia, false);
			
			operacao.getRetorno().add(MathjaxTag.MTEXT_OPEN);
			operacao.getRetorno().add(new Descricao(" = " + potencia.getBase().getValor()));
			operacao.getRetorno().add(MathjaxTag.MTEXT_CLOSE);
			
			fechaMath();
			
		}
		
	}

	protected void validarExpoenteZero(Potenciacao potencia) throws RegraException {
		if (OperacaoUtil.validarValor(potencia.getExpoente(), 0)) {
			
			operacao.getRetorno().add(new Descricao("Potencia de expoente 0, então: "));
			
			abreMath();
			
			montaPotencia(potencia, false);
			
			String numero = "1";
			if (potencia.getBase().getValor() < 0) {
				numero = "-1";
			}
			
			operacao.getRetorno().add(MathjaxTag.MTEXT_OPEN);
			operacao.getRetorno().add(new Descricao(" = " + numero));
			operacao.getRetorno().add(MathjaxTag.MTEXT_CLOSE);
			
			fechaMath();
			
		}
	}
	
	protected void validarExpoenteNegativo() {
		
	}

}
