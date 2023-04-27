package com.mhj.math.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mhj.math.dto.FracaoDto;

public class FracaoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FracaoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO adicionar validators
	}

}
