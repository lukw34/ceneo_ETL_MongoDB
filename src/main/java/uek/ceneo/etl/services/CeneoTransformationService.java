package uek.ceneo.etl.services;

import org.springframework.stereotype.Service;
import uek.ceneo.etl.models.Product;
import uek.ceneo.etl.models.Review;
import uek.ceneo.etl.utils.scrapper.CeneoOpinion;
import uek.ceneo.etl.utils.scrapper.CeneoProduct;

import java.util.List;

/**
 * Klasa odpowiedzialna za przeksztalcenie poszczegolnych informacji do modeli bazy danych.
 *
 * @see org.springframework.stereotype.Service;
 */
@Service("CeneoTransformationService")
public class CeneoTransformationService implements TransformationService {

    /**
     * Przeksztalca opinie wyciagniete z pliku html do zdefiniowanego modelu danych
     *
     * @param ceneoOpinion Opinia pobrana z pliku html
     * @param productId    Identyfikator produktu
     * @return Gotowy obiekt, ktory mozna wpisac do bazy danych
     */
    @Override
    public Review transformOpinion(CeneoOpinion ceneoOpinion, String productId) {
        String id = ceneoOpinion.getId().split("-")[3] + "_" + productId;
        return new Review(
                id,
                ceneoOpinion.getDisadvantages(),
                ceneoOpinion.getAdvantages(),
                ceneoOpinion.getProductReview(),
                ceneoOpinion.getReviewScoreCount(),
                ceneoOpinion.getReviewerName(),
                ceneoOpinion.getReviewTime(),
                ceneoOpinion.getVoteYes(),
                ceneoOpinion.getVoteNo(),
                ceneoOpinion.getReviewSummary(),
                productId
        );
    }


    /**
     * Przeksztalca opinie wyciagniete z pliku html do zdefiniowanego modelu danych
     *
     * @param ceneoProductImpl Informacje o produkcie pobrana z pliku html
     * @param reviews          Lista identyfikatorow opinii przypisana do produktu
     * @return Gotowy obiekt, ktory mozna wpisac do bazy danych
     */
    @Override
    public Product transformProduct(CeneoProduct ceneoProductImpl, List<String> reviews) {
        return new Product(
                ceneoProductImpl.getId(),
                ceneoProductImpl.getMark(),
                ceneoProductImpl.getModel(),
                ceneoProductImpl.getAdditionalRemarks(),
                ceneoProductImpl.getCategory(),
                reviews,
                ceneoProductImpl.getName()
        );
    }
}
