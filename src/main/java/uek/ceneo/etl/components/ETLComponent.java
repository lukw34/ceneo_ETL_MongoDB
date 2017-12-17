package uek.ceneo.etl.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uek.ceneo.etl.services.ETL;
import uek.ceneo.etl.services.FileService;

@ShellComponent
public class ETLComponent {
    private final ETL ETLCeneoService;

    @Autowired
    public ETLComponent(ETL ETLCeneoService) {
        this.ETLCeneoService = ETLCeneoService;
    }

    @ShellMethod(value = "Wykonuje operacje EXTRACT dla prouktu o podanym id", key = {"E", "extract", "e"})
    public void extract(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        ETLCeneoService.extract(id);
    }


    @ShellMethod(value = "Wykonuje operacje TRANSFORM dla prouktu o podanym id", key = {"T", "transform", "t"})
    public void transform(
            @ShellOption(help = "Identyfikator produktu") String id
    ) {
        ETLCeneoService.transform(id);
    }


}
