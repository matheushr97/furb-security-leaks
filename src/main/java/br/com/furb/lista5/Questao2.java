package br.com.furb.lista5;

import br.com.furb.SimpleScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.UUID;

@Log4j2
public class Questao2 extends SimpleScreen {

	private static final String UPLOAD_DIR = "repositorio/upload";
	private static final String DOWNLOAD_DIR = "repositorio/download";

	@FXML
	private TextField fileNameTextField;

	//Simula o acesso a um arquivo via banco de dados, informando seu UUID.
	private HashMap<UUID, String> filesRepository;

	public Questao2() {
		super("/views/Lista5Q1.fxml", "Lista 5 - Questão 2");
		this.filesRepository = new HashMap<>();
		try {
			Files.createDirectories(Paths.get(DOWNLOAD_DIR));
			Files.createDirectories(Paths.get(UPLOAD_DIR));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void uploadFile() {
		File file = selectFile();

		if (file != null) {
			Path source = Paths.get(file.toURI());
			Path target = Paths.get(UPLOAD_DIR).resolve(file.getName());
			try {
				Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
				UUID uid = UUID.randomUUID();
				filesRepository.put(uid, file.getName());
				showUploadedFileMsg(uid);
			} catch (IOException e) {
				log.error("Falha no upload do arquivo", e);
			}
		}
	}

	@FXML
	public void downloadFile() {
		UUID uuid = fromString(fileNameTextField.getText());

		if (filesRepository.containsKey(uuid)) {
			Path source = Paths.get(UPLOAD_DIR).resolve(filesRepository.get(uuid));
			Path target = Paths.get(DOWNLOAD_DIR).resolve(source.getFileName());
			try {
				Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
				showInfoMessage("Arquivo baixado com sucesso, verifique em seu diretório de downloads.");
			} catch (IOException e) {
				log.error("Falha no download do arquivo", e);
			}
		} else {
			showErrorMessage("O arquivo informado não existe ou não está disponível");
		}

	}

	private static UUID fromString(String value) {
		try {
			return UUID.fromString(value);
		} catch (Exception e) {
			return null;
		}
	}

	private File selectFile() {
		log.info("Selecionando arquivo");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecione o arquivo para enviar");
		return fileChooser.showOpenDialog(this.getStage());
	}

	private void showInfoMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Informação");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	private void showUploadedFileMsg(UUID uuid) {
		TextInputDialog dialog = new TextInputDialog(uuid.toString());
		dialog.setTitle("Informação");
		dialog.setHeaderText("Arquivo enviado com sucesso.");
		dialog.setContentText("Copie seu código de arquivo:");
		dialog.show();
	}

	private void showErrorMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
}
