package uek.ceneo.etl.services;

import org.bson.Document;
import org.springframework.shell.table.Table;

import java.util.ArrayList;

/**
 * Interfejs do tworzenia tablic prezentujacych dane
 */
public interface TableService {
    /**
     * Zwraca gotowa do wyswietlenia tablice
     *
     * @param doc Dane do wyswietlenia
     * @param properties Atrybuty, ktore maja zostac pokazane uzytkownikowi
     * @return Tablica jako ciag znakow
     */
    Table getTable(ArrayList<Document> doc, ArrayList<String> properties);
}
