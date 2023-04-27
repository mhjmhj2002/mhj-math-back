package com.mhj.math.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mhj.math.operacao.EquacaoGrau2;

public class EquacaoGrau2Validation implements Validator {
	
	private final String required = "EquacaoGrau2Build.field.required";

	@Override
	public boolean supports(Class<?> clazz) {
		return EquacaoGrau2.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "a", required);
		ValidationUtils.rejectIfEmpty(errors, "b", required);
		ValidationUtils.rejectIfEmpty(errors, "c", required);
		ValidationUtils.rejectIfEmpty(errors, "sinalA", required, "erro sinalA");
		ValidationUtils.rejectIfEmpty(errors, "sinalB", required);
		ValidationUtils.rejectIfEmpty(errors, "sinalC", required);
		
	}
	
}




