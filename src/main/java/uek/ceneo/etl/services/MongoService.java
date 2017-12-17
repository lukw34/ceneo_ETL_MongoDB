package uek.ceneo.etl.services;

import java.util.ArrayList;

public interface MongoService {
    boolean insert(String collection, String dbObject);

    ArrayList<Boolean> insertArray(String collection, String dbArray);
}
