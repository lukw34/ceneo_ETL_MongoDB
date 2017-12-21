package uek.ceneo.etl.services;

import org.springframework.stereotype.Service;

import java.io.*;

@Service("FileServiceImpl")
public class FileServiceImpl implements FileService {

    @Override
    public String read(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String path = "resource/" + fileName;
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

    @Override
    public long write(String fileName, String data) {
        String path = "resource/" + fileName;
        try {
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(data);
            bufferedWriter.close();
            File file = new File("resource/" + fileName);
            return file.length() / 1024;
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public void clear(String fileName) {
        try {
            File file = new File("resource/" + fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
