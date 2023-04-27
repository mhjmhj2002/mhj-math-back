package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Expressao;

public enum Tag implements Expressao {
	
	BR(1,"<BR>"),
	HTML_OPEN(2,"<HTML>"),
	HTML_CLOSE(3,"</HTML>"),
	BODY_OPEN(4,"<BODY>"),
	BODY_CLOSE(5,"</BODY>"),
	HEAD_OPEN(6,"<HEAD>"),
	HEAD_CLOSE(7,"</HEAD>"),
	SCRIPT_OPEN(8,"</SCRIPT>"),
	SCRIPT_CLOSE(9,"</SCRIPT>");
	
	private int codigo;
	
	private String html;

	private Tag(int codigo, String tag) {
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
