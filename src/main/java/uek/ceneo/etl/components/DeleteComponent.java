package uek.ceneo.etl.components;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uek.ceneo.etl.services.MongoService;

import java.util.stream.Collectors;

@ShellComponent
public class DeleteComponent {
    MongoService mongoService;

    @Autowired
    public DeleteComponent(MongoService mongoService) {
        this.mongoService = mongoService;
    }

    @ShellMethod(value = "Usuwa opinie z bazy danych.", key = {"d", "delete", "del", "D"})
    public String deleteOpinionst(
            @ShellOption(
                    help = "Identyfikator produktu, którego opinie mają zostać usuniete",
                    defaultValue = ShellOption.NULL
            ) String id,
            @ShellOption(
                    help = "Usuwa wszystkie opinie",
                    defaultValue = ShellOption.NONE
            ) boolean all
    ) {
        StringBuilder log = new StringBuilder();
        if (all) {
            log.append(this.mongoService.dropCollection("reviews") ? "Usunieto kolekcje [reviews]" : "");
            return log.toString();
        }
        long counrRemovedReviews = this.mongoService
                .remove("reviews", new Document("productId", id));
        log.append("Usunieto ").append(counrRemovedReviews).append(" opinii.");
        return log.toString();
    }
}
