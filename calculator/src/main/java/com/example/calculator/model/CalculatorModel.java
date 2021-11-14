package com.example.calculator.model;

/**
 * Klasa odpowiedzialna za działanie kalkulatora
 *
 */

public class CalculatorModel {

    private final static CalculatorModel instance = new CalculatorModel(); // ????
    private int firstNumber = 0;
    private int secondNumber = 0;


    public CalculatorModel() {

    }

    public static CalculatorModel getInstance(){
        return instance;
    }




    /**
     * Ustawienie podanych liczb jako firstNumber i secondNumber
     * @param firstNumber
     */

    public void setFirstNumber(int firstNumber) {
        this.firstNumber=firstNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }





    /**
     * Zwraca działania na firstNumber i secondNumber
     * @return
     */

    public int add() {
        return firstNumber + secondNumber;
    }

    public int subtract() {
        return firstNumber - secondNumber;
    }

    public int multiply() {
        return firstNumber * secondNumber;
    }

    public int divide() {
        return firstNumber / secondNumber;
    }


}
