package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

/**
 * Created by Michał Dąbrowski
 */
public class Cherry {

    private final Texture texture;                  // obraz cherry
    private final GridPoint2 polozenie;             // położenie w oknie gry

    public Cherry(Texture texture) {
        this.texture = texture;
        this.polozenie = new GridPoint2();

        randomizePosition();
    }

    // rysowanie grafiki
    public void draw(Batch batch) {
        batch.draw(texture, polozenie.x, polozenie.y);
    }


    public void randomizePosition() {
        // wyznaczenie liczby możliwych pozycji w oknie gry
        int numberOfXPositions = Gdx.graphics.getWidth() / texture.getWidth();
        int numberOfYPositions = Gdx.graphics.getHeight() / texture.getHeight();

        /*
        Aby wylosować pozycję jedzenia w oknie gry, losujemy dwie liczby z przedziału <0 do numberOfXPositions lub numberOfYPositions),
        a następnie przemnażamy każdą z tych wylosowanych wartości przez szerokość (bądź wysokość) tekstury jedzenia,
        dzięki czemu pozycja będzie odpowiednio dopasowana do „siatki” pozycji prezentowanych na powyższym obrazku.
        */

        this.polozenie.set(
                (int) (Math.random() * numberOfXPositions) * texture.getWidth(),
                (int) (Math.random() * numberOfYPositions) * texture.getHeight()
        );

    }


    public GridPoint2 getPosition() {
        return this.polozenie;
    }






}
