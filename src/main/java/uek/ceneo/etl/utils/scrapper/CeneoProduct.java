package uek.ceneo.etl.utils.scrapper;

import java.util.ArrayList;

public class CeneoProduct implements Product {

    private String type;
    private String mark;
    private String model;
    private String additionalRemarks;
    private String category;
    private String id;

    private ArrayList<Opinion> opinions;

    CeneoProduct(String id, String category) {
        this.opinions = new ArrayList<>();
        this.category = category;
        this.id = id;
    }

    @Override
    public void setProperties(String type, String mark, String model, String additionalRemarks) {
        this.type = type;
        this.mark = mark;
        this.model = model;
        this.additionalRemarks = additionalRemarks;
    }

    @Override
    public void addOpinion(Opinion opinion) {
        opinions.add(opinion);
    }

    public void removeOpinion(int id) {
        opinions.remove(id);
    }
}
