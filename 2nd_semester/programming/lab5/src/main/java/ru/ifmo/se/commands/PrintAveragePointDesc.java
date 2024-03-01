package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.IOHandler;

import java.util.ArrayDeque;

public class PrintAveragePointDesc implements Command {
    @Override
    public String getName() {
        return "average_point_desc";
    }

    @Override
    public String getDescription() {
        return this.getName() + "              - print all average point desc\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        ArrayDeque<LabWork> sortedCollection = (ArrayDeque<LabWork>) collection.stream().sorted();

        for (LabWork lw: sortedCollection) {
            IOHandler.println(lw.getAveragePoint());
        }
    }
}
