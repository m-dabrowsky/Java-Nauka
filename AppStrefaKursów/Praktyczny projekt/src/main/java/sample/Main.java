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
        // Załadowanie pliku FXML
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        // Usunięcie paska zadań głównego okna
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // Ustawienie sceny na Stage'u tworząc nową Scene o wyglądzie przedstawionym w pliku FXML, szerokości, wysokości)
        primaryStage.setScene(new Scene(root, 600, 400));
        // Wyświetlenie Stage
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
