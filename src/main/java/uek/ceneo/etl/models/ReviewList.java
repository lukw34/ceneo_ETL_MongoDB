package uek.ceneo.etl.models;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Klasa reprezentujaca model opowiadajacy tablicy opinii
 *
 * @see uek.ceneo.etl.models.JSONModel
 */
public class ReviewList implements JSONModel<JSONArray> {
    /**
     * Lista opinii
     */
    private ArrayList<JSONModel> reviews;

    /**
     * Tworzy pusta liste
     */
    public ReviewList() {
        reviews = new ArrayList<>();
    }

    /**
     * Doaje opinie o listy
     *
     * @param review Nowa opinia
     */
    public void insertReview(JSONModel review) {
        reviews.add(review);
    }

    /**
     * Implementacja metody zwracajacej liste opinii w postaci ciagu znakow formatu JSON
     *
     * @return Reprezentacja tablicy opinii jako ciagu znakow
     */
    @Override
    public String toJSONString() {
        return toJSONObject().toString();
    }

    /**
     * Implementacja metody tworzacej tablice o formacie JSON
     *
     * @return Reprezentacja listy opinii w postaci tablicy JSON-owej
     */
    @Override
    public JSONArray toJSONObject() {
        JSONArray jsonArray = new JSONArray();
        reviews.stream().map(JSONModel::toJSONObject).forEach(jsonArray::put);
        return jsonArray;
    }
}
