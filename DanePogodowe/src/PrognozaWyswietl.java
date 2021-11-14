/**
 * Created by Michał Dąbrowski
 */
public class PrognozaWyswietl implements Obserwator, WyswietlElement{

    private float aktualneCisnienie = 29.92f;
    private float ostatnieCisnienie;

    public PrognozaWyswietl(Podmiot DanePogodowe) {
        DanePogodowe.zarejestrujObserwatora(this);
    }


    // Inicjalizacja metody z interfejsu Obserwator
    public void aktualizacja(float temp, float wilgotnosc, float cisnienie){
        //DanePogodowe danePogodowe = (DanePogodowe) obserwator;
        ostatnieCisnienie = aktualneCisnienie;
       // aktualneCisnienie = danePogodowe.getCisnienie();
        wyswietl();
        System.out.println();
    }


    // Inicjalizacja metody z interfejsu WyswietlElement
    public void wyswietl(){
        System.out.println("Pogoda:");
        if(aktualneCisnienie > ostatnieCisnienie){
            System.out.println("Nadchodzi polepszenie pogody!");
        } else if (aktualneCisnienie == ostatnieCisnienie){
            System.out.println("Pogoda bez zmian");
        } else if (aktualneCisnienie < ostatnieCisnienie) {
            System.out.println("Nadchodzi zimno");
        }

    }

}
