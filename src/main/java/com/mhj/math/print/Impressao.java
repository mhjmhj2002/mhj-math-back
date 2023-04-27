package com.mhj.math.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.mhj.math.data.interfaces.Data;
import com.mhj.math.enums.HtmlTag;
import com.mhj.math.enums.Simbolo;
import com.mhj.math.enums.TagPropertie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Impressao {

	public void imprimirHTML(String path, String file, List<Data> retorno) {
		File arquivo = new File(path + file + ".html");
		PrintWriter saida = null;
		StringBuilder linhasHtml = new StringBuilder();
		try {
			linhasHtml.append(HtmlTag.HTML_OPEN.getTag());
			linhasHtml = addHeader(linhasHtml);
			linhasHtml.append(HtmlTag.BODY_OPEN.getTag());
			linhasHtml.append("<div class=\"text-center\">");

			for (Data linha : retorno) {
				linhasHtml.append(linha.getHtml());
			}

			log.info(linhasHtml.toString());

			linhasHtml.append("</div>");
			linhasHtml.append(HtmlTag.BODY_CLOSE.getTag());
			linhasHtml.append(HtmlTag.HTML_CLOSE.getTag());

			saida = new PrintWriter(arquivo, "UTF-8");
			saida.print(linhasHtml.toString());

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			log.error("Erro de escrita: ", e);
		} finally {
			if (saida != null) {
				saida.close();
			}
		}
	}

	private StringBuilder addHeader(StringBuilder linhasHtml) {
		linhasHtml.append(HtmlTag.HEAD_OPEN.getHtml());
		linhasHtml.append(Simbolo.MENOR.getHtml());
		linhasHtml.append("SCRIPT");
		linhasHtml.append(TagPropertie.TEXT_JAVASCRIPT.getHtml());
		linhasHtml.append(TagPropertie.IMPORT_MATHJAX.getHtml());
		linhasHtml.append(Simbolo.MAIOR.getHtml());
		linhasHtml.append(HtmlTag.SCRIPT_CLOSE.getHtml());
		linhasHtml.append(
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css\" integrity=\"sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ\" crossorigin=\"anonymous\">");
		linhasHtml.append(
				"<script src=\"https://code.jquery.com/jquery-3.1.1.slim.min.js\" integrity=\"sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n\" crossorigin=\"anonymous\"></script>");
		linhasHtml.append(
				"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js\" integrity=\"sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb\" crossorigin=\"anonymous\"></script>");
		linhasHtml.append(
				"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js\" integrity=\"sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn\" crossorigin=\"anonymous\"></script>");
		linhasHtml.append(HtmlTag.HEAD_CLOSE.getHtml());
		return linhasHtml;
	}

	public void imprimirTexto(String path, String file, List<Data> retorno) {
		File arquivo = new File(path + file + ".txt");
		PrintWriter saida = null;
		StringBuilder linhasTexto = new StringBuilder();
		try {

			for (Data linha : retorno) {
				linhasTexto.append(linha.getTexto());
			}

			log.info(linhasTexto.toString());

			saida = new PrintWriter(arquivo, "UTF-8");
			saida.print(linhasTexto.toString());

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			log.error("Erro de escrita: ", e);
		} finally {
			if (saida != null) {
				saida.close();
			}
		}
	}

	public static String getHTML(List<Data> retorno) {
		StringBuilder linhasHtml = new StringBuilder();
		linhasHtml.append("<div class=\"text-center\">");

		for (Data linha : retorno) {
			linhasHtml.append(linha.getHtml());
		}

		log.info(linhasHtml.toString());

		linhasHtml.append("</div>");

		return linhasHtml.toString();

	}

}
