package uek.ceneo.etl.services;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public interface MongoService {
    boolean insert(String collection, String dbObject);

    ArrayList<Document> getUniqueList(String collection);

    ArrayList<Document> getUniqueList(String collection, Document documentQuery);

    ArrayList<Boolean> insertArray(String collection, String dbArray);

    ArrayList<Document> find(String collection, Document documentQuery);

    ArrayList<Document> find(String collection, Document documentQuery, Bson sorts);

    ArrayList<Document> find(String collection);

    long remove(String collection, Document documentQuery);

    boolean dropCollection(String collection);
}
