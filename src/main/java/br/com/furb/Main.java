package br.com.furb;

import br.com.furb.trabalho1.Trabalho1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		loadSimpleScreen(new Trabalho1(), primaryStage);
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
			screen.setStage(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
