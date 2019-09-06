package br.com.furb.lista5;

import br.com.furb.SimpleScreen;
import javafx.fxml.FXML;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Questao1 extends SimpleScreen {

	public Questao1() {
		super("/views/Lista5Q1.fxml", "Lista 5 - Questão 1");
	}

	@FXML
	private void selectFile() {
		log.info("Selecionando arquivo");
	}
}
