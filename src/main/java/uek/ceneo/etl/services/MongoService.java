package uek.ceneo.etl.services;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

/**
 * Interfejs do obsługi bazy danych
 */
public interface MongoService {
    /**
     * Wstawia dokument do bazy danych
     *
     * @param collection Nazwa kolekcji
     * @param dbObject   Obiekt, który ma zostac wstawiony
     * @return Zwraca true jesli operacja zakonczyla sie sukcesem
     */
    boolean insert(String collection, String dbObject);

    /**
     * Pobiera unikatowa liste elementow na postawie pola id
     *
     * @param collection Nazwa kolekcji
     * @return Unikatowa lista dokumentów pobranych z bazy
     */
    ArrayList<Document> getUniqueList(String collection);

    /**
     * Pobiera unikatowa liste elementow na postawie pola id z wykorzystaniem query
     *
     * @param collection    Nazwa kolekcji
     * @param documentQuery Zapytanie do bazy
     * @return Unikatowa lista dokumentów pobranych z bazy na podstawie zapytania
     */
    ArrayList<Document> getUniqueList(String collection, Document documentQuery);

    /**
     * Wpisuje do bazy tablice obiektow
     *
     * @param collection Nazwa kolekcji
     * @param dbArray    Tablica obiektow formacie JSON
     * @return Zwraca tablice flag, czy obiekt faktycznie prawidlowo zostal wpisany
     */
    ArrayList<Boolean> insertArray(String collection, String dbArray);

    /**
     * Na podstawie zefiniowanego zapytania znajduje dokumenty w bazie
     *
     * @param collection    Nazwa kolekcji
     * @param documentQuery Zapytanie do bazy
     * @return Lista dokumentów pobranych z bazy na podstawie zapytania
     */
    ArrayList<Document> find(String collection, Document documentQuery);

    /**
     * Na podstawie zefiniowanego zapytania znajduje w kolekcji dokumenty i zwraca posrotwane wyniki
     *
     * @param collection Nazwa kolekcji
     * @param documentQuery    Zapytanie do bazy
     * @param sorts            Zdefiniowana zasada sortowania
     * @return Posortowana lista dokumentów pobranych z bazy na podstawie zapytania
     */
    ArrayList<Document> find(String collection, Document documentQuery, Bson sorts);

    /**
     * Pobiera wszystkie elementy z zdefiniowanej kolekcji
     *
     * @param collection Nazwa kolekcji
     * @return Lista dokumentów pobranych z bazy na podstawie zapytania
     */
    ArrayList<Document> find(String collection);

    /**
     * Na podstawie zefiniowanego zapytania usuwa dokumenty z bazy
     *
     * @param collection Nazwa kolekcji
     * @param documentQuery    Zapytanie do bazy
     * @return Liczba usunietych elementow
     */
    long remove(String collection, Document documentQuery);

    /**
     * Usuwa kolekcje z bazy
     *
     * @param collection Nazwa kolekcji
     * @return Informacja czy operacja przebiegla pomyslnie
     */
    boolean dropCollection(String collection);
}
