package ru.ifmo.se.handlers;

import ru.ifmo.se.exceptions.InvalidParameterException;

import java.io.*;
import java.nio.file.FileSystemException;

public class FileHandler {
    public static String read(String filepath) throws Exception {
        if (filepath == null) {
            throw new InvalidParameterException("There is no environment variable with collection file path. It must be named: <LAB5_FILEPATH>");
        }

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
    }

    public static void save(String filepath, String content) throws Exception {
        if (filepath == null) {
            throw new InvalidParameterException("There is no environment variable with collection file path. It must be named: <LAB5_FILEPATH>");
        }

        File file = new File(filepath);

        if (!file.exists()){
            throw new FileNotFoundException("File does not exist");
        }

        if (!file.canWrite()){
            throw new FileSystemException("Current permissions deny access to provided file");
        }

        PrintWriter writer = new PrintWriter(file);
        writer.write(content);
        writer.close();
    }
}
