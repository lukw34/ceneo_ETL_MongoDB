package uek.ceneo.etl.services;

import org.bson.Document;
import org.springframework.shell.table.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("TableServiceCLI")
public class TableServiceCLI implements TableService {

    @Override
    public Table getTable(ArrayList<Document> doc, ArrayList<String> properties) {
        Function<Document, String[]> generateRow = document -> {
            ArrayList<String> row = new ArrayList<>();
            properties.forEach(property -> row.add(document.get(property, String.class)));
            return row.toArray(new String[row.size()]);
        };
        String[][] tableData = doc.stream()
                .map(generateRow)
                .collect(Collectors.toList())
                .toArray(new String[doc.size()][]);

        TableModel model = new ArrayTableModel(tableData);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addFullBorder(BorderStyle.fancy_heavy);
        return tableBuilder.build();
    }
}
