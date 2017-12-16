package uek.ceneo.etl.utils.scrapper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CeneoConnection {

    private final String URL = "https://www.ceneo.pl/";
    private String id = null;
    private Document doc = null;
    private static CeneoConnection instance;

    public static CeneoConnection getInstance() {
        if (instance == null) {
            instance = new CeneoConnection();
        }
        return instance;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Ustawia połączenie z konkretną strona internetowa
     */
    void setConnection() {
        String fullUrl = URL + id;
        try {
            doc = Jsoup.connect(fullUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter dla identyfikatora produktu
     *
     * @return Identyfikator produktu
     */
    String getId() {
        return id;
    }

    /**
     * Getter dla okumentu
     *
     * @return Objekt reprezentujacy HTML-a
     */
    public Document getDocument() {
        return doc;
    }
}
