package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;

import java.util.ArrayDeque;

public class Add implements Command {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - add new LabWork to collection\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        LabWork lab = new LabWork();
        collection.add(lab);

        collectionHandler.updateCollection(collection);
    }
}
