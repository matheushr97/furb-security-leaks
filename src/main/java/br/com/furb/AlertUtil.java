package br.com.furb;

import javafx.scene.control.Alert;

public class AlertUtil {

	private static final String DEFAULT_INFO_TITLE = "Informação";
	private static final String DEFAULT_WARNING_TITLE = "Atenção";
	private static final String DEFAULT_ERROR_TITLE = "Erro";

	public static Alert info(String contentText) {
		return info(contentText, DEFAULT_INFO_TITLE);
	}

	public static Alert info(String contentText, String title) {
		return info(contentText, title, null);
	}

	public static Alert info(String contentText, String title, String headerText) {
		return make(Alert.AlertType.INFORMATION, contentText, title, headerText);
	}

	public static Alert warning(String contentText) {
		return warning(contentText, DEFAULT_WARNING_TITLE);
	}

	public static Alert warning(String contentText, String title) {
		return warning(contentText, title, null);
	}

	public static Alert warning(String contentText, String title, String headerText) {
		return make(Alert.AlertType.WARNING, contentText, title, headerText);
	}

	public static Alert error(String contentText) {
		return error(contentText, DEFAULT_ERROR_TITLE);
	}

	public static Alert error(String contentText, String title) {
		return error(contentText, title, null);
	}

	public static Alert error(String contentText, String title, String headerText) {
		return make(Alert.AlertType.ERROR, contentText, title, headerText);
	}

	public static Alert make(Alert.AlertType alertType, String contentText, String title, String headerText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		return alert;
	}
}
