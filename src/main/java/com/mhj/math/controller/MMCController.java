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

import com.mhj.math.build.MMCBuild;
import com.mhj.math.exception.BusinessException;
import com.mhj.math.exception.RegraException;
import com.mhj.math.operacao.MMC;
import com.mhj.math.operacao.Operacao;
import com.mhj.math.print.Impressao;
import com.mhj.math.type.Inteiro;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mmc")
@Slf4j
public class MMCController {

	@Autowired
	private MMCBuild mmcBuild;

	@PostMapping( name = "calcular_mmc")
	public ResponseEntity<String> calcular(@RequestParam("numeros") List<Integer> numeros, Locale locale)
			throws BusinessException {

		List<Inteiro> nums = new ArrayList<>();

		for (int i = 0; i < numeros.size(); i++) {
			nums.add(new Inteiro(numeros.get(i)));
		}

		MMC mmc = new MMC(nums, new Inteiro(1));

		mmcBuild.setMmc(mmc);
		mmcBuild.setLocale(locale);
		mmcBuild.setOperacao(new Operacao(new ArrayList<>(), new ArrayList<>()));

		try {
			mmcBuild.resolver();
		} catch (RegraException e) {
			log.error("Erro de regra: ", e);
		}
		
		Operacao operacao = mmcBuild.getOperacao();

		return new ResponseEntity<>(Impressao.getHTML(operacao.getRetorno()), HttpStatus.OK);
	}

}
