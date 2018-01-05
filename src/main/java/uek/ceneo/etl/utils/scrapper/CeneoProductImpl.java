package uek.ceneo.etl.utils.scrapper;

import java.util.ArrayList;

/**
 * Klasa reprezentujaca produkt zawarty w serwisie Ceneo
 */
public class CeneoProductImpl implements CeneoProduct {

    private String mark;
    private String model;
    private String additionalRemarks;
    private String category;
    private String id;

    private ArrayList<CeneoOpinion> ceneoOpinions;

    /**
     * Konstruktor klasy
     *
     * @param id       Identyfikator produktu
     * @param category Kategoria
     */
    CeneoProductImpl(String id, String category) {
        this.ceneoOpinions = new ArrayList<>();
        this.category = category;
        this.id = id;
    }

    /**
     * Ustawia atrybuty produktu podane w argumentach funkcji
     *
     * @param mark              Marka
     * @param model             Model
     * @param additionalRemarks Dodatkowe uwagi
     */
    @Override
    public void setProperties(String mark, String model, String additionalRemarks) {
        this.mark = mark;
        this.model = model;
        this.additionalRemarks = additionalRemarks;
    }

    /**
     * Dodanie opinii do produktu
     *
     * @param ceneoOpinion Opinia produktu
     */
    @Override
    public void addOpinion(CeneoOpinion ceneoOpinion) {
        ceneoOpinions.add(ceneoOpinion);
    }

    /**
     * Pobiera marke produktu
     *
     * @return Marka produktu
     */
    @Override
    public String getMark() {
        return mark;
    }

    /**
     * Pobiera model produktu
     *
     * @return model produktu
     */
    @Override
    public String getModel() {
        return model;
    }

    /**
     * Pobiera dodatkowe uwagi przypisane do prouktu
     *
     * @return Dodatkowe uwagi
     */
    @Override
    public String getAdditionalRemarks() {
        return additionalRemarks;
    }

    /**
     * Pobiera kategorie produktu
     *
     * @return Kategoria produktu
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Pobiera kategorie produktu
     *
     * @return Kategoria produktu
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Pobiera liste opinii
     *
     * @return Lista opinii
     */
    @Override
    public ArrayList<CeneoOpinion> getCeneoOpinions() {
        return ceneoOpinions;
    }
}
