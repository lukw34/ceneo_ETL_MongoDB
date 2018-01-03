package uek.ceneo.etl.services;

import org.bson.Document;

import java.util.ArrayList;

public interface TableService {
    String getTable(ArrayList<Document> doc, ArrayList<String> properties);
}
