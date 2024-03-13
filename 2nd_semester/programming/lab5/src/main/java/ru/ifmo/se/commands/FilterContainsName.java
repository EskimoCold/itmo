package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.IOHandler;

import java.util.ArrayDeque;

public class FilterContainsName implements Command {
    @Override
    public String getName() {
        return "filter_by_name";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {substring}                   - print all elements contain this substring";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        String substring = args[1];

        for (LabWork lw: collection) {
            if (lw.getName().toLowerCase().contains(substring)) {
                IOHandler.println(lw);
            }
        }
    }
}
