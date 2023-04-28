package com.mhj.math.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mhj.math.build.PotenciaSomaBuild;
import com.mhj.math.data.Inteiro;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.operacao.Potenciacao;
import com.mhj.math.print.Impressao;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/potencia")
@Slf4j
public class PotenciaController {
	
	@Autowired
	private PotenciaSomaBuild potenciaSomaBuild;

	@PostMapping( name = "calcular_ss_potencia", value="calcular_ss_potencia")
	public ResponseEntity<String> calcularSoma(@RequestParam("bases") List<Integer> bases, @RequestParam("expoentes") List<Integer> expoentes, @RequestParam("sinaisExpoente") List<String> sinaisExpoente, @RequestParam("sinaisBase") List<String> sinaisBase, Locale locale)
			throws BusinessException {

		List<Inteiro> baseList = new ArrayList<>();
		List<Inteiro> expList = new ArrayList<>();
		List<Potenciacao> potencias = new ArrayList<>();

		for (int i = 1; i < expoentes.size(); i++) {
			expList.add(new Inteiro(Integer.valueOf(sinaisExpoente.get(i) + expoentes.get(i))));
		}
		
		for (int i = 1; i < bases.size(); i++) {
			baseList.add(new Inteiro(Integer.valueOf(sinaisBase.get(i) + bases.get(i))));
		}
		
		int pos = 0;
		for (Inteiro num : baseList) {
			Potenciacao potencia = new Potenciacao(num, expList.get(pos));
			potencias.add(potencia);
			pos++;
		}
		
		potenciaSomaBuild.setPotencias(potencias);
		potenciaSomaBuild.setLocale(locale);
		potenciaSomaBuild.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			potenciaSomaBuild.resolver();
		} catch (RegraException e) {
			log.error("Erro de regra");
		}
		Operacao operacao = potenciaSomaBuild.getOperacao();

		ModelAndView modelAndView = new ModelAndView("math/ef2/8ano/potencia_resultado");
		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

		return new ResponseEntity<>(Impressao.getHTML(operacao.getRetorno()), HttpStatus.OK);
	}

}
