package uek.ceneo.etl.services;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Klasa odpowiedzialna za obsluge plikow umiejscowionych w folderze resources
 *
 * @see org.springframework.stereotype.Service;
 */
@Service("FileServiceImpl")
public class FileServiceImpl implements FileService {
    /**
     * Głowny folder gdzie przechowywane sa pliki
     */
    private final String  ROOT_PATH = "resource/";

    /**
     * Czyta zawartosc pliku znajdujacego sie w folderze resources
     *
     * @param fileName Nazwa pliku
     * @return Zawartosc pliku
     */
    @Override
    public String read(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String path = ROOT_PATH + fileName;
        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return stringBuilder.toString();
    }

    /**
     * Zapisuje dane do pliku znajdujacego sie w folderze resources
     *
     * @param fileName Nazwa pliku
     * @param data     Dane
     * @return Rozmiar utworzonego pliku
     */
    @Override
    public long write(String fileName, String data) {
        String path = ROOT_PATH + fileName;
        try {
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(data);
            bufferedWriter.close();
            File file = new File(ROOT_PATH + fileName);
            return file.length() / 1024;
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * Usuwa plik znajdujacy sie w folderze resources
     *
     * @param fileName Nazwa pliku, ktory ma zostac usuniety
     */
    @Override
    public void clear(String fileName) {
        try {
            File file = new File(ROOT_PATH + fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
