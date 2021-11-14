/**
 * Created by Michał Dąbrowski
 */
public class WarunkiBiezaceWyswietl implements Obserwator, WyswietlElement {

    private float temp;
    private float wilgotnosc;
    private float cisnienie;
    private Podmiot DanePogodowe;

    // Utworzenie obiektu WarunkiBiezaceWyswietl o określonych parametrach które przechowuje DanePogodoe
    public WarunkiBiezaceWyswietl(Podmiot DanePogodowe){
        this.DanePogodowe = DanePogodowe;
        DanePogodowe.zarejestrujObserwatora(this);
    }

    // Inicjalizacja metody z interfejsu Obserwator
    public void aktualizacja(float temp, float wilgotnosc, float cisnienie){
        this.temp = temp;
        this.wilgotnosc = wilgotnosc;
        this.cisnienie = cisnienie;
        wyswietl();
    }

    // Inicjalizacja metody z interfejsu WyswietlElement
    public void wyswietl(){

        System.out.println("warunki bieżące " + temp + " stopni C oraz " + wilgotnosc + "% wilgotnosc");
    }


}
