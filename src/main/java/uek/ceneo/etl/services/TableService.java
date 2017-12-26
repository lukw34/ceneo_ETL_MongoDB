package uek.ceneo.etl.services;

import org.bson.Document;
import org.springframework.shell.table.Table;

import java.util.ArrayList;

public interface TableService {
    Table getTable(ArrayList<Document> doc, ArrayList<String> properties);
}
