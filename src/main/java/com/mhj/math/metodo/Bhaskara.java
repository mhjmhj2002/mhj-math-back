package com.mhj.math.metodo;

import java.util.List;

import com.mhj.math.enums.Letra;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.type.Descricao;
import com.mhj.math.type.Inteiro;
import com.mhj.math.type.interfaces.Data;
import com.mhj.math.util.MathjaxUtil;

public class Bhaskara extends Metodo {

	// x = (-b +- sqrt(b^2 - 4ac)) / (2a)

	/**
	 * 
	 */
	private static final long serialVersionUID = -8158535186241635898L;
	private List<Data> formula;

	public Bhaskara() {
		super();
		preencheFormulaHtml();
	}

	@Override
	public String getNome() {
		return "Bhaskara";
	}

	@Override
	public List<Data> getFormula() {
		return formula;
	}

	private void preencheFormulaHtml() {
		formula = MathjaxUtil.abreMath();
		formula.add(MathjaxTag.MI_OPEN);
		formula.add(Letra.x);
		formula.add(MathjaxTag.MI_CLOSE);
		formula.add(MathjaxTag.MO_OPEN);
		formula.add(Simbolo.IGUAL);
		formula.add(MathjaxTag.MO_CLOSE);
		
		formula.add(MathjaxTag.MROW_OPEN);
		formula.add(MathjaxTag.MFRAC_OPEN);
		formula.add(MathjaxTag.MROW_OPEN);
		
		formula.add(MathjaxTag.MO_OPEN);
		formula.add(new Descricao("&#x2212;"));
		formula.add(MathjaxTag.MO_CLOSE);

		formula.add(MathjaxTag.MI_OPEN);
		formula.add(Letra.b);
		formula.add(MathjaxTag.MI_CLOSE);

		formula.add(MathjaxTag.MO_OPEN);
		formula.add(new Descricao("&#x00B1;"));
		formula.add(MathjaxTag.MO_CLOSE);
		
		formula.add(MathjaxTag.MSQRT_OPEN);
		
		formula.add(MathjaxTag.MSUP_OPEN);
		formula.add(MathjaxTag.MI_OPEN);
		formula.add(Letra.b);
		formula.add(MathjaxTag.MI_CLOSE);
		formula.add(MathjaxTag.MN_OPEN);
		formula.add(new Inteiro(2));
		formula.add(MathjaxTag.MN_CLOSE);
		formula.add(MathjaxTag.MSUP_CLOSE);

		formula.add(MathjaxTag.MN_OPEN);
		formula.add(new Inteiro(-4));
		formula.add(MathjaxTag.MN_CLOSE);
		formula.add(MathjaxTag.MI_OPEN);
		formula.add(Letra.a);
		formula.add(MathjaxTag.MI_CLOSE);
		formula.add(MathjaxTag.MI_OPEN);
		formula.add(Letra.c);
		formula.add(MathjaxTag.MI_CLOSE);
		
		formula.add(MathjaxTag.MSQRT_CLOSE);
		formula.add(MathjaxTag.MROW_CLOSE);
		
		formula.add(MathjaxTag.MROW_OPEN);
		formula.add(MathjaxTag.MN_OPEN);
		formula.add(new Inteiro(2));
		formula.add(MathjaxTag.MN_CLOSE);
		formula.add(MathjaxTag.MI_OPEN);
		formula.add(Letra.a);
		formula.add(MathjaxTag.MI_CLOSE);
		formula.add(MathjaxTag.MROW_CLOSE);
		
		formula.add(MathjaxTag.MFRAC_CLOSE);
		formula.add(MathjaxTag.MROW_CLOSE);
		
		formula.add(MathjaxTag.MTEXT_OPEN);
		formula.add(Simbolo.PONTO);
		formula.add(MathjaxTag.MTEXT_CLOSE);
		formula.add(MathjaxTag.MATH_CLOSE);
		
	}

}
