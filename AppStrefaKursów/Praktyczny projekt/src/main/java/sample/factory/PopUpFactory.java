package sample.factory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.handler.InfoPopupOkHandler;


public class PopUpFactory {

    // Metoda wyświetlająca okno oczekiwania Popup z tekstem wpisanym w polu text
    public Stage createWaitingPopUp(String text){
        Stage stage = new Stage();                      // Stworzenie nowego okna czyli Stage
        stage.initStyle(StageStyle.UNDECORATED);        // usunięcie z Popup paska zadań

        VBox pane = new VBox();                         // Utworzenie obiektu VBox żeby text i pasek ładowania były jedno pod drugim
        pane.setStyle(waitingPopUpStyle());             // ustawienie stylu dla okna (CSS)
        pane.setAlignment(Pos.CENTER);                  // przekazywane elementy mają być na środku
        pane.setSpacing(10);                            // Odstęp pomiędzy napisem a paskiem ładowania


        Label label = new Label(text);                  // Utworzeniu obiektu Label do wyświetlenie tekstu w oknie popUp
        label.setStyle(waitingLabelStyle());            // ustawienie stylu dla tekstu (CSS)

        ProgressBar progressBar = new ProgressBar();    // Utworzenie obiektu ProgressBar(pasek postępu)

        pane.getChildren().addAll(progressBar, label);  // Dodanie do kontenera VBox dzieci - czyli Label i progressBar. AddAll - umożliwia wpisanie po przecinku


        // Stworzenie sceny, o kontenerze VBox o rozmiarach 200x100
        stage.setScene(new Scene(pane, 200, 100));

        // Zablokowanie okna PopUp
        stage.initModality(Modality.APPLICATION_MODAL);

        return stage;
    }


    /**
     * Metody określające style dla tekstu i okna PopUp
     *
     */

    private String waitingLabelStyle() {
        return "-fx-text-fill: #003366;";
    }

    private String waitingPopUpStyle() {
        return "-fx-background-color: #C7C7C7; -fx-border-color: #003366;";
    }

    private String okButtonStyle(){
        return " -fx-text-fill: #003366;\n" +
                "    -fx-background-color: #C7C7C7;\n" +
                "    -fx-border-color: #003366;\n" +
                "    -fx-background-radius: 0;";
    }

    private String okButtonHoverStyle(){
        return " -fx-text-fill: #003366;\n" +
                "    -fx-background-color: #E1E1E1;\n" +
                "    -fx-border-color: #003366;\n" +
                "    -fx-background-radius: 0;";
    }


    public Stage createInfoPopup(String text, InfoPopupOkHandler handler) {
        Stage stage = new Stage();                      // Stworzenie nowego okna czyli Stage
        stage.initStyle(StageStyle.UNDECORATED);        // usunięcie z Popup paska zadań

        VBox pane = new VBox();                         // Utworzenie obiektu VBox żeby text i pasek ładowania były jedno pod drugim
        pane.setStyle(waitingPopUpStyle());             // ustawienie stylu dla okna (CSS)
        pane.setAlignment(Pos.CENTER);                  // przekazywane elementy mają być na środku
        pane.setSpacing(10);                            // Odstęp pomiędzy napisem a paskiem ładowania


        Label label = new Label(text);                  // Utworzeniu obiektu Label do wyświetlenie tekstu w oknie popUp
        label.setStyle(waitingLabelStyle());            // ustawienie stylu dla tekstu (CSS)

        Button okButton = new Button("OK");
        okButton.setStyle(okButtonStyle());             // styl przycisku
        okButton.setOnMouseEntered(x -> {               // styl po najechaniu myszką na przycisk
            okButton.setStyle(okButtonHoverStyle());
        });
        okButton.setOnMouseExited(x -> {                // styl po usunięciu myszki z przycisku
            okButton.setStyle(okButtonStyle());
        });

        okButton.setOnAction(x -> {
            stage.close();
            handler.handle();
        });

        pane.getChildren().addAll(okButton, label);  // Dodanie do kontenera VBox dzieci - czyli Label i progressBar. AddAll - umożliwia wpisanie po przecinku


        // Stworzenie sceny, o kontenerze VBox o rozmiarach 200x100
        stage.setScene(new Scene(pane, 200, 100));

        // Zablokowanie okna PopUp
        stage.initModality(Modality.APPLICATION_MODAL);

        return stage;
    }
}
