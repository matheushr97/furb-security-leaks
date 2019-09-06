package br.com.furb;

import br.com.furb.lista5.Questao1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		loadSimpleScreen(new Questao1(), primaryStage);
	}

	private void loadSimpleScreen(SimpleScreen screen, Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(screen.getFxmlResource()));
			fxmlLoader.setController(screen);
			Pane root = (Pane) fxmlLoader.load();
			Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			if (screen.getTitle() != null) {
				primaryStage.setTitle(screen.getTitle());
			}
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
