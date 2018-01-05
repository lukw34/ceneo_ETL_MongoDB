package uek.ceneo.etl.utils.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Klasa odpowiedzialna za ustanowienie polaczanie ze strona internetową.
 * Zastosowano tutaj wzorzec projektowy o nazwie singleton.
 */
public class CeneoConnection {

    private final String URL = "https://www.ceneo.pl/";
    private String id = null;
    private Document doc = null;
    private static CeneoConnection instance;

    /**
     * Pobiera jedyna instancje klasy
     *
     * @return Zwraca instancje klasy
     */
    static CeneoConnection getInstance() {
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
