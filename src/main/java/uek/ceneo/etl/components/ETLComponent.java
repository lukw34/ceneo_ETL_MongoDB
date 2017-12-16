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

    @ShellMethod("Wykonuje operacje EXTRACT dla prouktu o podanym id")
    public String extract(
            @ShellOption String id
    ) {

        ETLCeneoService.extract(id);
        return id;
    }
}
