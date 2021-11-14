package com.example.calculator.controller;

import com.example.calculator.En.Operations;
import com.example.calculator.model.CalculatorModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Klasa odpowiedzialna za działania bezpośrednio na oknie aplikacji
 */


public class Controller {

    CalculatorModel calculator = CalculatorModel.getInstance();             // na początku potrzebny jest kalkulator
    Operations operations;                                                  // oraz typy operacji jakie można na nim wykonywać

    boolean isTypingFirstNumber = true;
    String promptFirstNumber = "";
    String promptSecondNumber = "";


    @FXML
    private Label result;                                                   // wynik

    /*public void init(Stage primaryStage){

        titlePane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getX();
                y = event.getY();

            }
        });

        titlePane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getSceneX()-x);
                primaryStage.setY(event.getSceneY()-y);
            }
        });


        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });

        minimalizeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setIconified(true);
            }
        });

    }*/


    /**
     *  Wyświetlenie odpowiedniej liczby
     *  Jeśli isTypingFirstNumber = true to na ekranie zostanie wyświetlone promptFirstNumber
     *  jeśli nie to promptSecondNumber
     */

    private void displayRightNumberInResultLabel() {
        result.setText(isTypingFirstNumber ? promptFirstNumber : promptSecondNumber);
    }


    /**
     * Metoda dopisująca podaną cyfrę do odpowiedniego Stringa
     * jeśli promptFirstNumber = "halko" po wciśnięciu 1 -> halko1
     * @param number
     */
    private void promptNumberToRightNumber (int number){
        if (isTypingFirstNumber) {
            promptFirstNumber = promptFirstNumber.concat(String.valueOf(number));
        } else {
            promptSecondNumber = promptSecondNumber.concat(String.valueOf(number));
        }

        displayRightNumberInResultLabel();
    }

    /**
     * Przełączanie liczb - po wciśnięciu przycisku operacji trzeba zapisywać do drugiej liczby
     * czyli zmienić stan isTypingFirstNumber na false
     */
    private void switchToSecondNumber() {
        isTypingFirstNumber = false;
        displayFirstNumber();
    }


    /**
     * Wyświetlenie pierwszej liczby
     */

    private void displayFirstNumber(){
        result.setText(promptFirstNumber);
    }


    /**
     * Sprawdzenie czy druga została wprowadzona
     */

    private boolean isSecondNumberExist(){
        return promptSecondNumber.length() > 0;
    }

    /**
     * Czy operacja została wybrana
     */

    private boolean isOperationDefined(){
        return operations != null;
    }




    /**
     * Wywołanie obliczeń
     * @return
     * Wciśnięcie przycisku np: dodawania spowoduje przypisanie do operations typu wyliczeniowego ADD
     * a następnie wywołanie metody add() z klasy CalculatorModel
     */

    private int callCalculatorAction() {
        switch(operations) {
            case ADD:
                return calculator.add();

            case DIVIDE:
                return calculator.divide();

            case SUBTRACT:
                return calculator.subtract();

            case MULTIPLY:
                return calculator.multiply();

            default:
                return 0;
        }
    }




    /**
     * Operacje
     * Wciśnięcie przycisku np: dodawania spowoduje przypisanie do operations typu wyliczeniowego ADD
     */
    public void clickButtonAdd() {
        operations = Operations.ADD;
        switchToSecondNumber();
    }

    public void clickButtonMinus() {
        operations = Operations.SUBTRACT;
        switchToSecondNumber();

    }

    public void clickButtonMultiply() {
        operations = Operations.MULTIPLY;
        switchToSecondNumber();
    }

    public void clickButtonDivide() {
        operations = Operations.DIVIDE;
        switchToSecondNumber();
    }




    /**
     * Zapisywanie wybranych cyfr
     */

    public void clickButtonZero() {
        promptNumberToRightNumber(0);
    }

    public void clickButtonOne() {
        promptNumberToRightNumber(1);
    }

    public void clickButtonTwo() {
        promptNumberToRightNumber(2);
    }

    public void clickButtonThree() {
        promptNumberToRightNumber(3);
    }

    public void clickButtonFour() {
        promptNumberToRightNumber(4);
    }

    public void clickButtonFive() {
        promptNumberToRightNumber(5);
    }

    public void clickButtonSix() {
        promptNumberToRightNumber(6);
    }

    public void clickButtonSeven() {
        promptNumberToRightNumber(7);
    }

    public void clickButtonEight() {
        promptNumberToRightNumber(8);
    }

    public void clickButtonNine() {
        promptNumberToRightNumber(9);
    }



    /**
     * Czyszczenie kalkulatora
     */

    public void clickButtonClear(){
        result.setText("");
        promptFirstNumber = "";
        promptSecondNumber = "";
        isTypingFirstNumber = true;
        operations = null;
    }




    /**
     * Obliczenia
     */
    public void clickButtonResult() {
        if (isSecondNumberExist() && isOperationDefined()) {            // czy piierwsza i druga liczba zostały podane
            calculator.setFirstNumber(Integer.valueOf(promptFirstNumber));  // ustawia pierwsza liczbę zamieniajac string na integer
            calculator.setSecondNumber(Integer.valueOf(promptSecondNumber));
            int result = callCalculatorAction();

            isTypingFirstNumber = false;
            promptFirstNumber = String.valueOf(result);
            promptSecondNumber="";
            displayFirstNumber();

        }
    }

    /*@FXML
    public void onNumberClicked (MouseEvent event) {
        if(start){
            result.setText("");
            start = false;
        }
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("button", ""));
        result.setText(Double.parseDouble(result.getText())==0?String.valueOf((double)value):String.valueOf(Double.parseDouble(result.getText())*10+value));

    }


    public void onSymbolClicked (MouseEvent event) {

        String symbol = ((Pane)event.getSource()).getId().replace("button", "");
        if(!symbol.equals("=")){
            double num2 = Double.parseDouble(result.getText());
            switch(operator){
                case "+":
                    result.setText((num1+num2) + "");
                case "-":
                    result.setText((num1-num2) + "");
                case "*":
                    result.setText((num1*num2) + "");
                case "/":
                    result.setText((num1/num2) + "");
            }
            operator = ".";

        } else if(symbol.equals("Close")){
            result.setText(String.valueOf(0.0));
            operator = ".";
        } else {
            switch(symbol){
                case "Plus":
                    operator = "+";
                case "Minus":
                    operator = "-";
                case "Multiply":
                    operator = "*";
                case "Divide":
                    operator = "/";
            }
        }


    }*/












}
