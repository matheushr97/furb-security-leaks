package br.com.furb.trabalho1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.furb.AlertUtil;
import br.com.furb.SimpleScreen;
import javafx.beans.property.LongProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.NumberStringConverter;

public class Trabalho1 extends SimpleScreen {

	private boolean vulnerable = true;

	@FXML
	private TableView table;

	@FXML
	private TableColumn nameCol;
	
	@FXML
	private TextField nameTextField;

	@FXML
	private ChoiceBox securityChoiceBox;

	@FXML
	private TextField quantidadeTextField;

	@FXML
	private Text contadorTxt;

	private LongProperty contadorProperty;

	private static final long MAX_COUNT = 1000;

	private ObservableList<Object> observableArrayList;

	public Trabalho1() {
		super("/views/Trabalho1.fxml", "Trabalho 1 - CWE 178 | 179 | 606");
	}

	@FXML
	private void initialize() {
		String securityOptions[] = { "Vulnerável", "Segura" };

		securityChoiceBox.setItems(FXCollections.observableArrayList(securityOptions));
		securityChoiceBox.getSelectionModel().select(0);
		securityChoiceBox.valueProperty()
				.addListener((observable, oldValue, newValue) -> vulnerable = !newValue.equals("Segura"));

		quantidadeTextField.setTextFormatter(new TextFormatter<Long>(new LongStringConverter(), 0l));

		contadorProperty = new SimpleLongProperty(0);
		contadorTxt.textProperty().bindBidirectional(contadorProperty, new NumberStringConverter());
		

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        
        observableArrayList = FXCollections.observableArrayList();

		table.setItems(observableArrayList);
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

	@FXML
	private void cadastrarNome() {
		String nome = nameTextField.getText();
		if (vulnerable) {
			cadastroVulnerable(nome);
		} else {
			cadastroSeguro(nome);
		}
	}

	private void cadastroVulnerable(String nome) {
		cadastro(nome, Pattern.compile("\\<script\\>"));
	}

	private void cadastro(String nome, Pattern regex) {
		Matcher matcher = regex.matcher(nome);
		if(matcher.find()) {
			AlertUtil.error("Nome n�o pode conter tags <script>").show();
		} else {
			observableArrayList.add(new Person(nome));
		}
	}

	private void cadastroSeguro(String nome) {
		cadastro(nome, Pattern.compile("\\<script\\>", Pattern.CASE_INSENSITIVE));
	}

}
