package uek.ceneo.etl.models;

/**
 * Interfejs umozliwiajacy obsluge modelu danych
 *
 * @param <T> Obiekt lub tablica
 */
public interface JSONModel<T> {
    /**
     * Przetwarza obiekt to reprezentacji jako ciagu znakow w formacie JSON
     *
     * @return Obiekt jako ciag znakow w formacie JSON.
     */
    String toJSONString();

    /**
     * Przetwarza dane do formatu JSON
     *
     * @return Zwraca dane w formacie JSON
     */
    T toJSONObject();
}
