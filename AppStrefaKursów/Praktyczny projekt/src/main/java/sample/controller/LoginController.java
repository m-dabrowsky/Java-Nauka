package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.dto.OperatorCredentialDto;
import sample.factory.PopUpFactory;
import sample.rest.Authenticator;
import sample.rest.AuthenticatorImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final String APP_FXML = "/fxml/app.fxml";
    private static final String APP_TITLE = "RP SYSTEM";
    private PopUpFactory popUpFactory;      // utworzenie pola dla okna PopUp
    private Authenticator authenticator;    // utworzenie pola dla autoryzacji

    @FXML
    private Button exitButton;              // przycisk ZAMKNIĘCIA
    @FXML
    private Button loginButton;             // przycisk ZALOGUJ SIĘ
    @FXML
    private AnchorPane loginAnchorPane;     // Główny Kontener aplikacji
    @FXML
    private TextField loginTextField;       // pole do wpisania loginu
    @FXML
    private TextField passwordTextField;    // pole do wpisania hasłą


    // Konstruktor klasy
    public LoginController(){
        popUpFactory = new PopUpFactory();   // stworzenie obiektu PopUp
        authenticator = new AuthenticatorImpl();
    }


    // Metoda tworzona automatycznie po dodaniu: implements Initializable
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeExitButton();             // wywołanie działania naciśnięcia przycisku X
        initializeLoginButton();            // wywołanie działania naciśnięcia przycisku Login

    }


    /**
     * Część odpowiedzialna za przycisk ZALOGUJ SIĘ
     */

    // 2. Działanie po naciśnięciu przycisku
    private void initializeLoginButton() {
        loginButton.setOnAction(event -> performAuthentication()); // na przycisku loginButton ustaw akcje --> wykonanie metody performAuth
        
    }

    // 1. metoda która się wywołuje po naciśnięciu przycisku logowania
    private void performAuthentication() {
        // Stworzenie okna PopUp o napisie Connecting to teh server i zapisanie w zmiennej popUp typu Stage (bo jest to okno)
        Stage popUp = popUpFactory.createWaitingPopUp("Connecting to the server");
        popUp.show();                                           // Wyświetlenie okna PopUp

        String login = loginTextField.getText();                // pobranie wpisane tekstu w okno Login
        String password = passwordTextField.getText();          // pobranie wpisanego tekstu w okno password

        OperatorCredentialDto dto = new OperatorCredentialDto();        // Utworzenie obiektu do zapisywania loginu i hasła
        dto.setLogin(login);                                    // zapis login
        dto.setPassword(password);                              // zapis hasła
        authenticator.authenticate(dto, (authenticationResult)->{
            Platform.runLater(()->{
                popUp.close();                                  // zamknięcie PopUpu
                if(authenticationResult.isAuthenticated()){
                    openAppAndCloseLoginStage();
                } else {
                    showIncorrectCredentialsMessage();
                }
            });
        });
    }

    /**
     * Część odpowiedzialna za pokazanie listy pracowników i zamknięcie okna logowania
     */

    // Wyświetla w konsoli informację o błędnym haśle
    private void showIncorrectCredentialsMessage() {
        System.out.println("Incorrect credentials");
    }

    // zamknięcie okna logowania i otworzenie listy pracowników
    private void openAppAndCloseLoginStage() {
        Stage appStage = new Stage();
        Parent appRoot = null;
        try {
            appRoot = FXMLLoader.load(getClass().getResource(APP_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(appRoot, 1024, 768 );
        appStage.setTitle(APP_TITLE);
        appStage.setScene(scene);
        appStage.show();
        getStage().close();
    }

    /**
     * Część odpowiedzialna za przycisk X
     */

    // 2. zamknięcie okna po naciśnięciu przycisku X
    private void initializeExitButton() {
        exitButton.setOnAction(event -> getStage().close());    // na przycisku exitButton ustaw akcje --> pobrania Stage i jego zamknięcie

    }

    // 1. Pobranie Stage
    private Stage getStage(){
        return (Stage) loginAnchorPane.getScene().getWindow();
    }






}

