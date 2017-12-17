package uek.ceneo.etl.utils.scrapper;

import java.util.ArrayList;

public interface CeneoProduct {
    void addOpinion(CeneoOpinion ceneoOpinion);

    void setProperties(String mark, String model, String additionalRemarks);

    String getMark();

    String getModel();

    String getAdditionalRemarks();

    String getCategory();

    String getId();

    ArrayList<CeneoOpinion> getCeneoOpinions();
}
