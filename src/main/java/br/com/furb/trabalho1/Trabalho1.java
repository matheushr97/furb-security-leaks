package br.com.furb.trabalho1;

import br.com.furb.AlertUtil;
import br.com.furb.SimpleScreen;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.NumberStringConverter;

public class Trabalho1 extends SimpleScreen {

	private boolean vulnerable = true;

	@FXML
	private ChoiceBox securityChoiceBox;

	@FXML
	private TextField quantidadeTextField;

	@FXML
	private Text contadorTxt;

	private LongProperty contadorProperty;

	private static final long MAX_COUNT = 1000;

	public Trabalho1() {
		super("/views/Trabalho1.fxml", "Trabalho 1 - CWE 178 | 179 | 606");
	}

	@FXML
	private void initialize() {
		String securityOptions[] = {"Vulnerável", "Segura"};

		securityChoiceBox.setItems(FXCollections.observableArrayList(securityOptions));
		securityChoiceBox.getSelectionModel().select(0);
		securityChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> vulnerable = !newValue.equals("Segura"));

		quantidadeTextField.setTextFormatter(
				new TextFormatter<Long>(new LongStringConverter(), 0l));

		contadorProperty = new SimpleLongProperty(0);
		contadorTxt.textProperty().bindBidirectional(contadorProperty, new NumberStringConverter());
	}

	@FXML
	public void processarContagem() {
		Long quantidade = Long.valueOf(quantidadeTextField.getText());
		if (vulnerable) {
			contagem(quantidade);
		} else {
			contagemSegura(quantidade);
		}
		System.out.println(contadorProperty.getValue());
	}

	private void contagemSegura(Long quantidade) {
		if (quantidade > MAX_COUNT) {
			AlertUtil.error(String.format("Valor excede o número máximo de %d contagens", MAX_COUNT)).show();
		} else {
			contagem(quantidade);
		}
	}

	private void contagem(Long quantidade) {
		for (long i = 0; i < quantidade; i++) {
			contadorProperty.setValue(contadorProperty.add(1).get());
		}
	}

}
