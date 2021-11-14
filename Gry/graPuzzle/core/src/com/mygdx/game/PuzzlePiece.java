package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

/**
 * Created by Michał Dąbrowski
 */
public class PuzzlePiece {

    private TextureRegion pieceImg;
    private GridPoint2 positionOnScreen; // pozycja na ekranie na której element ma byc rysowany
    private GridPoint2 positionInPuzzle; // wyznacza lewy, dolny wierzchołek miejsca danego elementu w układance

    public PuzzlePiece(TextureRegion pieceImg, GridPoint2 positionOnScreen, GridPoint2 positionInPuzzle) {
        this.pieceImg = pieceImg;
        this.positionOnScreen = positionOnScreen;
        this.positionInPuzzle = positionInPuzzle;
    }

    void draw(SpriteBatch batch) {
        batch.draw(pieceImg, positionOnScreen.x, positionOnScreen.y);
    }

    // czy kliknięto w obszar jednego z elementów
    boolean isMouseIn(GridPoint2 mousePos) {
        /*
        Metoda sprawdza, czy punkt przekazany jako argument znajduje się w prostokącie opisanym przez jego lewy,
        dolny wierzchołek oraz prawy, górny wierzchołek.
        */
        return mousePos.x >= positionOnScreen.x &&
                mousePos.y >= positionOnScreen.y &&
                mousePos.x < positionOnScreen.x + pieceImg.getRegionWidth() &&
                mousePos.y < positionOnScreen.y + pieceImg.getRegionHeight();
    }

    void moveBy(int x, int y) {
        /*
        Znając poprzednie i aktualne położenie kursora myszy, łatwo możemy policzyć, o ile się on przesunął, odejmując oba położenia od siebie
        */
        positionOnScreen.x += x;
        positionOnScreen.y += y;
    }

    boolean isPositionRight(GridPoint2 dropPosition) {
        /*
        sprawdza, czy punkt przekazany jako argument jest zawarty w prostokącie opisanym przez docelowe położenie danego elementu układanki.
        */
        return dropPosition.x >= positionInPuzzle.x &&
                dropPosition.y >= positionInPuzzle.y &&
                dropPosition.x < positionInPuzzle.x + pieceImg.getRegionWidth() &&
                dropPosition.y < positionInPuzzle.y + pieceImg.getRegionHeight();
    }

    // dopasowanie upuszczonego elementu do siatki
    void snapToGrid() {
        positionOnScreen.set(positionInPuzzle);
    }



}
