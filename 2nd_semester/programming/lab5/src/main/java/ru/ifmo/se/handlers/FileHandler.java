package ru.ifmo.se.handlers;

import java.io.*;

public class FileHandler {
    public static String read(String filepath) {
        if (filepath == null) {
            IOHandler.println("There is no environment variable with collection file path. It must be named: <LAB5_FILEPATH>");
            return null;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(filepath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            StringBuilder content = new StringBuilder();

            int byteRead;
            while ((byteRead = bufferedInputStream.read()) != -1) {
                content.append((char) byteRead);
            }

            bufferedInputStream.close();
            fileInputStream.close();

            return content.toString();

        } catch (IOException e) {
            IOHandler.println(e.getMessage());
            return null;
        }
    }

    public static void save(String filepath, String content) {
        if (filepath == null) {
            IOHandler.println("There is no environment variable with collection file path. It must be named: <LAB5_FILEPATH>");
            return;
        }

        File file = new File(filepath);

        if (!file.exists()){
            IOHandler.println("File does not exist");
            return;
        }

        if (!file.canWrite()){
            IOHandler.println("Current permissions deny access to provided file");
            return;
        }

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(content);
            writer.close();

        } catch (Exception e) {
            IOHandler.println(e.getMessage());

        }

    }
}
