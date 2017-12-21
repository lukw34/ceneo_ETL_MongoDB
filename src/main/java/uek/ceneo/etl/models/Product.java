package uek.ceneo.etl.models;


import org.json.JSONObject;

import java.util.List;

public class Product implements JSONModel<JSONObject> {
    private String mark;
    private String model;
    private String additionalRemarks;
    private String category;
    private String id;
    private List<String> reviews;

    public Product(String id, String mark, String model, String additionalRemarks, String category, List<String> reviews) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.additionalRemarks = additionalRemarks;
        this.category = category;
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toJSONString() {
       return toJSONObject().toString();
    }

    @Override
    public JSONObject toJSONObject() {
        return new JSONObject()
                .put("id", id)
                .put("model", model)
                .put("mark", mark)
                .put("additionalRemarks", additionalRemarks)
                .put("category", category)
                .put("reviews", reviews.toArray());
    }
}
