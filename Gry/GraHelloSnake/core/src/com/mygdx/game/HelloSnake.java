package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloSnake extends ApplicationAdapter {

	// Wymiary okna gry
	public static final int WINDOWS_WIDTH = 420;
	public static final int WINDOWS_HEIGHT = 225;


	private SpriteBatch batch;

	// Zmienne do utworzenai instancji klasy Texture
	private Texture imgSnake;
	private Texture imgCherry;

	// Klasa Snake
	private Snake snake;
	// Klasa cherry
	private Cherry cherry;

	private boolean gameOver;

	@Override
	public void create () {
		batch = new SpriteBatch();

		// Utworzenie obiektu snake
		imgSnake = new Texture("snake.png");
		snake = new Snake(imgSnake);

		// utworzenie obiektu cherry
		imgCherry = new Texture("cherry.png");
		cherry = new Cherry(imgCherry);

		initializeNewGame();

	}

	@Override
	public void render () {
		updateGame();

		// Kolor tła
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Rysowanie
		batch.begin();
		snake.draw(batch);
		cherry.draw(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		imgSnake.dispose();
		imgCherry.dispose();
	}

	private void updateGame() {
		if (!gameOver) { // 1
			snake.act(Gdx.graphics.getDeltaTime());            // argument zwraca czas jaki upłynął od jej ostatniego wywołania
			if (snake.isCherryFound(cherry.getPosition())) {
				snake.extendSnake();
				cherry.randomizePosition();
			}
			if (snake.hasHitHimself()) {
				gameOver = true;
			}
		} else {
			if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
				initializeNewGame();
			}
		}
	}

	private void initializeNewGame() {
		snake.initialize();
		cherry.randomizePosition();
		gameOver = false;
	}


}
