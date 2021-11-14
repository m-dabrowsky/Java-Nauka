import java.util.ArrayList;



public class DanePogodowe implements Podmiot{

    // lista subskrybentów
    private ArrayList obserwatorzy;
    // zmienne do przechowywania pomiarów temp itd.
    private float temp;
    private float wilgotnosc;
    private float cisnienie;

    // Konstruktor, każde wywołanie tworzy nową obiekt
    public DanePogodowe() {

        obserwatorzy = new ArrayList();
    }

    // dodanie obiektu do listy subskrybentów
    public void zarejestrujObserwatora(Obserwator o){

        obserwatorzy.add(o);
    }

    // usunięcie obiektu z listy subskrybentów
    public void usunObserwatora(Obserwator o){
        int i = obserwatorzy.indexOf(o);                // pobiera numer indeksu dla danego obiektu w liście
        if (i >= 0){                                    // jeśli index jest większy lub równy 0
            obserwatorzy.remove(i);                     // usuwa z listy
        }
    }



    //Metoda informująca obserwatory że zmienił się stan podmiotu.
    public void powiadomObserwatorow(){
        for (int i = 0; i < obserwatorzy.size(); i++) {                // iteracje dla rozmiaru listy
            Obserwator Obs = (Obserwator)obserwatorzy.get(i);          // pobiera obserwatora z listy, rzutuje na interfejs Obserwator aby wywołać metodę
            Obs.aktualizacja(temp, wilgotnosc, cisnienie);             // która zaktualizuje zmiany stanów
        }
    }

    // Setter odpowiedzialny za ustawianie nowych odczytów, i powiadomienie obserwatorów
    public void ustawOdczyty(float temp, float wilgotnosc, float cisnienie) {
        this.temp = temp;
        this.wilgotnosc = wilgotnosc;
        this.cisnienie = cisnienie;
        powiadomObserwatorow();
    }



}
