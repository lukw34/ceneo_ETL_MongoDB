package uek.ceneo.etl.utils.scrapper;

import java.util.ArrayList;

/**
 * Klasa reprezentujaca produkt zawarty w serwisie Ceneo
 */
public class CeneoProductImpl implements CeneoProduct {
    /**
     * Marka produktu
     */
    private String mark;
    /**
     * Model produktu
     */
    private String model;
    /**
     * Dodatkowe uwagi
     */
    private String additionalRemarks;
    /**
     * Kategoria produktu
     */
    private String category;
    /**
     * Nazwa produktu
     */
    private String name;
    /**
     * Identyfikator produktu
     */
    private String id;
    /**
     * Lista opinii opisujacych produkt
     */
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
     * @param name              Nazwa prouktu
     */
    @Override
    public void setProperties(String mark, String model, String additionalRemarks, String name) {
        this.mark = mark;
        this.model = model;
        this.additionalRemarks = additionalRemarks;
        this.name = name;
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
     * Pobiera nezwe produktu
     *
     * @return Nazwa  produktu
     */
    @Override
    public String getName() {
        return this.name;
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
