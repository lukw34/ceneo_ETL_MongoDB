package uek.ceneo.etl.services;

import org.bson.Document;

import java.util.ArrayList;

public interface CSVService {
    String build(ArrayList<Document> documents);
}
