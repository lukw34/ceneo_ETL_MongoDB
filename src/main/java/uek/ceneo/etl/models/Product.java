package uek.ceneo.etl.models;


import org.json.JSONObject;

public class Product implements JSONModel<JSONObject> {
    private String mark;
    private String model;
    private String additionalRemarks;
    private String category;
    private String id;

    public Product(String id, String mark, String model, String additionalRemarks, String category) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.additionalRemarks = additionalRemarks;
        this.category = category;
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
                .put("category", category);
    }
}
