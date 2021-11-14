package libraryAssistant_UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * Created by Michał Dąbrowski
 */
public class Contoler implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField publisher;
    @FXML
    private TextField saveButton;
    @FXML
    private TextField cancelButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void addBook (ActiveEvent event){

    }

    @FXML
    private void cancel (ActiveEvent event){

    }
    DatabaseConnection connectionNow = new DatebaseConnection();
    Connection connectDB = connectionNow.getConnection();

    /*
    DatabaseConnection
     */


}
