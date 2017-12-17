package uek.ceneo.etl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uek.ceneo.etl.models.Product;
import uek.ceneo.etl.models.Review;
import uek.ceneo.etl.models.ReviewList;
import uek.ceneo.etl.utils.scrapper.CeneoProduct;
import uek.ceneo.etl.utils.scrapper.Scrapper;

import java.util.List;
import java.util.stream.Collectors;

@Service("ETLCeneoService")
public class ETLCeneo implements ETL {

    private final FileService fileService;
    private final TransformationService ceneoTransformationService;


    @Autowired
    public ETLCeneo(FileService fileService, TransformationService ceneoTransformationService) {
        this.ceneoTransformationService = ceneoTransformationService;
        this.fileService = fileService;
    }

    @Override
    public void extract(String id) {
        Scrapper ceneoScrapper = new Scrapper(id);
        fileService.write(id + ".html", ceneoScrapper.getDocument());
    }

    @Override
    public void transform(String id) {
        String htmlDocument = fileService.read(id + ".html");
//        fileService.clear(id + ".html");
        Scrapper ceneoScrapper = new Scrapper(id);
        ReviewList reviewList = new ReviewList();
        CeneoProduct ceneoProduct = ceneoScrapper.getProductInformation(htmlDocument);
        Product product = this.ceneoTransformationService.transformProduct(ceneoProduct);
        List<Review> reviews = ceneoProduct.getCeneoOpinions()
                .stream()
                .map(this.ceneoTransformationService::transformOpinion)
                .collect(Collectors.toList());
        reviews.forEach(review -> review.setProuctId(product.getId()));
        reviews.forEach(reviewList::insertReview);
        fileService.write("product_" + id, product.toJSONString());
        fileService.write("reviews_" + id, reviewList.toJSONString());
    }

    @Override
    public void load(String id) {

    }
}
