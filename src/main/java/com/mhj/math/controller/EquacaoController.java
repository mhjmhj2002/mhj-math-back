package com.mhj.math.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhj.math.build.EquacaoGrau2Build;
import com.mhj.math.dto.request.EquacaoBaskaraRequest;
import com.mhj.math.enums.Letra;
import com.mhj.math.enums.Sinal;
import com.mhj.math.metodo.Bhaskara;
import com.mhj.math.operacao.EquacaoGrau2;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.print.Impressao;
import com.mhj.math.type.Descricao;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/equacao-baskara")
public class EquacaoController {

	@Autowired
	private EquacaoGrau2Build equacaoGrau2Build;
	
	@PostMapping
	public ResponseEntity<String> calcular(@RequestBody @Valid EquacaoBaskaraRequest equacaoBaskara, Locale locale) {

		EquacaoGrau2 equacaoGrau2 = new EquacaoGrau2();
		
		equacaoGrau2.setA(new Descricao(equacaoBaskara.getA().toString()));
		equacaoGrau2.setB(new Descricao(equacaoBaskara.getB().toString()));
		equacaoGrau2.setC(new Descricao(equacaoBaskara.getC().toString()));
		equacaoGrau2.setSinalA(equacaoBaskara.getSinalA());
		equacaoGrau2.setSinalB(equacaoBaskara.getSinalB());
		equacaoGrau2.setSinalC(equacaoBaskara.getSinalC());
		
		equacaoGrau2.setVariavel(Letra.X);
		equacaoGrau2.setResultado(new Descricao("0"));
		equacaoGrau2.setSinalResultado(Sinal.POSITIVO);
		equacaoGrau2.setMetodo(new Bhaskara());

		equacaoGrau2Build.setEquacaoGrau2(equacaoGrau2);
		equacaoGrau2Build.setLocale(locale);
		equacaoGrau2Build.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			equacaoGrau2Build.resolver();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		Operacao operacao = equacaoGrau2Build.getOperacao();

		return new ResponseEntity<>(Impressao.getHTML(operacao.getRetorno()), HttpStatus.OK);
	}

}
