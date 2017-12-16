package uek.ceneo.etl.utils.scrapper;

import java.util.ArrayList;

public class CeneoProduct implements Product {

    private String type;
    private String mark;
    private String model;
    private String additionalRemarks;
    private String category;

    private ArrayList<Opinion> opinions;

    CeneoProduct(String category) {
        this.category = category;
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

    public void showAllInformation() {
        System.out.println();
        System.out.println("Kategoria: " + category);
        System.out.println("Rodzaj: " + type);
        System.out.println("Marka: " + mark);
        System.out.println("Model: " + model);
        System.out.println("Dodatkowe uwagi: " + additionalRemarks);
        System.out.println();
        int i = 0;
        for (Opinion opinion : opinions) {
            System.out.println((++i) + ")\n" + opinion);
        }
    }
}
