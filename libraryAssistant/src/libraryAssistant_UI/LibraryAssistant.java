package libraryAssistant_UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Michał Dąbrowski
 */

public class LibraryAssistant extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Umożliwa odtworzenie pliku FXMLDocument
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryAssistant.class.getResource("libraryAssistant_UI/FXMLDocument.fxml"));

        // Załadowanie pliku .fxml do javy i przetłumaczanie na obiekt PARENT zrozumiały na javy
        Parent layout = fxmlLoader.load();

        // Stworzenie obiektu sceny
        Scene scene = new Scene(layout);

        // Dodanie sceny do stage
        primaryStage.setScene(scene);
        // Ustawienie tytułu Stage
        primaryStage.setTitle("Library Assistant");
        // Wyświetlenie stage
        primaryStage.show();
    }


    public static void main(String[] args){
        lanuch(args);
    }

    private static void lanuch(String[] args) {
    }
}



