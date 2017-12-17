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

   CeneoProduct getProductInformation(Document doc) {
        Parser pageParser = new CeneoParser(doc);
        return (CeneoProduct) pageParser.parse();
    }

    public CeneoProduct getProductInformation(String htmlDocument) {
        return this.getProductInformation(Jsoup.parse(htmlDocument));
    }
}
