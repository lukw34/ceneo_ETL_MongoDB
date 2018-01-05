package uek.ceneo.etl.services;

import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa odpowiedzialna za generowanie plikow w formacie csv
 *
 * @see org.springframework.stereotype.Service;
 */
@Service("CSVService")
public class GenericCSVService implements CSVService {

    /**
     * Tworzy dokument csv w oparciu o dane pobrane z bazy ceneo
     *
     * @param documents Lista dokumentow pobranych z bazy ceneo
     * @return Dane w formacie csv
     */
    @Override
    public String build(ArrayList<Document> documents) {
        List<String> rows = documents.stream().map(document -> {
            Collection<Object> valuesCollection = document.values();
            return String.join(",", valuesCollection.stream().map(Object::toString).collect(Collectors.toList()).toArray(new String[valuesCollection.size()]));
        }).collect(Collectors.toList());

        return String.join("\n", rows);
    }
}
