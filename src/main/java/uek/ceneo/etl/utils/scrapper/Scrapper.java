package uek.ceneo.etl.utils.scrapper;

import org.jsoup.select.Elements;

import javax.lang.model.element.Element;

public class Scrapper {

    private CeneoConnection connection;
    private Parser pageParser;

    public Scrapper(String productId) {
        this.connection = CeneoConnection.getInstance();
        this.connection.setId(productId);
        this.connection.setConnection();
        this.pageParser = new CeneoParser(this.connection.getDocument());
    }

    public String getDocument() {
        return this.connection.getDocument().html();
    }

    public void getProduct(String id) {
        pageParser.parse();
    }
}
