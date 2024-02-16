package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.IOHandler;

import java.util.ArrayDeque;

public class Show implements Command{
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - show all elements of collection\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        if (collection.isEmpty()) {
            IOHandler.println("Collection is empty!");
        }

        for (LabWork lab: collection) {
            IOHandler.println(lab.toString());
            IOHandler.println("");
        }
    }
}
