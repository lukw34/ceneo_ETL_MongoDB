package uek.ceneo.etl.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uek.ceneo.etl.services.ETL;

@ShellComponent
public class ETLComponent {
    private final ETL ETLCeneoService;

    @Autowired
    public ETLComponent(ETL ETLCeneoService) {
        this.ETLCeneoService = ETLCeneoService;
    }

    @ShellMethod(value = "Wykonuje operacje EXTRACT dla prouktu o podanym id", key = {"E", "extract", "e"})
    public String  extract(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        return ETLCeneoService.extract(id);
    }


    @ShellMethod(value = "Wykonuje operacje TRANSFORM dla prouktu o podanym id", key = {"T", "transform", "t"})
    public String transform(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        return ETLCeneoService.transform(id);
    }


    @ShellMethod(value = "Wykonuje operacje LOAD dla prouktu o podanym id", key = {"L", "load", "l"})
    public String load(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        return ETLCeneoService.load(id);
    }

    @ShellMethod(value = "Wykonuje operacje ETL dla prouktu o podanym id", key = {"ETL", "etl", })
    public String ETL(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        StringBuilder log = new StringBuilder();
        log.append(ETLCeneoService.extract(id)).append(ETLCeneoService.transform(id)).append(ETLCeneoService.load(id));
        return log.toString();
    }
}
