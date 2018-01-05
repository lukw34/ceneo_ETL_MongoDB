package uek.ceneo.etl.services;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * Klasa odpowiedzialna za obsluge bazy danych dla opinii i produktow uzupelnionych w procesie ETL
 *
 * @see org.springframework.stereotype.Service;
 */
@Service("CeneoMongoService")
public class CeneoMongoService implements MongoService {

    /**
     * Obiekt bazy danych, z ktora nawiazano polaczenie
     */
    private MongoDatabase ceneoDB;
    /**
     * Zmienne srodowiskowe
     */
    private Environment env;


    /**
     * Konstuktor serwisu.
     * Inicjalizuje serwisy potrzebne do ustanowienia polaczenia z baza danych
     *
     * @param env Zmienne srodowiskowe
     */
    @Autowired
    CeneoMongoService(Environment env) {
        String host = env.getProperty("mongodb.host");
        String port = env.getProperty("mongodb.port");
        String password = env.getProperty("mongodb.password");
        String userName = env.getProperty("mongodb.username");
        String database = env.getProperty("mongodb.database");
        ArrayList<MongoCredential> mongoCredentials = new ArrayList<>();
        if (userName != null) {
            MongoCredential credentials = MongoCredential.createCredential(userName, database, password.toCharArray());
            mongoCredentials.add(credentials);
        }

        ServerAddress serverAddress = new ServerAddress(host, Integer.parseInt(port));
        MongoClient mongoClient = new MongoClient(serverAddress, mongoCredentials);
        this.ceneoDB = mongoClient.getDatabase(database);
    }

    /**
     * @param list List zawierajaca dokumenty pobrane z bazy
     * @return Zwraca liste unikatowych elementow
     */
    private ArrayList<Document> makeUniqueList(ArrayList<Document> list) {
        return list.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(document -> document.get("id", String.class)))),
                        ArrayList::new));
    }

    /**
     * Wstawia obiekt do bazy danych zawierajacej informacje o produktach i opiniach sklepu ceneo
     *
     * @param dbCollection Nazwa kolekcji
     * @param object       Obiekt, który ma zostac wstawiony
     * @return Zwraca true jesli operacja zakonczyla sie sukcesem
     */
    @Override
    public boolean insert(String dbCollection, String object) {
        Document document = Document.parse(object);
        String id = (String) document.get("id");
        MongoCollection<Document> collection = this.ceneoDB.getCollection(dbCollection);
        try {
            collection.insertOne(document);
            return true;
        } catch (MongoWriteException e) {
            StringBuilder log = new StringBuilder();
            log.append("[Collection: ")
                    .append(dbCollection)
                    .append("] Obiekt o identyfikatorze: ")
                    .append(id)
                    .append(" juz istnieje");
            System.out.println(log.toString());
            return false;
        }
    }

    /**
     * Pobiera unikatowa liste elementow na postawie pola id
     *
     * @param dbCollectionName Nazwa kolekcji
     * @return Unikatowa lista dokumentów pobranych z bazy
     */
    @Override
    public ArrayList<Document> getUniqueList(String dbCollectionName) {
        return this.getUniqueList(dbCollectionName, new Document());
    }

    /**
     * Pobiera unikatowa liste elementow na postawie pola id na podstawie query
     *
     * @param collection    Nazwa kolekcji
     * @param documentQuery Zapytanie do bazy
     * @return Unikatowa lista dokumentów pobranych z bazy na podstawie zapytania
     */
    @Override
    public ArrayList<Document> getUniqueList(String collection, Document documentQuery) {
        return this.makeUniqueList(this.find(collection, documentQuery));
    }


    /**
     * Wpisuje do bazy tablice obiektow
     *
     * @param dbCollection Nazwa kolekcji
     * @param array        Tablica obiektow formacie JSON
     * @return Zwraca tablice flag, czy obiekt faktycznie prawidlowo zostal wpisany
     */
    @Override
    public ArrayList<Boolean> insertArray(String dbCollection, String array) {
        JSONArray jsonArray = new JSONArray(array);
        ArrayList<String> documents = new ArrayList<>();
        ArrayList<Boolean> results = new ArrayList<>();
        for (int index = 0; index < jsonArray.length(); index++) {
            documents.add(jsonArray.get(index).toString());
        }
        documents.forEach(document -> {
            Document jsonDocument = Document.parse(document);
            String id = (String) jsonDocument.get("id");
            jsonDocument.append("_id", id);
            results.add(this.insert("reviews", jsonDocument.toJson()));
        });
        return results;
    }

    /**
     * Na podstawie zefiniowanego zapytania znajduje w jednej z kolekcji bazy ceneo dokument
     *
     * @param dbCollectionName Nazwa kolekcji
     * @param documentQuery    Zapytanie do bazy
     * @return Lista dokumentów pobranych z bazy na podstawie zapytania
     */
    @Override
    public ArrayList<Document> find(String dbCollectionName, Document documentQuery) {
        MongoCollection<Document> collection = this.ceneoDB.getCollection(dbCollectionName);
        ArrayList<Document> queryResult = new ArrayList<>();
        collection.find(documentQuery).forEach((Block<? super Document>) queryResult::add);
        return queryResult;
    }

    /**
     * Na podstawie zefiniowanego zapytania znajduje w jednej z kolekcji bazy ceneo dokumenty. I zwraca je posrtowane
     *
     * @param dbCollectionName Nazwa kolekcji
     * @param documentQuery    Zapytanie do bazy
     * @param sorts            Zdefiniowana zasada sortowania
     * @return Posortowana lista dokumentów pobranych z bazy na podstawie zapytania
     */
    @Override
    public ArrayList<Document> find(String dbCollectionName, Document documentQuery, Bson sorts) {
        MongoCollection<Document> collection = this.ceneoDB.getCollection(dbCollectionName);
        ArrayList<Document> queryResult = new ArrayList<>();
        collection.find(documentQuery).sort(sorts).forEach((Block<? super Document>) queryResult::add);
        return queryResult;
    }

    /**
     * Pobiera wszystkie elementy z zdefiniowanej kolekcji
     *
     * @param collection Nazwa kolekcji
     * @return Lista dokumentów pobranych z bazy na podstawie zapytania
     */
    @Override
    public ArrayList<Document> find(String collection) {
        return this.find(collection, new Document());
    }

    /**
     * Na podstawie zefiniowanego zapytania usuwa dokumenty z bazy
     *
     * @param dbCollectionName Nazwa kolekcji
     * @param documentQuery    Zapytanie do bazy
     * @return Liczba usunietych elementow
     */
    @Override
    public long remove(String dbCollectionName, Document documentQuery) {
        MongoCollection<Document> collection = this.ceneoDB.getCollection(dbCollectionName);
        return collection.deleteMany(documentQuery).getDeletedCount();
    }

    /**
     * Usuwa kolekcje z bazy ceneo
     *
     * @param dbCollectionName Nazwa kolekcji
     * @return Informacja czy operacja przebiegla pomyslnie
     */
    @Override
    public boolean dropCollection(String dbCollectionName) {
        try {
            MongoCollection<Document> collection = this.ceneoDB.getCollection(dbCollectionName);
            collection.drop();
            return true;
        } catch (Exception e) {
            StringBuilder log = new StringBuilder();
            log.append("[Collection: ")
                    .append(dbCollectionName)
                    .append("] Kolekcja nie istnieje.");
            System.out.println(log.toString());
            return false;
        }
    }
}
