package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

public class TicTacToeController implements Initializable {

    @FXML
    private MenuItem newGameMenu;
    @FXML
    private MenuItem closeMenu;
    @FXML
    private MenuItem aboutMenu;


    @FXML
    private Button button00;
    @FXML
    private Button button10;
    @FXML
    private Button button01;
    @FXML
    private Button button11;
    @FXML
    private Button button20;
    @FXML
    private Button button21;
    @FXML
    private Button button02;
    @FXML
    private Button button12;
    @FXML
    private Button button22;

    private Model model;
    private Button[][] buttons;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new Model();
        buttons = new Button[][] {
                {button00, button01, button02},
                {button10, button11, button12},
                {button20, button21, button22}
        };
        addButtonAction();
        addMenuAction();
    }

    /*
    * Metoda resetująca grę - tworzy nowy model i ustawia pusty tekst przycisków
    */
    private void newGame(){
        model = new Model();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setText("");
            }

        }
    }


    private void addButtonAction(){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                final int x = i, y = j;
                buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String buttonText = buttons[x][y].getText();
                        if("".equals(buttonText)){
                            model.setValue(x,y, model.getActivePlayer());
                            updateView();
                            model.switchPlayer();
                        }
                        if (model.getWinner() != Model.BLANK){
                            showWinnerPopup();
                        }
                    }
                });
            }

        }
    }


    private void addMenuAction(){
        newGameMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGame();
            }
        });

        closeMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        aboutMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String aboutText = "Gra w Kółka i Krzyżyk\n" + "Michał Dąbrowski";
                createPopup(aboutText);
            }
        });

    }


    private void updateView(){
        for (int i = 0; i < Model.SIZE; i++) {
            for (int j = 0; j < Model.SIZE; j++) {
                if(model.getValue(i,j) == Model.X){
                    buttons[i][j].setText("X");
                } else if (model.getValue(i,j) == Model.O){
                    buttons[i][j].setText("O");
                }
            }
        }
    }


    private void showWinnerPopup() {
        String winner = null;
        if (model.getWinner() == Model.X) {
            winner = "X";
        } else if (model.getWinner() == Model.O) {
            winner = "O";
        }

        String winnerText = "Wygrywa: " + winner;

        Popup popup = createPopup(winnerText);
        popup.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent mouseEvent){
             newGame();
            }
            });
    }


    private  Popup createPopup(String text){
        TextArea popupText = new TextArea(text);
        popupText.setPrefWidth(200);
        popupText.setPrefHeight(100);
        popupText.setEditable(false);

        Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.getContent().addAll(popupText);

        popup.show(button00.getScene().getWindow());
        popup.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newGame();
                popup.hide();
            }
        });

        return popup;
    }


}
