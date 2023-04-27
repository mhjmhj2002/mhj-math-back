package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Value;

public enum MathjaxValue implements Value {
	
	CENTER(1,"center"),
	PT(2, "pt"),
	EM(3, "em"),
	SOLID(4, "solid"),
	NONE(5, "none"),
	LINK_MATHML(6, "http://www.w3.org/1998/Math/MathML"),
	BLOCK(7, "block");
	
	private int codigo;
	
	private String html;

	private MathjaxValue(int codigo, String tag) {
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
