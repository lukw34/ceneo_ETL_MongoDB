package uek.ceneo.etl.services;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoWriteException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
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
        document.append("_id", id);
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
        Document document =new Document("array", JSON.parse(array));
        System.out.println(document.values().size());
        document.values().forEach(System.out::println);

        return null;
    }
}
