package uek.ceneo.etl.models;

import org.json.JSONObject;

import java.util.Calendar;

public class Review implements JSONModel {

    private String disadvantages;
    private String advantages;
    private String productReview;
    private double reviewScoreCount;
    private String reviewerName;
    private String reviewTime;
    private int voteYes;
    private int voteNo;
    private String productId;
    private String reviewSummary;
    private String id;

    public Review(String id, String disadvantages, String advantages, String productReview,
                  double reviewScoreCount, String reviewerName, String reviewTime, int voteYes,
                  int voteNo, String reviewSummary, String productId) {
        this.id = id;
        this.disadvantages = disadvantages;
        this.advantages = advantages;
        this.productReview = productReview;
        this.reviewScoreCount = reviewScoreCount;
        this.reviewerName = reviewerName;
        this.reviewTime = reviewTime;
        this.voteYes = voteYes;
        this.voteNo = voteNo;
        this.reviewSummary = reviewSummary;
        this.productId = productId;
    }


    @Override
    public String toJSONString() {
        return toJSONObject().toString();
    }

    @Override
    public JSONObject toJSONObject() {
        return new JSONObject()
                .put("id", id)
                .put("disadvantages", disadvantages)
                .put("advantages", advantages)
                .put("productReview", productReview)
                .put("reviewScoreCount", reviewScoreCount)
                .put("reviewerName", reviewerName)
                .put("reviewTime", reviewTime)
                .put("voteYes", voteYes)
                .put("voteNo", voteNo)
                .put("reviewSummary", reviewSummary)
                .put("productId", productId);
    }

}
