package uek.ceneo.etl.services;

import org.bson.Document;

import java.util.ArrayList;

/**
 * Interfejs umozliwiajacy tworzenie pliku csv
 */
public interface CSVService {
    /**
     * Tworzy dokument csv
     *
     * @param documents Lista dokumentow pobranych z bazy
     * @return Dane w formacie csv
     */
    String build(ArrayList<Document> documents);
}
