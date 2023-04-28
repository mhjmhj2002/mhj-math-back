package com.mhj.math.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mhj.math.build.MDCBuild;
import com.mhj.math.dto.MdcDto;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.MDC;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.print.Impressao;
import com.mhj.math.type.Inteiro;
import com.mhj.math.validation.MDCValidation;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mdc")
@Slf4j
public class MDCController {

	@Autowired
	private MDCBuild mdcBuild;

	@PostMapping( name="calcular_mdc")
	public ResponseEntity<String> calcular(@RequestParam("numeros") List<Integer> numeros, Locale locale) throws BusinessException {
		
		List<Inteiro> nums = new ArrayList<>();
		
		for (int i = 1; i < numeros.size(); i++) {
			nums.add(new Inteiro(numeros.get(i)));			
		}
		
		MDC mdc = new MDC(nums, new Inteiro(1));
		
		mdcBuild.setMdc(mdc);
		mdcBuild.setLocale(locale);
		mdcBuild.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			mdcBuild.resolver();
		} catch (RegraException e) {
			log.error("Erro de regra", e);
		}
		
		Operacao operacao = mdcBuild.getOperacao();

		return new ResponseEntity<>(Impressao.getHTML(operacao.getRetorno()), HttpStatus.OK);
	}

}
