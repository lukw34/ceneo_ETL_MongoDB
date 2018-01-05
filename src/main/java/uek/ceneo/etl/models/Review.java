package uek.ceneo.etl.models;

import org.json.JSONObject;

/**
 * Klasa reprezentujaca poszczegolne opinie. Zawiera takie dane jak:
 * <ul>
 * <li>Wady produktu [disadvantages]</li>
 * <li>Zalety produktu [avantages] </li>
 * <li>Podsumowanie opinii [productReview]</li>
 * <li>Liczba gwiazdek [reviewScoreCount]</li>
 * <li>Autor opinii [reviewerName]</li>
 * <li>Data wystawienia opinii [reviewTime]</li>
 * <li>POLECAM/ NIE POLECAM [reviewSummary] </li>
 * <li>Ile osob uznalo opinie za przydatna [voteYes]</li>
 * <li>Ile osob uznalo opinie za nieprzyatna [voteNo]</li>
 * <li>Identyfikator produktu [productId]</li>
 * <li>Identyfikator opinii [id]</li>
 * </ul>
 *
 * @see uek.ceneo.etl.models.Review
 * @see uek.ceneo.etl.models.JSONModel
 */
public class Review implements JSONModel {
    /**
     * Wady produktu
     */
    private String disadvantages;
    /**
     * Zalety produktu
     */
    private String advantages;
    /**
     * Podsumowanie opinii
     */
    private String productReview;
    /**
     * Liczba gwiazdek
     */
    private double reviewScoreCount;
    /**
     * Autor opinii
     */
    private String reviewerName;
    /**
     * Data wystawienia opinii
     */
    private String reviewTime;
    /**
     * Ile osob uznalo opinie za przydatna
     */
    private int voteYes;
    /**
     * Ile osob uznalo opinie za nieprzydatna
     */
    private int voteNo;
    /**
     * Identyfikator produktu
     */
    private String productId;
    /**
     * POLECAM/ NIE POLECAM
     */
    private String reviewSummary;
    /**
     * Identyfikator opinii
     */
    private String id;

    /**
     * Konstruktor klasy Review
     *
     * @param id Identyfikator opinii
     * @param disadvantages Wady produktu
     * @param advantages Zalety produktu
     * @param productReview Podsumowanie opinii
     * @param reviewScoreCount Liczba gwiazdek
     * @param reviewerName Autor opinii
     * @param reviewTime Data wystawienia opinii
     * @param voteYes Ile osob uznalo opinie za przydatna
     * @param voteNo Ile osob uznalo opinie za nieprzydatna
     * @param reviewSummary POLECAM/ NIE POLECAM
     * @param productId Identyfiaktor produktu, ktorego opinie dotycza
     */
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
        this.reviewSummary = reviewSummary;
        this.voteYes = voteYes;
        this.voteNo = voteNo;
        this.productId = productId;
    }

    /**
     * Implementacja metody zwracajacej opinii w postaci ciagu znakow formatu JSON
     *
     * @return Reprezentacja opinii jako ciagu znakow
     */
    @Override
    public String toJSONString() {
        return toJSONObject().toString();
    }


    /**
     * Implementacja metody tworzacej obiekt o formacie JSON
     *
     * @return Reprezentacja opinii jako obieku JSON-owego
     */
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
