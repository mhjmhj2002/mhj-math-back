package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Tag;

public enum MathjaxTag implements Tag {
	
	MATH_OPEN(1,"<MATH>"),
	MATH_CLOSE(2,"</MATH>"),
	MI_OPEN(3,"<MI>"),
	MI_CLOSE(4,"</MI>"),
	MO_OPEN(5,"<MO>"),
	MO_CLOSE(6,"</MO>"),
	MROW_OPEN(7,"<MROW>"),
	MROW_CLOSE(8,"</MROW>"),
	MFRAC_OPEN(9,"<MFRAC>"),
	MFRAC_CLOSE(10,"</MFRAC>"),
	MSQRT_OPEN(11,"<MSQRT>"),
	MSQRT_CLOSE(12,"</MSQRT>"),
	MSUP_OPEN(13,"<MSUP>"),
	MSUP_CLOSE(14,"</MSUP>"),
	MN_OPEN(15,"<MN>"),
	MN_CLOSE(16,"</MN>"), 
	MTEXT_OPEN(17, "<MTEXT>"), 
	MTEXT_CLOSE(18, "</MTEXT>"), 
	MTABLE_OPEN(19, "<MTABLE>"), 
	MTABLE_CLOSE(20, "</MTABLE>"),
	MTD_OPEN(21, "<MTD>"),
	MTD_CLOSE(22, "</MTD>"), 
	MTR_OPEN(23, "<MTR>"), 
	MTR_CLOSE(24, "</MTR>");
	
	private int codigo;
	
	private String html;

	private MathjaxTag(int codigo, String tag) {
		this.codigo = codigo;
		this.html = tag;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTag() {
		return html;
	}

	@Override
	public String getHtml() {
		return this.html;
	}

	@Override
	public String getTexto() {
		return null;
	}

}