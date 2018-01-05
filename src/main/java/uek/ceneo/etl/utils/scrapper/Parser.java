package uek.ceneo.etl.utils.scrapper;

/**
 * Interfejs pozwalajacy przeanalizowac dane zawarte w pliku
 *
 * @param <T>
 */
public interface Parser<T> {
    /**
     * Główna metoda odpowiedzialna za analize danych
     *
     * @return Wyodrebnione dane
     */
    T parse();
}
