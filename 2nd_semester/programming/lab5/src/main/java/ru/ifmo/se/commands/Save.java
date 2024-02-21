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
        return this.getName() + "                            - save collection to file\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        String savePath = System.getenv("LAB5_FILEPATH");

        StringBuilder output = new StringBuilder("<labWorks>\n");

        for (LabWork lw : collection) {
            output.append(lw.toXml());
        }

        output.append("</labWorks>");

        FileHandler.save(savePath, output.toString());
    }
}
