package uek.ceneo.etl.utils.scrapper;

public interface Product {
    void addOpinion(Opinion opinion);
    void setProperties(String type, String mark, String model, String additionalRemarks);
}
