package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.FileHandler;
import ru.ifmo.se.handlers.IOHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class Save implements Command {
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            -- save collection to file\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        String savePath = System.getenv("LAB5_FILEPATH");

        if (savePath == null) {
            IOHandler.println("There is no environment variable with collection file path");
            return;
        }

        StringBuilder output = new StringBuilder("<labWorks>");

        for (LabWork lw : collection) {
            output.append(lw.toXml());
        }

        output.append("</labWorks>");

        File file = FileHandler.process(savePath);

        if (file == null){
            return;
        }

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(output.toString());
            writer.close();
            IOHandler.println("Collection has been successfully written to file: " + savePath);

        } catch (FileNotFoundException e) {
            IOHandler.println("Error writing to file: " + e.getMessage());
        }
    }
}
