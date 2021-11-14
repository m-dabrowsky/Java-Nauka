/**
 * Metody pobierają konkretny obiekt który ma zostać zarejestrowany na liście subskrybentów
 */
public interface Podmiot {

    void zarejestrujObserwatora(Obserwator o);
    void usunObserwatora(Obserwator o);
    void powiadomObserwatorow();

}
