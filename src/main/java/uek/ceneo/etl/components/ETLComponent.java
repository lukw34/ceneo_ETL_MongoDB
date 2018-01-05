package uek.ceneo.etl.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uek.ceneo.etl.services.ETL;

/**
 * Klasa zawiera komendy zwiazane z obsluga procesu ETL
 *
 * @see org.springframework.shell.standard.ShellComponent
 */
@ShellComponent
public class ETLComponent {
    /**
     * Serwis do obslugi procesow ETL
     */
    private final ETL ETLCeneoService;

    /**
     * Konstruktor wstrzykuj serwisy niezbedne do dzialania komponentu
     *
     * @param ETLCeneoService Serwis obslugujacy poszczegolne etapy procesu ETL
     */
    @Autowired
    public ETLComponent(ETL ETLCeneoService) {
        this.ETLCeneoService = ETLCeneoService;
    }

    /**
     * Metoda wykonujaca operacje extract.
     * Polega ona na pobraniu pliku html.
     *
     * @param id Identyfikator produktu
     * @return Komunikat wyswietlany w konsoli
     * @see org.springframework.shell.standard.ShellMethod
     */
    @ShellMethod(value = "Wykonuje operacje EXTRACT dla prouktu o podanym id", key = {"E", "extract", "e"})
    public String  extract(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        return ETLCeneoService.extract(id);
    }


    /**
     * Metoda wykonujaca operacje transform.
     * Polega ona na przeanalizowaniu dokumentu html w kontekscie pobrania interesujacych informacji, a nastepnie dokonanie transformacji do modeli danych.
     *
     * @param id Identyfikator produktu
     * @return Komunikat wyswietlany w konsoli
     * @see org.springframework.shell.standard.ShellMethod
     */
    @ShellMethod(value = "Wykonuje operacje TRANSFORM dla prouktu o podanym id", key = {"T", "transform", "t"})
    public String transform(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        return ETLCeneoService.transform(id);
    }


    /**
     * Metoda wykonujaca operacje load.
     * Polega ona na załaowaniu wczesniej przygotowanych danych do bazy.
     *
     * @param id Identyfikator produktu
     * @return Komunikat wyswietlany w konsoli
     * @see org.springframework.shell.standard.ShellMethod
     */
    @ShellMethod(value = "Wykonuje operacje LOAD dla prouktu o podanym id", key = {"L", "load", "l"})
    public String load(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        return ETLCeneoService.load(id);
    }

    /**
     * Metoda wykonujaca caly proces ETL.
     *
     * @param id Identyfikator produktu
     * @return Komunikat wyswietlany w konsoli
     * @see org.springframework.shell.standard.ShellMethod
     */
    @ShellMethod(value = "Wykonuje operacje ETL dla prouktu o podanym id", key = {"ETL", "etl", })
    public String ETL(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        StringBuilder log = new StringBuilder();
        log.append(ETLCeneoService.extract(id)).append(ETLCeneoService.transform(id)).append(ETLCeneoService.load(id));
        return log.toString();
    }
}
