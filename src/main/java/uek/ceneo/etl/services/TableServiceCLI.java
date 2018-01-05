package uek.ceneo.etl.services;

import org.bson.Document;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasa odpowiedzialna za obsluge  tablic prezentujacych dane uzytkownikowi
 *
 * @see org.springframework.stereotype.Service;
 */
@Service("TableServiceCLI")
public class TableServiceCLI implements TableService {

    /**
     * Zwraca gotowa do wyswietlenia tablice. Generowana jest ona na podstawie przeslanych atrybutow,
     * ktre maja zostac wyswietlone
     *
     * @param doc        Dane do wyswietlenia
     * @param properties Atrybuty, ktore maja zostac pokazane uzytkownikowi
     * @return Tablica jako ciag znakow
     */
    @Override
    public String getTable(ArrayList<Document> doc, ArrayList<String> properties) {
        Function<Document, String[]> generateRow = document -> {
            ArrayList<String> row = new ArrayList<>();
            properties.forEach(property -> row.add(document.get(property).toString()));
            return row.toArray(new String[row.size()]);
        };
        String[][] tableData = doc.stream()
                .map(generateRow)
                .collect(Collectors.toList())
                .toArray(new String[doc.size()][]);
        String[][] headers = new String[1][properties.size()];
        headers[0] = properties.toArray(new String[properties.size()]);

        TableModel model = new ArrayTableModel(Stream.of(headers, tableData).flatMap(Stream::of)
                .toArray(String[][]::new));
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addFullBorder(BorderStyle.fancy_heavy);
        return tableBuilder.build().render(120);
    }
}
