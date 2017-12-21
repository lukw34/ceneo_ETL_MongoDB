package uek.ceneo.etl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uek.ceneo.etl.models.Product;
import uek.ceneo.etl.models.Review;
import uek.ceneo.etl.models.ReviewList;
import uek.ceneo.etl.utils.scrapper.CeneoProduct;
import uek.ceneo.etl.utils.scrapper.Scrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("ETLCeneoService")
public class ETLCeneo implements ETL {

    private final FileService fileService;
    private final TransformationService ceneoTransformationService;
    private final MongoService ceneoMongoService;


    @Autowired
    public ETLCeneo(FileService fileService, TransformationService ceneoTransformationService, MongoService ceneoMongoService) {
        this.ceneoMongoService = ceneoMongoService;
        this.ceneoTransformationService = ceneoTransformationService;
        this.fileService = fileService;
    }

    @Override
    public String extract(String id) {
        Scrapper ceneoScrapper = new Scrapper(id);
        long size = fileService.write(id + ".html", ceneoScrapper.getDocument());
        StringBuilder log = new StringBuilder();
        log.append("Pobrano plik html o rozmiarze: ").append(size).append(" kb\n");
        return log.toString();
    }

    @Override
    public String transform(String id) {
        String htmlDocument = fileService.read(id + ".html");
        fileService.clear(id + ".html");
        StringBuilder log = new StringBuilder();
        if (htmlDocument.length() > 0) {
            Scrapper ceneoScrapper = new Scrapper(id);
            ReviewList reviewList = new ReviewList();
            CeneoProduct ceneoProduct = ceneoScrapper.getProductInformation(htmlDocument);
            List<Review> reviews = ceneoProduct.getCeneoOpinions()
                    .stream()
                    .map(this.ceneoTransformationService::transformOpinion)
                    .collect(Collectors.toList());
            List<String> reviewsId = reviews.stream().map(review -> (String) review.toJSONObject().get("id")).collect(Collectors.toList());
            Product product = this.ceneoTransformationService.transformProduct(ceneoProduct, reviewsId);
            reviews.forEach(reviewList::insertReview);
            long productSize = fileService.write("product_" + id, product.toJSONString());
            long reviewsSize = fileService.write("reviews_" + id, reviewList.toJSONString());
            log.append("Produkt zawiera ").append(reviews.size()).append(" opinie.\n");
            log.append("Stworzono: product_").append(id).append(".json\n");
            log.append("Stworzono: reviews_").append(id).append(".json\n");
        } else {
            log.append("Brak danych do wykonania procesu Transform !!\n");
        }

        return log.toString();
    }

    @Override
    public String load(String id) {
        String jsonProduct = fileService.read("product_" + id);
        String jsonReviews = fileService.read("reviews_" + id);
        fileService.clear("product_" + id);
        fileService.clear("reviews_" + id);
        boolean isInsertProduct = ceneoMongoService.insert("products", jsonProduct);
        ArrayList<Boolean> isOpinionsInserted = ceneoMongoService.insertArray("reviews", jsonReviews);
        StringBuilder log = new StringBuilder();
        if(isInsertProduct) {
            log.append("Wpisano produkt o id ").append(id).append("\n");
        }

        log.append("Wpisano ").append(isOpinionsInserted.stream().filter(flag -> flag).count()).append(" opinii.\n");
        return log.toString();
    }
}
