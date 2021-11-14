package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

// kierunki poruszania sie węża
enum KierunekRuchu {LEFT, UP, RIGHT, DOWN}


public class Snake {

    private final Texture texture;
    private final List<GridPoint2> snakeSegments = new ArrayList<>();
    private KierunekRuchu direction;
    private boolean canChangeDirection;

    // czas poruszania się węża
    private float czasRuchu;


    public Snake(Texture texture) {
        this.texture = texture;

    }


    public void initialize(){
        czasRuchu = 0;
        // Domyślne ustawienie ruchu węża w prawo
        direction = KierunekRuchu.RIGHT;

        snakeSegments.clear();
        // dodanie do listy wspołrzędnych poszczególnych segmentów
        snakeSegments.add(new GridPoint2(90, 30));  // współrzędne 1 segmentu
        snakeSegments.add(new GridPoint2(75, 30));  // współrzędne 2 segmentu
        snakeSegments.add(new GridPoint2(60, 30));  // współrzędne 3 segmentu
        snakeSegments.add(new GridPoint2(45, 30));  // współrzędne 4 segmentu
        snakeSegments.add(new GridPoint2(30, 30));  // współrzędne 5 segmentu

    }

    // Zachowanie węża
    public void act(float deltaTime) {                           // deltatime określa czas jaki upłynął od jej poprzedniego wywołania
        zmianaKierunku();

        if (canChangeDirection) {
            zmianaKierunku();
        }
        czasRuchu += deltaTime;                                 //

        if (czasRuchu >= 0.1) {                                 // gdy czas ruchu przekroczy wartość 0.1 (100ms) przesuniemy węża i
            czasRuchu = 0;                                       // zerujemy wartość czasRuchu
            canChangeDirection = true;
            ruch();
        }
    }

    // sprawdzenie czy głowa węża znajduje się w tym samym miejscu co jedzenie, jeśli tak zwróci True
    public boolean isCherryFound(GridPoint2 cherryPosition) {
        return head().equals(cherryPosition);
    }

    // Jeśli zje jedzenie to wydłuży węża o jeden segment
    public void extendSnake() {
        snakeSegments.add(new GridPoint2(snakeSegments.get(snakeSegments.size() - 1))
        );
    }

    // Czy głowa węża uderzyła w jeden z jego segmentów (w ciało)
    public boolean hasHitHimself() {
        for (int i = 1; i < snakeSegments.size(); i++) {
            if (snakeSegments.get(i).equals(head())) {
                return true;
            }
        }
        return false;
    }

    // Rysowanie węża
    public void draw(Batch batch) {
        /*
        Odpowiada za narysowanie obrazku (texture) w każdym z powyższych współrzędnych
        */
        for (GridPoint2 pos : snakeSegments) {      // w liście snakeSegment dla każdego elementu (pos) typu/klasy GridPoint2
            batch.draw(texture, pos.x, pos.y);      // narysuj obrazek ze zmiennej texture we współrzędnych jw.
        }

    }

    // Zmiana kierunku węża za pomocą strzałek
    private void zmianaKierunku(){
        KierunekRuchu newDirection = direction;

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) &&
                direction != KierunekRuchu.RIGHT) {
            newDirection = KierunekRuchu.LEFT;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) &&
                direction != KierunekRuchu.LEFT) {
            newDirection = KierunekRuchu.RIGHT;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) &&
                direction != KierunekRuchu.DOWN) {
            newDirection = KierunekRuchu.UP;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) &&
                direction != KierunekRuchu.UP) {
            newDirection = KierunekRuchu.DOWN;
        }

        if (direction != newDirection) {
            direction = newDirection;
            canChangeDirection = false;
        }

    }

    // Ruch węża
    private void ruch() {

        // przesuń wszystkie segmenty poza głową
        for (int i = snakeSegments.size() - 1; i > 0; i--) {
            snakeSegments.get(i).set(snakeSegments.get(i - 1)); // pobierz wartość indeksu dla aktualnej iteracji a następnie przesuń w tablicy na miejsce o jeden mniejsze (oprócz ostatniego)
        }

        // przesuń głowę
        int szerokoscSegmentu = texture.getWidth();
        int wysokoscSegment = texture.getHeight();


        // pozycje X i Y ostatniego segmentu przed górna i prawą krawędzią okna
        int ostaniSegmentOknaX = Gdx.graphics.getWidth() - szerokoscSegmentu;
        int ostaniSegmentOknaY = Gdx.graphics.getHeight() - wysokoscSegment;


        GridPoint2 head = head();             // Głowa

        switch (direction) {
            case LEFT:
                head.x = (head.x == 0) ? ostaniSegmentOknaX : head.x - szerokoscSegmentu; // sprawdza czy aktualnie głowa węża znajduje się przy lewej krawędzi okna gry, współrzędna X ma wtedy 0
                break;                                                                      // Jeżeli tak to ustawiamy tą współrzędną na wyliczoną wartośc ostniatniSegmentOknaX
            case UP:
                head.y = (head.y == ostaniSegmentOknaY) ? 0 : head.y + wysokoscSegment;
                break;
            case RIGHT:
                head.x = (head.x == ostaniSegmentOknaX) ? 0 : head.x + szerokoscSegmentu;
                break;
            case DOWN:
                head.y = (head.y == 0) ? ostaniSegmentOknaY : head.y - wysokoscSegment;
                break;
        }
    }

    // Głowa węża
    private GridPoint2 head() {
        return snakeSegments.get(0);
    }


}
