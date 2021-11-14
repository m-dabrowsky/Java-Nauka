package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField enterPasswordField;


    // Akcja wciśnięcia przycisku LOGIN
    public void loginButtonOnAction(ActionEvent event){

        if(usernameText.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){               // jeśli wpisano użytkownika i hasło to wykona validateLogin
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password");                            // w przeciwnym razie zwróci wiadomość:
        }

    }

    // Akcja wciśnięcia przycisku CANCEL
    public void cancelButtonOnAction(ActionEvent event){

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public void validateLogin(){
        DataBaseConnection connectNow = new DataBaseConnection();                                       // Tworzy nowy obiekt klasy BazaDanych
        Connection connectionDB = connectNow.getConnection();                                           // wywołuje z klasy metodą odpowiedzialna za połączenie z bazą danych

        String verifyLogin = "SELECT COUNT(1) FROM user_account WHERE username = \"Mikki\" AND password = \"lala12\";";                                                   // zapytanie/instrukcja do SQL

        try{
            Statement statement = connectionDB.createStatement();                                       // służy do wykonywania instrukcji SQL
            ResultSet queryResult = statement.executeQuery(verifyLogin);                                // wynik wywowałania polecenia

            while(queryResult.next()){                                                                  // .next() zwraca boolean - Póki mamy jakies wyniki to możemy coś z tym zrobić
                if(queryResult.getInt(1) == 1 ){
                    loginMessageLabel.setText("Congratulations!");
                } else {
                    loginMessageLabel.setText("Valid login. Please try again.");
                }
                System.out.println(queryResult.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }



    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File brandingFile = new File("/Images/pngwing.com%20(4).png");
//        Image brandingImage = new Image(brandingFile.toURI().toString());
//        brandingImageView.setImage(brandingImage);
//
//
//        File lockFile = new File("/Images/pngwing.com%20(1).png");
//        Image lockImage = new Image(brandingFile.toURI().toString());
//        lockImageView.setImage(lockImage);
    }
}
