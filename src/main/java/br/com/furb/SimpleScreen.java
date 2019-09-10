package br.com.furb;

import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class SimpleScreen {

	private String fxmlResource;

	private String title;

	private Stage stage;

	public SimpleScreen(String fxmlResource, String title) {
		this.fxmlResource = fxmlResource;
		this.title = title;
	}
}
