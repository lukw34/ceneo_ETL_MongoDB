package uek.ceneo.etl.models;


import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Klasa reprezentujaca poszczegolne produkty. Zawiera takie dane jak:
 * <ul>
 * <li>Identyfikator [id]</li>
 * <li>Rodzaj urzadzenia [category]</li>
 * <li>Marka [mark] </li>
 * <li>Model [model]</li>
 * <li>Lista identyfikatorow opinii [reviews]</li>
 * <li>Data utworzenia [createdAt]</li>
 * </ul>
 *
 * @see uek.ceneo.etl.models.JSONModel
 */
public class Product implements JSONModel<JSONObject> {
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
     * Identyfikator produktu
     */
    private String id;
    /**
     * Lista identyfiaktorów opinii opisujacych produkt
     */
    private List<String> reviews;
    /**
     * Data wpisania produktu do bazy
     */
    private Date createdAt;

    /**
     * Konstruktor klasy
     *
     * @param id                Identyfikator produktu
     * @param mark              Marka produktu
     * @param model             Model produktu
     * @param additionalRemarks Dodatkowe uwagi
     * @param category          Rodzaj produktu
     * @param reviews           Lista opinii
     */
    public Product(String id, String mark, String model, String additionalRemarks,
                   String category, List<String> reviews) {
        Calendar calendar = Calendar.getInstance();
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.additionalRemarks = additionalRemarks;
        this.category = category;
        this.reviews = reviews;
        this.createdAt = calendar.getTime();
    }

    /**
     * Pobiera identyfikator produktu
     *
     * @return Identyfikator produktu
     */
    public String getId() {
        return id;
    }

    /**
     * Implementacja metody zwracajacej produkt w postaci ciagu znakow formatu JSON
     *
     * @return Reprezentacja produktu jako ciagu znakow
     */
    @Override
    public String toJSONString() {
        return toJSONObject().toString();
    }


    /**
     * Implementacja metody tworzacej obiekt o formacie JSON
     *
     * @return Reprezentacja produktu jako obieku JSON-owego
     */
    @Override
    public JSONObject toJSONObject() {
        return new JSONObject()
                .put("id", id)
                .put("model", model)
                .put("mark", mark)
                .put("additionalRemarks", additionalRemarks)
                .put("category", category)
                .put("reviews", reviews.toArray())
                .put("createdAt", this.createdAt);
    }
}
