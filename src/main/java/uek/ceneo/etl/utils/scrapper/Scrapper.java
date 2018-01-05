package uek.ceneo.etl.utils.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Klasa odpowiedzialna za udostepnienie metod umozliwiajacch analize danych odnosnie konkretnego produktu
 */
public class Scrapper {

    private CeneoConnection connection;

    /**
     * Konstruktor klasy
     *
     * @param productId Identyikator produktu
     */
    public Scrapper(String productId) {
        this.connection = CeneoConnection.getInstance();
        this.connection.setId(productId);
        this.connection.setConnection();
    }

    /**
     * Pobiera plik html
     *
     * @return Plik html
     */
    public String getDocument() {
        return this.connection.getDocument().html();
    }

    /**
     * Pobiera informacje o produkcie zawarte w przeslanym dokumencie.
     *
     * @param doc Dokument do analizy
     * @return Informacje o produkcie
     */
    CeneoProduct getProductInformation(Document doc) {
        Parser pageParser = new CeneoParser(doc);
        return (CeneoProduct) pageParser.parse();
    }

    /**
     * Pobiera informacje o produkcie zawarte w ciagu znakow.
     *
     * @param htmlDocument Ciag znakow
     * @return Informacje o produkcie
     */
    public CeneoProduct getProductInformation(String htmlDocument) {
        return this.getProductInformation(Jsoup.parse(htmlDocument));
    }
}
