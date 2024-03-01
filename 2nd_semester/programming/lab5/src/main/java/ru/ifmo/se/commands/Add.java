package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;

import java.util.ArrayDeque;

public class Add implements CommandWithElement {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                             - add new LabWork to collection\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        LabWork lab = new LabWork(Boolean.FALSE);
        collection.add(lab);

        collectionHandler.setCollection(collection);
    }

    @Override
    public void executeFromFile(CollectionHandler collectionHandler, LabWork lw) {
        System.out.println(lw);
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        collection.add(lw);
        collectionHandler.setCollection(collection);
    }
}
