package uek.ceneo.etl.components;


import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
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
    public String listProduct() {
        ArrayList<String> properties = new ArrayList<>();
        properties.add("id");
        properties.add("mark");
        properties.add("category");
        properties.add("additionalRemarks");
        return this.tableService.getTable(this.mongoService.getUniqueList("products"), properties);
    }


    @ShellMethod(value = "Pobiera liste opinii zapisanych do bazy dla produktu o podanym identyfikatorze.", key = {"LR", "list-reviews", "lr"})
    public String listReviews(
            @ShellOption(help = "Identyfikator produktu dla ktoreg maja zostać pobrane opinie") String id
    ) {
        StringBuilder log = new StringBuilder();
        ArrayList<Document> products = this.mongoService.find("products", new Document("id", id), Sorts.ascending("createdAt"));
        if (products.size() > 0) {
            ArrayList<Document> documents = new ArrayList<>();
            ArrayList productReviews = products.get(0).get("reviews", ArrayList.class);

            for (Object productReview : productReviews) {
                String reviewId = productReview.toString();
                ArrayList<Document> reviewsList = this.mongoService.find("reviews", new Document().append("_id", reviewId));
                if (reviewsList.size() > 0) {
                    documents.add(reviewsList.get(0));
                }
            }

            ArrayList<String> properties = new ArrayList<>();
            properties.add("disadvantages");
            properties.add("advantages");
            properties.add("productReview");
            properties.add("reviewScoreCount");
            properties.add("reviewerName");
            properties.add("reviewTime");
            properties.add("reviewSummary");
            properties.add("voteYes");
            properties.add("voteNo");
            properties.add("productId");

            return this.tableService.getTable(documents, properties);
        }
        log.append("Produkt o identyfikatorze ").append(id).append(" nie istnieje.");
        return log.toString();
    }
}
