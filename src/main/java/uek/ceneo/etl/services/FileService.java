package uek.ceneo.etl.services;

public interface FileService {
    String read(String fileName);

    long write(String fileName, String data);

    void clear(String fileName);
}
