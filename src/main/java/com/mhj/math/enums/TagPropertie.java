package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Expressao;

public enum TagPropertie implements Expressao {
	
	CHARSET_UTF_8(1," charset=\"UTF-8\" "),
	TEXT_JAVASCRIPT(2, " type=\"text/javascript\" async "),
	IMPORT_MATHJAX(3, " src=\"https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-MML-AM_CHTML\" ");
	
	private int codigo;
	
	private String html;

	private TagPropertie(int codigo, String tag) {
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
