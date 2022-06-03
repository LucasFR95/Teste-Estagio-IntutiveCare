package br.com.controller;

import br.com.webscraper.WebScraper;

public class Controller {

	public static void main(String[] args) {
		//Esse programa cria uma pasta e armazena os anexos junto com eles zipados.
		String url = "https://www.gov.br/ans/pt-br/assuntos/consumidor/o-que-o-seu-plano-de-saude-deve-cobrir-1/o-que-e-o-rol-de-procedimentos-e-evento-em-saude";
		WebScraper webscraper = new WebScraper();
	    webscraper.filesDownload(url);

	}

}
