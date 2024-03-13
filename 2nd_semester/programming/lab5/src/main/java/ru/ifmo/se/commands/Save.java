package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.IOHandler;
import ru.ifmo.se.handlers.XMLManager;

import java.util.ArrayList;

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
        String savePath = System.getenv("LAB5_FILEPATH");
        ArrayList<LabWork> labs = new ArrayList<LabWork>(collectionHandler.getCollection());

        try {
            XMLManager.XMLWriter.write(labs, savePath);
        } catch (Exception e) {
            IOHandler.println(e.getMessage());
        }
    }
}
