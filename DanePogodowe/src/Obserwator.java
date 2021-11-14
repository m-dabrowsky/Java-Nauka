/**
 Interfejs który będą miały zaimplementowane obiekty obserwujące.
 Zmienne odpowiadają wartością stanu, jakie obiekty obserwujące otrzymają od podmiotu

 */
public interface Obserwator {

    void aktualizacja(float temp, float wilgotnosc, float cisnienie);

}
