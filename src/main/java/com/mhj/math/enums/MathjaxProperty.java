package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Property;

public enum MathjaxProperty implements Property {
	
	XMLNS(1,"xmlns"),
	DISPLAY(2, "display"),
	COLUMN_ALIGN(3, "columnalign"),
	ROW_SPACING(4, "rowspacing"),
	COLUMN_SPACING(5, "columnspacing"),
	ROW_LINES(6, "rowlines"),
	COLUMN_LINES(7, "columnlines");
	
	private int codigo;
	
	private String html;

	private MathjaxProperty(int codigo, String tag) {
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
