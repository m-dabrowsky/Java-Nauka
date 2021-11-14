package org.example;

public class Model {

    public static final int X = 1;
    public static final int O = -1;
    public static final int BLANK = 0;

    // rozmiar planszy
    public static final int SIZE = 3;
    // tablica reprezentująca planszę
    private int[][] table;
    // aktualny gracz zmieniany co rundę
    private int activePlayer;

    //ustawia pole w tablicy
    public void setValue (int x, int y, int value){
        table[x][y] = value;
    }

    // zwraca pole w tablicy
    public int getValue(int x, int y){
        return table[x][y];
    }

    // zwraca aktualnego gracza
    public int getActivePlayer(){
        return activePlayer;
    }

    // ustawia zmienną activePlayer na wartość ze zmienionym znakiem z 1 na -1 lub odwrotnie
    public void switchPlayer(){
        activePlayer = -activePlayer;
    }

    /*
    *  Konstruktor - inicjalizacja tablicy o rozmiarze SIZE oraz ustawienie gracza rozpoczynającego grę na O
    */
    public Model(){
        table = new int[SIZE][SIZE];
        activePlayer = O;

    }

    /*
     *  Kto wygrał
     */

    public int getWinner(){
        int winner = BLANK;

        // sprawdzamy wiersze
        for (int row = 0; row < SIZE; row++) {
            for (int col = 1; col < SIZE; col++) {
                if(table[row][col] != table[row][col-1]){
                    break;
                } else if (col == SIZE-1){
                    winner = table[row][col];
                    return winner;
                }
            }
        }

        // sprawdzamy kolumny
        for (int row = 0; row < SIZE; row++) {
            for (int col = 1; col < SIZE; col++) {
                if(table[col][row] != table[col-1][row]){
                    break;
                } else if (col == SIZE-1){
                    winner = table[col][row];
                    return winner;
                }
            }
        }

        // sprawdzamy pierwszą przekątną
        for (int i = 1; i < SIZE; i++) {
            if (table[i][i] != table[i - 1][i - 1]) {
                break;
            } else if (i == SIZE - 1) {
                winner = table[i][i];
                return winner;
            }
        }



        // sprawdzamy drugą przekątną
        for (int i = 0; i < SIZE; i++) {
            if (table[i][SIZE - 1 - i] != table[i + 1][SIZE - 2 - i]) {
                break;
            } else if (i == SIZE - 2) {
                winner = table[i][i];
                return winner;
            }
        }
        return winner;
    }

}
