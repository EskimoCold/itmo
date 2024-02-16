package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;

import java.util.ArrayDeque;

public class Clear implements Command{
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - clear the collection\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        collectionHandler.updateCollection(new ArrayDeque<LabWork>());
    }
}
