package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SimpleJigsawPuzzleGame extends ApplicationAdapter {

	// WYMIARY OKNA
	public static final int WINDOWS_WIDTH = 800;
	public static final int WINDOWS_HEIGHT = 450;

	// Wielkość kawałków okna
	private static final int PUZZLE_PIECE_WIDTH = 100;
	private static final int PUZZLE_PIECE_HEIGHT = 100;

	//
	private SpriteBatch batch;

	// Obrazeki
	private Texture img;
	private Texture puzzleLines;

	// Do umieszczenia obrazka na środku ekranu
	private int puzzleOriginX;
	private int puzzleOriginY;

	// Lista z elementami układanki i lista z elementami ulożonymi na swoim miejscu
	private List<PuzzlePiece> puzzlePiecesLeft;
	private List<PuzzlePiece> puzzlePiecesInPlace;

	// Kliknięty element układanki
	private PuzzlePiece selectedPiece;

	// sprawdzania o ile kursor myszki się przesunął
	private GridPoint2 lastMousePosition = new GridPoint2();

	
	@Override
	public void create () {

		// instancja klasy Texture ze ścieżką obrazka
		img = new Texture("kitty.png");
		puzzleLines = new Texture("puzzle_outline.png");

		// Wyznaczenie współrzędnych środka
		puzzleOriginX = WINDOWS_WIDTH / 2 - img.getWidth() / 2;
		puzzleOriginY = WINDOWS_HEIGHT / 2 - img.getHeight() / 2;

		batch = new SpriteBatch();

		// instalacja klasy LinkedList
		puzzlePiecesLeft = new LinkedList<>();
		puzzlePiecesInPlace = new LinkedList<>();

		// Wywołanie metody odpowiedzialnej za przygotowanie elementów puzzli
		preparePuzzlePieces();

	}

	@Override
	public void render () {
		// Obsługa myszki
		handleMouse();

		// Kolor tła
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Rozpoczęcie rysowania
		batch.begin();

		// Rysowanie siatki
		batch.draw(puzzleLines, puzzleOriginX, puzzleOriginY);

		for (PuzzlePiece piece : puzzlePiecesInPlace) {
			piece.draw(batch);
		}

		// Przechodzimy przez całą listę z elementami układanki i każemy im się narysować na ekranie.
		//batch.draw(img, puzzleOriginX,puzzleOriginY);
		for (PuzzlePiece piece : puzzlePiecesLeft) {
			piece.draw(batch);
		}

		// Rysowanie przesuwanego elementu
		if (selectedPiece != null) {
			selectedPiece.draw(batch);
		}

		// Zakończenie rysowania
		batch.end();
	}
	
	@Override
	public void dispose () {
		// Zwolnienie zasobów pamięci
		batch.dispose();
		img.dispose();
		puzzleLines.dispose();

	}

	// Wylosowanie współrzędnej
	private int randomIntMax(int maxValue) {
		return (int) (Math.random() * (maxValue + 1));
	}

	// Metoda zwracajacą obiekt i randomowych współrzędnych
	// Musimy od szerokości i wysokości okna odjąć szerokość i wysokość elementu – w przeciwnym razie niektóre elementy mogłyby wyjść poza prawą i górną krawędź okna
	private GridPoint2 randomizePuzzlePiecePosition() {
		return new GridPoint2(
				randomIntMax(WINDOWS_WIDTH - PUZZLE_PIECE_WIDTH), 		// współrzędna X
				randomIntMax(WINDOWS_HEIGHT - PUZZLE_PIECE_HEIGHT)		// współrzędna Y
		);
	}

	// Pobieranie pozycji myszki
	private GridPoint2 getMousePosMappedToScreenPos() {
		return new GridPoint2(Gdx.input.getX(),WINDOWS_HEIGHT - 1 - Gdx.input.getY());
	}

	// Sprawdzanie czy kursor myszki wyszedł poza krawędzie okna
	private boolean isMouseInsideGameWindow() {
		GridPoint2 mousePosition = getMousePosMappedToScreenPos();
		return mousePosition.x >= 0 &&
				mousePosition.y >= 0 &&
				mousePosition.x < WINDOWS_WIDTH &&
				mousePosition.y < WINDOWS_HEIGHT;
	}

	// Obsługa myszki
	private void handleMouse(){
		// pobranie pozycji myszki
		GridPoint2 mousePosition = getMousePosMappedToScreenPos();
		// jeśli lewy przycisk myszki jest wciśnięty
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			// sprawdź czy położenie myszki znajduje się w obszarze jednego z elementów
			ListIterator<PuzzlePiece> listIterator = puzzlePiecesLeft.listIterator(puzzlePiecesLeft.size());
			while (listIterator.hasPrevious()) { 						// zwraca True jeśli przejście wstecz ma następny element
				PuzzlePiece puzzlePiece = listIterator.previous();		// do puzzlePiece przypisuje poprzedni element
				if (puzzlePiece.isMouseIn(mousePosition)) {				// zwraca True jesli pozycja myszki jest na elemencie puzzlePiece
					selectedPiece = puzzlePiece;						// do zmiennej selectedPiece przypisuje dany element
					listIterator.remove();								// usunięcie go z listy puzzlePiecesLeft
					break;												// przerywa petle bo znaleziono element który został kliknięty
				}
			}
			lastMousePosition.set(mousePosition);						// ustawia do zmiennej pozycje myszki
		}


		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) &&
				selectedPiece != null) {

			// Dodanie metody sprawdzenia czy kursor nie wyszedł poza okno
			if (isMouseInsideGameWindow()) {
				selectedPiece.moveBy(
						mousePosition.x - lastMousePosition.x,
						mousePosition.y - lastMousePosition.y
				);
				lastMousePosition.set(mousePosition);					// aktualizacja pola
			}

		} else if (selectedPiece != null) {							// jeśli użytkownik przestaje trzymać przycisk myszki
			if (selectedPiece.isPositionRight(mousePosition)) {		// jeśli element jest na swoim miejscu
				selectedPiece.snapToGrid();
				puzzlePiecesInPlace.add(selectedPiece);				// to dodamy go do listy puzzlePiecesinPlace
			} else {												// w przeciwnym razie wstawimy go z powrotem do listy puzzlePiecesLeft
				puzzlePiecesLeft.add(selectedPiece);
			}
			selectedPiece = null;									// element nie jest juz przesuwany wiec ustawia slectedPiece na null
		}




	}

	// Przygotowanie puzzli zdjęcia
	private void preparePuzzlePieces() {
		/*
		Odpowiada za wykonanie podziału obrazu na elementy o wymiarze 100x100.
		Przechodzi przez wszystkie rzędy i kolumny układanki, aby podzielić je
		na równe części które będą opisane przez obiekty typu TextureRegion
		*/

		// Obliczenie ile wyjdzie kolumn i wierszy jeżeli mamy obraz 600x300 i chcemy mieć elementy 100x100
		int numberOfPuzzleRows = img.getHeight() / PUZZLE_PIECE_HEIGHT; 	// 3 WIERSZE
		int numberOfPuzzleColumns = img.getWidth() / PUZZLE_PIECE_WIDTH; 	// 6 KOLUMN

		for (int row = 0; row < numberOfPuzzleRows; row++) {				// dla 0 wiersza
			for (int col = 0; col < numberOfPuzzleColumns; col++) {			// dla 0 kolumny

				TextureRegion puzzlePieceImg = new TextureRegion();			// instancja klasy odpowiedzialnej za element
				puzzlePieceImg.setTexture(img);								// ustawienie obrazku do podziału
				puzzlePieceImg.setRegion(									// ustawienie obrazku i pkt. 0,0 i wymiarze 100x100
						col * PUZZLE_PIECE_WIDTH,						// 0
						row * PUZZLE_PIECE_HEIGHT,						// 0
						PUZZLE_PIECE_WIDTH,									// 100
						PUZZLE_PIECE_HEIGHT									// 100
				);


				GridPoint2 positionOnScreen = randomizePuzzlePiecePosition();		// pozycja na ekranie na której element ma byc rysowany.

				// Docelową pozycję X elementu wyliczamy jako położenie w teksturze przemnożone razy szerokość elementu.
				GridPoint2 positionInPuzzle = new GridPoint2(puzzleOriginX + col * PUZZLE_PIECE_WIDTH, puzzleOriginY + (numberOfPuzzleRows - 1 - row) * PUZZLE_PIECE_HEIGHT);

				PuzzlePiece piece = new PuzzlePiece(puzzlePieceImg, positionOnScreen, positionInPuzzle);
				puzzlePiecesLeft.add(piece);										// dodanie elementu obrazka do listy

			}
		}









	}
}
