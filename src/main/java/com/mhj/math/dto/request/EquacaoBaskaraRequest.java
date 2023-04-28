package com.mhj.math.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.mhj.math.enums.Sinal;

import lombok.Data;

@Data
public class EquacaoBaskaraRequest {

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.a}")
	private Integer a;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.b}")
	private Integer b;

	@Valid
	@NotNull(message = "{EquacaoGrau2Build.field.required.equacaoGrau2.c}")
	private Integer c;

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
