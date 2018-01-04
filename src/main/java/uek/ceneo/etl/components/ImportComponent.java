package uek.ceneo.etl.components;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uek.ceneo.etl.services.CSVService;
import uek.ceneo.etl.services.FileService;
import uek.ceneo.etl.services.MongoService;

import java.util.ArrayList;

/**
 * Klasa zawiera komendy zwiazane z importem danych z bazy do plikow
 *
 * @see ShellComponent
 */
@ShellComponent
public class ImportComponent {
    private final MongoService mongoService;
    private final CSVService csvService;
    private final FileService fileService;

    /**
     * Konstruktor wstrzykuj serwisy niezbedne do dzialania komponentu
     *
     * @param mongoService Serwis do obslugi bazy danych
     * @param csvService   Serwis do obslugi plikow csv
     * @param fileService  Serwis do obslugi operacji I/O dla plikow
     */
    @Autowired
    public ImportComponent(MongoService mongoService, CSVService csvService, FileService fileService) {
        this.csvService = csvService;
        this.mongoService = mongoService;
        this.fileService = fileService;
    }

    /**
     * Metoda odpowiedzialna za obsluge polecenia importujace opinie z bazy do plikow o wskazanym formacie
     *
     * @param id         Identyfikator produktu, ktorego opinie maja zostać zaimportowane
     * @param dataFormat Typ pliku do ktorego maja zostac zaimportowane pliki (csv lub txt)
     * @param all        Pobiera wszystkie opinie
     * @return komunikat wyswietlany w konsoli
     * @see ShellMethod
     */
    @ShellMethod(value = "Importuje opinie do pliku", key = {"import-review", "ir",})
    public String getReviewCSV(
            @ShellOption(
                    help = "Identyfikator produktu, ktorego opinie maja zostac zaimportowane",
                    defaultValue = ShellOption.NULL
            ) String id,
            @ShellOption(
                    help = "Typ pliku do ktorego maja zostac zaimportowane pliki (csv lub txt)",
                    defaultValue = "csv"
            ) String dataFormat,
            @ShellOption(
                    help = "Pobiera wszystkie opinie",
                    defaultValue = ShellOption.NONE
            ) boolean all
    ) {

        StringBuilder log = new StringBuilder();
        ArrayList<Document> items = new ArrayList<>();
        String data,
                fileName = "default";

        if (all) {
            items = this.mongoService.find("reviews");
            fileName = "allReview";
        } else if (id != null) {
            items = this.mongoService.find("reviews", new Document("productId", id));
            fileName = "reviews_" + id;
        }

        switch (dataFormat) {
            case "csv":
                data = this.csvService.build(items);
                break;
            case "txt":
                data = items.toString();
                break;
            default:
                return log.append("Dane nie mogą zostawic przetworzone do pliku ").append(dataFormat).append(".").toString();
        }

        long size = fileService.write(fileName + "." + dataFormat, data);
        return log.append("Stworzono plik ").append(dataFormat).append(" o rozmiarze ").append(size).append(" kb").toString();
    }
}
