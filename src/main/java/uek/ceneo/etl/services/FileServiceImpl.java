package uek.ceneo.etl.services;

import org.springframework.stereotype.Service;

import java.io.*;

@Service("FileService")
public class FileServiceImpl implements FileService {

    @Override
    public String read(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader reader = new FileReader("resource/" + fileName);
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
    public void write(String fileName, String data) {
        try {
            FileWriter writer = new FileWriter("resource/" + fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
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
