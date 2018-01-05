package uek.ceneo.etl.services;

/**
 * Serwis do oblugi plikow
 */
public interface FileService {
    /**
     * Czyta zawartosc pliku
     *
     * @param fileName Nazwa pliku
     * @return Zawartosc pliku
     */
    String read(String fileName);

    /**
     * Zapisuje dane do pliku
     *
     * @param fileName Nazwa pliku
     * @param data     Dane
     * @return Rozmiar utworzonego pliku
     */
    long write(String fileName, String data);

    /**
     * Usuwa plik
     *
     * @param fileName Nazwa pliku, ktory ma zostac usuniety
     */
    void clear(String fileName);
}
