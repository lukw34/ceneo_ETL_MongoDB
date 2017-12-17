package uek.ceneo.etl.models;

import org.json.JSONArray;

import java.util.ArrayList;

public class ReviewList implements JSONModel<JSONArray> {
    private ArrayList<JSONModel> reviews;

    public ReviewList() {
        reviews = new ArrayList<>();
    }

    public void insertReview(JSONModel review) {
        reviews.add(review);
    }

    @Override
    public String toJSONString() {
        return toJSONObject().toString();
    }

    @Override
    public JSONArray toJSONObject() {
        JSONArray jsonArray = new JSONArray();
        reviews.stream().map(JSONModel::toJSONObject).forEach(jsonArray::put);
        return jsonArray;
    }
}
