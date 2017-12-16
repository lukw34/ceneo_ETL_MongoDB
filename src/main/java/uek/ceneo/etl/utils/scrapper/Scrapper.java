package uek.ceneo.etl.utils.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.lang.model.element.Element;

public class Scrapper {

    private CeneoConnection connection;

    public Scrapper(String productId) {
        this.connection = CeneoConnection.getInstance();
        this.connection.setId(productId);
        this.connection.setConnection();
    }

    public String getDocument() {
        return this.connection.getDocument().html();
    }

   void getProductInformation(Document doc) {
        Parser pageParser = new CeneoParser(doc);
        pageParser.parse();
    }

    public void getProductInformation(String htmlDocument) {
        this.getProductInformation(Jsoup.parse(htmlDocument));
    }
}
