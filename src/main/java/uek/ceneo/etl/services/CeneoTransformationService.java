package uek.ceneo.etl.services;

import org.springframework.stereotype.Service;
import uek.ceneo.etl.models.Product;
import uek.ceneo.etl.models.Review;
import uek.ceneo.etl.utils.scrapper.CeneoOpinion;
import uek.ceneo.etl.utils.scrapper.CeneoProduct;

import java.util.List;

@Service("CeneoTransformationService")
public class CeneoTransformationService implements TransformationService {

    @Override
    public Review transformOpinion(CeneoOpinion ceneoOpinion) {
        String id = ceneoOpinion.getId().split("-")[3];
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
                ceneoOpinion.getReviewSummary()
        );
    }

    @Override
    public Product transformProduct(CeneoProduct ceneoProductImpl, List<String> reviews) {
        return new Product(
                ceneoProductImpl.getId(),
                ceneoProductImpl.getMark(),
                ceneoProductImpl.getModel(),
                ceneoProductImpl.getAdditionalRemarks(),
                ceneoProductImpl.getCategory(),
                reviews
        );
    }
}
