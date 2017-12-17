package uek.ceneo.etl.utils.scrapper;


public class CeneoOpinion {

    private String disadvantages;
    private String advantages;
    private String productReview;
    private double reviewScoreCount;
    private String reviewerName;
    private String reviewTime;
    private int voteYes;
    private int voteNo;
    private String reviewSummary;
    private String id;

    public CeneoOpinion(String id, String disadvantages, String advantages, String productReview,
                        String reviewScoreCount, String reviewerName, String reviewTime, String voteYes, String voteNo, String reviewSummary) {
        this.id = id;
        this.disadvantages = disadvantages;
        this.advantages = advantages;
        this.productReview = productReview;
        this.reviewScoreCount = Double.parseDouble(reviewScoreCount.replaceAll(",", ".").split("/")[0]);
        this.reviewerName = reviewerName;
        this.reviewTime = reviewTime;
        this.voteYes = Integer.parseInt(voteYes);
        this.voteNo = Integer.parseInt(voteNo);
        this.reviewSummary = reviewSummary;
    }

    public String getDisadvantages() {
        return disadvantages;
    }

    public String getAdvantages() {
        return advantages;
    }

    public String getProductReview() {
        return productReview;
    }

    public double getReviewScoreCount() {
        return reviewScoreCount;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public int getVoteYes() {
        return voteYes;
    }

    public int getVoteNo() {
        return voteNo;
    }

    public String getReviewSummary() {
        return reviewSummary;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "Wady: " + disadvantages + "\n" + "Zalety: " + advantages + "\n" + "Podsumowanie opinii: " + productReview + "\n" +
                "Liczba gwiazdek: " + reviewScoreCount + "\n" + "Autor opinii: " + reviewerName + "\n" + "Data wystawienia opinii: " + reviewTime + "\n" +
                "Polecam/niepolecam: " + reviewSummary + "\n" + "Opinie za przydatna: " + voteYes + "\n" + "Opinie za nieprzydatna: " + voteNo + "\n";
    }
}
