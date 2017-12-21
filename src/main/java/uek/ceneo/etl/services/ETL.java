package uek.ceneo.etl.services;

public interface ETL {
    public String extract(String id);
    public String transform(String id);
    public String load(String id);
}
