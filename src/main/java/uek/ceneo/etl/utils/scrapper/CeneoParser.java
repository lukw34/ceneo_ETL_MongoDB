package uek.ceneo.etl.utils.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parser pliku html zawierajacego dane z serwisu ceneo
 */
public class CeneoParser implements Parser<CeneoProduct> {
    private Document doc;


    /**
     * Konstruktor klasy
     *
     * @param doc Dokument do analizy
     */
    CeneoParser(Document doc) {
        this.doc = doc;
    }


    private void parseSingleOpinionPage(Elements listOfOpinions, CeneoProduct ceneoProduct) {
        for (Element element : listOfOpinions) {
            String disadvantages = element.select("div.cons-cell ul li").text();
            String advantages = element.select("div.pros-cell ul li").text();
            String productReview = element.select("p.product-review-body").first().text();
            String reviewScoreCount = element.select("span.review-score-count").text();
            String reviewerName = element.select("div.reviewer-name-line").text();
            String reviewTime = element.select("span.review-time time").attr("datetime");
            String voteYes = element.select("button.vote-yes span").text();
            String voteNo = element.select("button.vote-no span").text();
            String reviewSummary = element.select("div.product-review-summary em").text();
            String id = element.select("div.js_product-review-comments").first().attr("id");

            ceneoProduct.addOpinion(new CeneoOpinion(id, disadvantages, advantages, productReview, reviewScoreCount, reviewerName,
                    reviewTime, voteYes, voteNo, reviewSummary));
        }
    }

    private void opinionParsing(CeneoProduct ceneoProduct) {
        String disadvantages, advantages, productReview = "", reviewScoreCount = "", reviewerName = "", reviewTime = "",
                voteYes = "", voteNo = "", reviewSummary = "", id;
        Elements listOfOpinions = doc.select("ol.product-reviews li.review-box");
        Elements pageNumbers = doc.select("div.pagination ul");
        List<String> links = new ArrayList<>();
        for (Element link : pageNumbers) {
            links.addAll(link.select("li a[href*=/" + CeneoConnection.getInstance().getId() + "]").eachAttr("href"));
        }
        List<String> linksCopy = links.stream().distinct().collect(Collectors.toList());
        parseSingleOpinionPage(listOfOpinions, ceneoProduct);

        for (int i = 0; i < linksCopy.size(); i++) {
            try {
                doc = Jsoup.connect("https://www.ceneo.pl" + linksCopy.get(i)).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            listOfOpinions = doc.select("ol.product-reviews li.review-box");
            pageNumbers = doc.select("div.pagination ul");
            parseSingleOpinionPage(listOfOpinions, ceneoProduct);
            for (Element el : pageNumbers) {
                links.add(el.select("li a[href*=/" + CeneoConnection.getInstance().getId() + "]").last().attr("href"));
                linksCopy = links.stream().distinct().collect(Collectors.toList());
            }

        }
    }

    private void titleParsing(CeneoProduct product) {
        String mark = "";
        StringBuilder model = new StringBuilder();
        String additionalRemarks = "";

        Elements elem = doc.select("div.wrapper").select("div.product-content");
        Elements elem2 = doc.select("div.wrapper").select("nav.breadcrumbs");

        for (Element el : elem) {
            additionalRemarks = el.select("div.ProductSublineTags").text();
        }
        for (Element el : elem2) {
            String[] temp = el.select("nav.breadcrumbs strong").text().split(" ");
            int i = 1;
            mark = temp[0];
            while (i < temp.length) {
                model.append(temp[i]).append(" ");
                i++;
            }
        }

        product.setProperties(mark, model.toString(), additionalRemarks);
    }


    private String getCategory() {

        Elements elements = this.doc.select("div.wrapper").select("nav.breadcrumbs");

        String category = null;

        for (Element el : elements) {
            category = el.select("span.breadcrumb span").eq(1).text();
        }

        return category;
    }

    /**
     * <p>Główna metoda odpowiedzialna za analize danych z serwisu ceneo.</p>
     * <p> Na podstawie selektorów pobierane sa dane o produkcie, a nastepnie o wszystkich opiniach przypisanych do niego.</p>
     *
     * @return Produkt z jego opiniami
     */
    @Override
    public CeneoProduct parse() {
        String category = getCategory();
        CeneoProduct ceneoProduct = new CeneoProductImpl(CeneoConnection.getInstance().getId(), category);
        this.opinionParsing(ceneoProduct);
        this.titleParsing(ceneoProduct);

        return ceneoProduct;
    }
}
