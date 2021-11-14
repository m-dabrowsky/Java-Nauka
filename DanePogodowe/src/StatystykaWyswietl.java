/**
 * Created by Michał Dąbrowski
 */
public class StatystykaWyswietl implements Obserwator, WyswietlElement {


    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int odczyt;

    public StatystykaWyswietl(Podmiot DanePogodowe) {
        DanePogodowe.zarejestrujObserwatora(this);;
    }

    // Inicjalizacja metody z interfejsu Obserwator
    public void aktualizacja(float temp, float wilgotnosc, float cisnienie){


        tempSum += temp;
        odczyt++;

        if (temp > maxTemp){
            maxTemp = temp;
        }
        if (temp < minTemp){
            minTemp = temp;
        }

        wyswietl();
    }


    // Inicjalizacja metody z interfejsu WyswietlElement
    public void wyswietl(){
        System.out.println("Srednia/Max/Min temperatura: " + (tempSum/odczyt) + "/" + maxTemp + "/" + minTemp);


    }



}
