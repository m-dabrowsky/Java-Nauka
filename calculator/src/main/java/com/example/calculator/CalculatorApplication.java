package com.example.calculator;

import com.example.calculator.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.ldap.Control;


public class CalculatorApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Załadowanie pliku FXML
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));

		// Utworzenie Sceny
		Scene scene = new Scene(root,460,600);

		// Usunięcie paska zadań głównego okna
		primaryStage.initStyle(StageStyle.UNDECORATED);

		// Zablokowanie powiększania okna
		primaryStage.setResizable(false);


		// Ustawienie Sceny na Stage root
		primaryStage.setScene(scene);


		// Wyświetlenie Stage
		primaryStage.show();



	}

	public static void main(String[] args) {
		launch(args);
	}

}
