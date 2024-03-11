package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.IOHandler;

public class CountGreaterThanTunedInWorks implements CommandWithElement {

    @Override
    public String getName() {
        return "greater_tuned2work";
    }

    @Override
    public String getDescription() {
        return getName() + "                 - print count of elements that have a greater tuned in works";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        LabWork lw = new LabWork(Boolean.FALSE);

        int count = 0;
        for (LabWork lab: collectionHandler.getCollection()) {
            if (lw.getTunedInWorks() > lab.getTunedInWorks()) {
                count += 1;
            }
        }

        IOHandler.println(count);
    }

    @Override
    public void executeFromFile(CollectionHandler collectionHandler, LabWork lw, String[] args) {
        int count = 0;
        for (LabWork lab: collectionHandler.getCollection()) {
            if (lw.getTunedInWorks() > lab.getTunedInWorks()) {
                count += 1;
            }
        }

        IOHandler.println(count);
    }
}
