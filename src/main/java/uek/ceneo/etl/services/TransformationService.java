package uek.ceneo.etl.services;

import uek.ceneo.etl.models.Review;
import uek.ceneo.etl.models.Product;
import uek.ceneo.etl.utils.scrapper.CeneoOpinion;
import uek.ceneo.etl.utils.scrapper.CeneoProduct;

public interface TransformationService {
    Review transformOpinion(CeneoOpinion ceneoOpinion);

    Product transformProduct(CeneoProduct ceneoProductImpl);
}