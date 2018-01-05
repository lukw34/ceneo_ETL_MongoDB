package uek.ceneo.etl.utils.scrapper;


/**
 * Klasa reprezentujaca dane pobrane z pliku html
 */
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

    /**
     * Konstruktor klasy
     *
     * @param id               Identyfikator opinii
     * @param disadvantages    Wady produktu
     * @param advantages       Zalety produktu
     * @param productReview    Podsumowanie opinii
     * @param reviewScoreCount Liczba gwiazdek
     * @param reviewerName     Autor opinii
     * @param reviewTime       Data wystawienia opinii
     * @param voteYes          Ile osob uznalo opinie za przydatna
     * @param voteNo           Ile osob uznalo opinie za nieprzydatna
     * @param reviewSummary    POLECAM/ NIE POLECAM
     */
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

    /**
     * Pobiera wady przypisane do produktu
     *
     * @return Wady produktu
     */
    public String getDisadvantages() {
        return disadvantages;
    }

    /**
     * Pobiera zalety produktu
     *
     * @return Zalety produktu
     */
    public String getAdvantages() {
        return advantages;
    }

    /**
     * Pobiera podsumowanie opinii
     *
     * @return Podsumowanie opinii
     */
    public String getProductReview() {
        return productReview;
    }

    /**
     * Pobiera liczbe gwiazdek
     *
     * @return Liczba gwiazdek
     */
    public double getReviewScoreCount() {
        return reviewScoreCount;
    }

    /**
     * Pobiera autora opinii
     *
     * @return Autor
     */
    public String getReviewerName() {
        return reviewerName;
    }

    /**
     * Pobiera date wystawienia opinii
     *
     * @return Data wystawienia opinii
     */
    public String getReviewTime() {
        return reviewTime;
    }

    /**
     * Pobiera ile osob uznalo opinie za przydatna
     *
     * @return Ile osob uznalo opinie za przydatna
     */
    public int getVoteYes() {
        return voteYes;
    }

    /**
     * Pobiera ie osob uznalo opinie za nieprzydatna
     *
     * @return Ile osob uznalo opinie za nieprzydatna
     */
    public int getVoteNo() {
        return voteNo;
    }

    /**
     * Pobiera POLECAM/ NIE POLECAM
     *
     * @return POLECAM/ NIE POLECAM
     */
    public String getReviewSummary() {
        return reviewSummary;
    }

    /**
     * Pobiera identyfikator produktu
     *
     * @return identyfikator produktu
     */
    public String getId() {
        return id;
    }
}
