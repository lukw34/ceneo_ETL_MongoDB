package uek.ceneo.etl.utils.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CeneoParser implements Parser<Product> {
    private Document doc;


    public CeneoParser(Document doc) {
        this.doc = doc;
    }

    private void opinionParsing(Product product) {
        String disadvantages, advantages, productReview = "", reviewScoreCount = "", reviewerName = "", reviewTime = "",
                voteYes = "", voteNo = "", reviewSummary = "";
        Elements listOfOpinions = doc.select("ol.product-reviews li.review-box");
        Elements pageNumbers = doc.select("div.pagination ul");
        List<String> links = new ArrayList<>();
        for (Element link : pageNumbers) {
            links.addAll(link.select("li a[href*=/" + CeneoConnection.getInstance().getId() + "]").eachAttr("href"));
        }
        List<String> linksCopy = links.stream().distinct().collect(Collectors.toList());
        for (Element el : listOfOpinions) {
            disadvantages = el.select("div.cons-cell ul li").text();
            advantages = el.select("div.pros-cell ul li").text();
            productReview = el.select("p.product-review-body").first().text();
            reviewScoreCount = el.select("span.review-score-count").text();
            reviewerName = el.select("div.reviewer-name-line").text();
            reviewTime = el.select("span.review-time time").attr("datetime");
            voteYes = el.select("button.vote-yes span").text();
            voteNo = el.select("button.vote-no span").text();
            reviewSummary = el.select("div.product-review-summary em").text();

            product.addOpinion(new Opinion(disadvantages, advantages, productReview, reviewScoreCount, reviewerName,
                    reviewTime, voteYes, voteNo, reviewSummary));
        }

        for (int i = 0; i < linksCopy.size(); i++) {
            try {
                doc = Jsoup.connect("https://www.ceneo.pl" + linksCopy.get(i)).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            listOfOpinions = doc.select("ol.product-reviews li.review-box");
            pageNumbers = doc.select("div.pagination ul");
            for (Element el : listOfOpinions) {
                disadvantages = el.select("div.cons-cell ul li").text();
                advantages = el.select("div.pros-cell ul li").text();
                productReview = el.select("p.product-review-body").first().text();
                reviewScoreCount = el.select("span.review-score-count").text();
                reviewerName = el.select("div.reviewer-name-line").text();
                reviewTime = el.select("span.review-time time").attr("datetime");
                voteYes = el.select("button.vote-yes span").text();
                voteNo = el.select("button.vote-no span").text();
                reviewSummary = el.select("div.product-review-summary em").text();
                product.addOpinion(new Opinion(disadvantages, advantages, productReview, reviewScoreCount, reviewerName, reviewTime, voteYes, voteNo, reviewSummary));
            }
            for (Element el : pageNumbers) {
                links.add(el.select("li a[href*=/" + CeneoConnection.getInstance().getId() + "]").last().attr("href"));
                linksCopy = links.stream().distinct().collect(Collectors.toList());
            }

        }
    }

    private void titleParsing(Product product) {
        String type = "";
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
            type = el.select("span.breadcrumb span").eq(1).text();
        }

        product.setProperties(type, mark, model.toString(), additionalRemarks);
    }


    private String getCategory() {

        Elements elements = this.doc.select("div.wrapper").select("nav.breadcrumbs");

        String category = null;

        for (Element el : elements) {
            System.out.println(category);
            category = el.select("span.breadcrumb span").eq(1).text();
        }

        return category;
    }

    @Override
    public Product parse() {
        String category = getCategory();
        Product product = new CeneoProduct(category);
        this.opinionParsing(product);
        this.titleParsing(product);

        return product;
    }
}
