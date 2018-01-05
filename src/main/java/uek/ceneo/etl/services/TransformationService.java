package uek.ceneo.etl.services;

import uek.ceneo.etl.models.Product;
import uek.ceneo.etl.models.Review;
import uek.ceneo.etl.utils.scrapper.CeneoOpinion;
import uek.ceneo.etl.utils.scrapper.CeneoProduct;

import java.util.List;

/**
 * Interfejs do obslugi transformacji danych do zdefiniowanych modeli
 */
public interface TransformationService {
    /**
     * Przeksztalca opinie do zdefiniowanego modelu
     *
     * @param ceneoOpinion Opinia zwroca przez 'scrappera'
     * @param id           Identyfikator produktu
     * @return Gotowy model opinii
     */
    Review transformOpinion(CeneoOpinion ceneoOpinion, String id);

    /**
     * Przeksztalca produkt do zdefiniowanego modelu
     *
     * @param ceneoProductImpl Produkt zwrocony przez 'scrapper'
     * @param reviews          Lista przypisanych opinii
     * @return Gotowy model produktu
     */
    Product transformProduct(CeneoProduct ceneoProductImpl, List<String> reviews);
}
