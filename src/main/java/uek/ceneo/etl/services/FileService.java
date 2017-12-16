package uek.ceneo.etl.services;

import java.io.IOException;

public interface FileService {
    String read(String fileName);

    void write(String fileName, String data);

    void clear(String fileName);
}
