package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;

import java.util.ArrayDeque;

public class RemoveHead implements Command{
    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                    - remove first element from collection\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        collection.removeFirst();
    }
}
