package uek.ceneo.etl.services;

public interface ETL {
    public void extract(String id);
    public void transform(String id);
    public void load(String id);
}
