package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        //primaryStage.initStyle(StageStyle.UNDECORATED); // usuwa pasek z minimalizacją i krzyżykiem
        primaryStage.setTitle("Login Form");
        primaryStage.setScene(new Scene(root, 600, 400)); // skopiowane z programu
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}

