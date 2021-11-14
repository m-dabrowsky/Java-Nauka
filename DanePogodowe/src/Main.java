/**
 * Created by Michał Dąbrowski
 */
public class Main {

    public static void main(String[] args) {
        DanePogodowe danePogodowe = new DanePogodowe();

        WarunkiBiezaceWyswietl warunkiBiezaceWyswietl = new WarunkiBiezaceWyswietl(danePogodowe);
        StatystykaWyswietl statystykaWyswietl = new StatystykaWyswietl(danePogodowe);
        PrognozaWyswietl prognozaWyswietl = new PrognozaWyswietl(danePogodowe);

        danePogodowe.ustawOdczyty(26.6f, 65, 1031.1f);
        danePogodowe.ustawOdczyty(27.0f, 20, 31.1f);
        danePogodowe.ustawOdczyty(35.2f, 154, 131.1f);

    }




}
