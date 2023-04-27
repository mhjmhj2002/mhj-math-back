package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Expressao;
import com.mhj.math.data.interfaces.Tag;

public enum LineSeparator implements Expressao{
	
	BREAK(1, HtmlTag.BR, System.lineSeparator());
	
	private Integer codigo;
	
	private Tag html;
	
	private String texto;

	private LineSeparator(Integer codigo, HtmlTag html, String texto) {
		this.codigo = codigo;
		this.html = html;
		this.texto = texto;
	}

	@Override
	public String getHtml() {
		return html.getHtml();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getLineSeparator() {
		return texto;
	}

	@Override
	public String getTexto() {
		return texto;
	}
	
	

}
