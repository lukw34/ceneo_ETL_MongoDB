package uek.ceneo.etl.utils.scrapper;

import java.util.ArrayList;

public class CeneoProductImpl implements CeneoProduct {

    private String mark;
    private String model;
    private String additionalRemarks;
    private String category;
    private String id;

    private ArrayList<CeneoOpinion> ceneoOpinions;

    CeneoProductImpl(String id, String category) {
        this.ceneoOpinions = new ArrayList<>();
        this.category = category;
        this.id = id;
    }

    @Override
    public void setProperties(String mark, String model, String additionalRemarks) {
        this.mark = mark;
        this.model = model;
        this.additionalRemarks = additionalRemarks;
    }

    @Override
    public void addOpinion(CeneoOpinion ceneoOpinion) {
        ceneoOpinions.add(ceneoOpinion);
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getAdditionalRemarks() {
        return additionalRemarks;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public ArrayList<CeneoOpinion> getCeneoOpinions() {
        return ceneoOpinions;
    }
}
