package ru.ifmo.se.handlers;

import java.io.File;

public class FileHandler {
    public static File process(String filePath){
        File file = new File(filePath);

        if (!file.exists()){
            IOHandler.println("File does not exist");
            return null;
        }

        if (!file.canRead()){
            IOHandler.println("Current permissions deny access to provided file");
            return null;
        }

        if (file.length() == 0){
            IOHandler.println("File is empty");
        }

        return file;
    }
}
