package uek.ceneo.etl.utils.scrapper;

import java.util.ArrayList;

/**
 * Interfejs opisujacy kazdy produkt znajdujacy sie w serwisie ceneo
 */
public interface CeneoProduct {
    /**
     * Dodanie opinii do produktu
     *
     * @param ceneoOpinion Opinia produktu
     */
    void addOpinion(CeneoOpinion ceneoOpinion);

    /**
     * Ustawia atrybuty produktu podane w argumentach funkcji
     *
     * @param mark Marka
     * @param model Model
     * @param additionalRemarks Dodatkowe uwagi
     */
    void setProperties(String mark, String model, String additionalRemarks);

    /**
     * Pobiera marke produktu
     *
     * @return Marka produktu
     */
    String getMark();

    /**
     * Pobiera model produktu
     *
     * @return model produktu
     *
     */
    String getModel();

    /**
     * Pobiera dodatkowe uwagi przypisane do prouktu
     *
     * @return Dodatkowe uwagi
     */
    String getAdditionalRemarks();

    /**
     * Pobiera kategorie produktu
     *
     * @return Kategoria produktu
     */
    String getCategory();

    /**
     * Pobiera kategorie produktu
     *
     * @return Kategoria produktu
     */
    String getId();

    /**
     * Pobiera liste opinii
     *
     * @return Lista opinii
     */
    ArrayList<CeneoOpinion> getCeneoOpinions();
}
