package com.mhj.math.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.mhj.math.enums.Sinal;
import com.mhj.math.type.Descricao;

public class EquacaoBaskaraRequest {

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.a}")
	private Descricao a;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.b}")
	private Descricao b;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.c}")
	private Descricao c;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.sinalA}")
	private Sinal sinalA;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.sinalB}")
	private Sinal sinalB;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.sinalC}")
	private Sinal sinalC;

}
