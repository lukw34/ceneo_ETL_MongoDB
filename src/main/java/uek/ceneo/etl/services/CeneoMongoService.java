package uek.ceneo.etl.services;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service("CeneoMongoService")
public class CeneoMongoService implements MongoService {

    private MongoDatabase ceneoDB;

    @Autowired
    private Environment env;

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

    private ArrayList<Document> makeUniqueList(ArrayList<Document> list) {
        return list.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(document -> document.get("id", String.class)))),
                        ArrayList::new));
    }

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

    @Override
    public ArrayList<Document> getUniqueList(String dbCollectionName) {
        return this.getUniqueList(dbCollectionName, new Document());
    }

    @Override
    public ArrayList<Document> getUniqueList(String collection, Document documentQuery) {
       return this.makeUniqueList(this.find(collection, documentQuery));
    }


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

    @Override
    public ArrayList<Document> find(String dbCollectionName, Document documentQuery) {
        MongoCollection<Document> collection = this.ceneoDB.getCollection(dbCollectionName);
        ArrayList<Document> queryResult = new ArrayList<>();
        collection.find(documentQuery).forEach((Block<? super Document>) queryResult::add);
        return queryResult;
    }

    @Override
    public ArrayList<Document> find(String collection) {
        return this.find(collection, new Document());
    }

    @Override
    public long remove(String dbCollectionName, Document documentQuery) {
        MongoCollection<Document> collection = this.ceneoDB.getCollection(dbCollectionName);
        return collection.deleteMany(documentQuery).getDeletedCount();
    }

    @Override
    public boolean dropCollection(String dbCollectionName) {
        try {
            MongoCollection<Document> collection = this.ceneoDB.getCollection(dbCollectionName);
            collection.drop();
            return true;
        } catch (Exception e){
            StringBuilder log = new StringBuilder();
            log.append("[Collection: ")
                    .append(dbCollectionName)
                    .append("] Kolekcja nie istnieje.");
            System.out.println(log.toString());
            return false;
        }
    }
}
