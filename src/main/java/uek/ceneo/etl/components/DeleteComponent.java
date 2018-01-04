package uek.ceneo.etl.components;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uek.ceneo.etl.services.MongoService;

/**
 * Klasa zawiera komendy zwiazane z czyszczeniem danych w bazie
 *
 * @see ShellComponent
 */
@ShellComponent
public class DeleteComponent {
    private final MongoService mongoService;

    /**
     * Konstruktor wstrzykuj serwisy niezbedne do dzialania komponentu
     *
     * @param mongoService Serwis do obslugi bazy danych
     */
    @Autowired
    public DeleteComponent(MongoService mongoService) {
        this.mongoService = mongoService;
    }

    /**
     * Metoda odpowiedzialna za obsluge polecenia usuwajacego opinie z bazy
     *
     * @param id  Identyfikator produktu, ktorego opinie maja zostac usuniete
     * @param all Pobiera wszystkie opinie
     * @return Komunikat wyswietlany w konsoli
     * @see ShellMethod
     */
    @ShellMethod(value = "Usuwa opinie z bazy danych.", key = {"d", "delete", "del", "D"})
    public String deleteOpinions(
            @ShellOption(
                    help = "Identyfikator produktu, ktorego opinie mają zostac usuniete",
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
        long counterRemovedReviews = this.mongoService
                .remove("reviews", new Document("productId", id));
        log.append("Usunieto ").append(counterRemovedReviews).append(" opinii.");
        return log.toString();
    }
}
