package uek.ceneo.etl.components;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import uek.ceneo.etl.services.ETL;
import uek.ceneo.etl.services.MongoService;
import uek.ceneo.etl.services.TableService;

import java.util.ArrayList;

@ShellComponent
public class ListComponent {

    private final TableService tableService;
    private final MongoService mongoService;

    @Autowired
    public ListComponent(TableService tableService, MongoService mongoService) {
        this.mongoService = mongoService;
        this.tableService = tableService;
    }

    @ShellMethod(value = "Pobiera liste produktów zapisanych do bazy.", key = {"LP", "list-products", "lp"})
    public Table listProduct() {
        ArrayList<String> properties = new ArrayList<>();
        properties.add("id");
        properties.add("mark");
        properties.add("category");
        properties.add("additionalRemarks");
        return tableService.getTable(mongoService.getUniqueList("products"), properties);
    }


    @ShellMethod(value = "Pobiera liste produktów zapisanych do bazy.", key = {"LR", "list-reviews", "lr"})
    public Table listReviews(
            @ShellOption(help = "Identyfikator produktu dla ktoreg maja zostać pobrane opinie") String id
    ) {
        mongoService.find("products", new Document("id", id));
        return null;
    }
}
