package com.mhj.math.enums;

import com.mhj.math.data.interfaces.Expressao;

public enum Letra implements Expressao{

	A(1, "A", "A"), 
	B(2, "B", "B"), 
	C(3, "C", "C"), 
	D(4, "D", "D"), 
	E(5, "E", "E"), 
	F(6, "F", "F"), 
	G(7, "G", "G"), 
	H(8, "H", "H"), 
	I(9, "I", "I"), 
	J(10, "J", "J"), 
	K(11, "K", "K"), 
	L(12, "L", "L"), 
	M(13, "M", "M"), 
	N(14, "N", "N"), 
	O(15, "O", "O"), 
	P(16, "P", "P"), 
	Q(17, "Q", "Q"), 
	R(18, "R", "R"), 
	S(19, "S", "S"), 
	T(20, "T", "T"), 
	U(21, "U", "U"), 
	V(22, "V", "V"), 
	W(23, "W", "W"), 
	X(24, "X", "X"), 
	Y(25, "Y", "Y"), 
	Z(26, "Z", "Z"),
	a(27, "a", "a"), 
	b(28, "b", "b"), 
	c(29, "c", "c"), 
	d(30, "d", "d"), 
	e(31, "e", "e"), 
	f(32, "f", "f"), 
	g(33, "g", "g"), 
	h(34, "h", "h"), 
	i(35, "i", "i"), 
	j(36, "j", "j"), 
	k(37, "k", "k"), 
	l(38, "l", "l"), 
	m(39, "m", "m"), 
	n(40, "n", "n"), 
	o(41, "o", "o"), 
	p(42, "p", "p"), 
	q(43, "q", "q"), 
	r(44, "r", "r"), 
	s(45, "s", "s"), 
	t(46, "t", "t"), 
	u(47, "u", "u"), 
	v(48, "v", "v"), 
	w(49, "w", "w"), 
	x(50, "x", "x"), 
	y(51, "y", "y"), 
	z(52, "z", "z");

	private int codigo;

	private String html;

	private String texto;

	private Letra(int codigo, String html, String texto) {
		this.codigo = codigo;
		this.html = html;
		this.texto = texto;
	}

	public int getCodigo() {
		return codigo;
	}

	@Override
	public String getHtml() {
		return html;
	}

	public String getTexto() {
		return texto;
	}

	public static Letra getByCodigo(final int codigo) {
		for (Letra e : values()) {
			if (e.getCodigo() == codigo) {
				return e;
			}
		}
		return null;
	}

	public static Letra getByHtml(final String html) {
		for (Letra e : values()) {
			if (e.getHtml().equals(html)) {
				return e;
			}
		}
		return null;
	}
}
