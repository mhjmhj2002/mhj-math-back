package com.mhj.math.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mhj.math.build.FracaoDivisaoBuild;
import com.mhj.math.build.FracaoMultiplicacaoBuild;
import com.mhj.math.build.FracaoSimplificacaoBuild;
import com.mhj.math.build.FracaoSomaBuild;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.Fracao;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.print.Impressao;
import com.mhj.math.type.Inteiro;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/fracao")
public class FracaoController {
	
	@Autowired
	private FracaoSomaBuild fracaoBuildSoma;

	@Autowired
	private FracaoMultiplicacaoBuild fracaoBuildMultiplicacao;

	@Autowired
	private FracaoDivisaoBuild fracaoBuildDivisao;
	
	@Autowired
	private FracaoSimplificacaoBuild fracaoBuildSimplificacao;

	@PostMapping( name = "calcular_ss_fracao", value="calcular_ss_fracao")
	public ResponseEntity<String> calcularSoma(@RequestParam("numeradores") List<Integer> numeradores, @RequestParam("denominadores") List<Integer> denominadores, @RequestParam("sinais") List<String> sinais, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();
		List<Inteiro> denoms = new ArrayList<>();
		List<Fracao> fracoes = new ArrayList<>();

		for (int i = 0; i < numeradores.size(); i++) {
			nums.add(new Inteiro(Integer.valueOf(sinais.get(i) + numeradores.get(i))));
		}
		
		for (int i = 0; i < denominadores.size(); i++) {
			denoms.add(new Inteiro(denominadores.get(i)));
		}
		
		int pos = 0;
		for (Inteiro num : nums) {
			Fracao fracao = new Fracao(num, denoms.get(pos));
			fracoes.add(fracao);
			pos++;
		}
		
		fracaoBuildSoma.setFracoes(fracoes);
		fracaoBuildSoma.setLocale(locale);
		fracaoBuildSoma.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			fracaoBuildSoma.resolver();
		} catch (RegraException e) {
		}
		Operacao operacao = fracaoBuildSoma.getOperacao();

		return new ResponseEntity<>(Impressao.getHTML(operacao.getRetorno()), HttpStatus.OK);
	}

	@PostMapping( name = "calcular_mult_fracao", value="calcular_mult_fracao")
	public ResponseEntity<String> calcularMultiplicacao(@RequestParam("numeradores") List<Integer> numeradores, @RequestParam("denominadores") List<Integer> denominadores, @RequestParam("sinais") List<String> sinais, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();
		List<Inteiro> denoms = new ArrayList<>();
		List<Fracao> fracoes = new ArrayList<>();

		for (int i = 1; i < numeradores.size(); i++) {
			nums.add(new Inteiro(Integer.valueOf(sinais.get(i) + numeradores.get(i))));
		}
		
		for (int i = 1; i < denominadores.size(); i++) {
			denoms.add(new Inteiro(denominadores.get(i)));
		}
		
		int pos = 0;
		for (Inteiro num : nums) {
			Fracao fracao = new Fracao(num, denoms.get(pos));
			fracoes.add(fracao);
			pos++;
		}
		
		fracaoBuildMultiplicacao.setFracoes(fracoes);
		fracaoBuildMultiplicacao.setLocale(locale);
		fracaoBuildMultiplicacao.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			fracaoBuildMultiplicacao.resolver();
		} catch (RegraException e) {
		}
		Operacao operacao = fracaoBuildMultiplicacao.getOperacao();

		return new ResponseEntity<>(Impressao.getHTML(operacao.getRetorno()), HttpStatus.OK);
	}

	@PostMapping( name = "calcular_div_fracao", value="calcular_div_fracao")
	public ResponseEntity<String> calcularDivisao(@RequestParam("numeradores") List<Integer> numeradores, @RequestParam("denominadores") List<Integer> denominadores, @RequestParam("sinais") List<String> sinais, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();
		List<Inteiro> denoms = new ArrayList<>();
		List<Fracao> fracoes = new ArrayList<>();

		for (int i = 1; i < numeradores.size(); i++) {
			nums.add(new Inteiro(Integer.valueOf(sinais.get(i) + numeradores.get(i))));
		}
		
		for (int i = 1; i < denominadores.size(); i++) {
			denoms.add(new Inteiro(denominadores.get(i)));
		}
		
		int pos = 0;
		for (Inteiro num : nums) {
			Fracao fracao = new Fracao(num, denoms.get(pos));
			fracoes.add(fracao);
			pos++;
		}
		
		fracaoBuildDivisao.setFracoes(fracoes);
		fracaoBuildDivisao.setLocale(locale);
		fracaoBuildDivisao.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			fracaoBuildDivisao.resolver();
		} catch (RegraException e) {
		}
		Operacao operacao = fracaoBuildDivisao.getOperacao();

		return new ResponseEntity<>(Impressao.getHTML(operacao.getRetorno()), HttpStatus.OK);
	}

	@PostMapping( name = "calcular_simpl_fracao", value="calcular_simpl_fracao")
	public ResponseEntity<String> calcularSimplificacao(@RequestParam("numeradores") List<Integer> numeradores, @RequestParam("denominadores") List<Integer> denominadores, @RequestParam("sinais") List<String> sinais, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();
		List<Inteiro> denoms = new ArrayList<>();
		List<Fracao> fracoes = new ArrayList<>();

		for (int i = 1; i < numeradores.size(); i++) {
			nums.add(new Inteiro(Integer.valueOf(sinais.get(i) + numeradores.get(i))));
		}
		
		for (int i = 1; i < denominadores.size(); i++) {
			denoms.add(new Inteiro(denominadores.get(i)));
		}
		
		int pos = 0;
		for (Inteiro num : nums) {
			Fracao fracao = new Fracao(num, denoms.get(pos));
			fracoes.add(fracao);
			pos++;
		}
		
		fracaoBuildSimplificacao.setFracao(fracoes.get(0));
		fracaoBuildSimplificacao.setLocale(locale);
		fracaoBuildSimplificacao.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			fracaoBuildSimplificacao.resolver();
		} catch (RegraException e) {
		}
		Operacao operacao = fracaoBuildSimplificacao.getOperacao();

		return new ResponseEntity<>(Impressao.getHTML(operacao.getRetorno()), HttpStatus.OK);
	}

//	private List<Potencia> addPotenciaList() {
//		return Potencia.getPotenciaList();
//	}

}
