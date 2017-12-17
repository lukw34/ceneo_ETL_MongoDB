package uek.ceneo.etl.models;

public interface JSONModel<T> {
    String toJSONString();

    T toJSONObject();
}
