package uek.ceneo.etl.services;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoWriteException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
}
