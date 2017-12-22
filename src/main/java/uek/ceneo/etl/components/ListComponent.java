package uek.ceneo.etl.components;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.ArrayTableModel;


@ShellComponent
public class ListComponent {

    @ShellMethod(value = "Pobiera liste id produktów zapisanych do bazy.", key = {"LP", "list-products", "lp"})
    public String  extract() {
        String[][] data = new String[][]{{"asd", "ASd", "AS"}};

        ArrayTableModel tableModel = new ArrayTableModel(data)
        return tableModel.toString();
    }

}
