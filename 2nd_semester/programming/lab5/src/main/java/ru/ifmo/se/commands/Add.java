package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;

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
        LabWork lab = new LabWork(Boolean.FALSE);
        collectionHandler.add(lab);
    }

    @Override
    public void executeFromFile(CollectionHandler collectionHandler, LabWork lw, String[] args) {
        collectionHandler.add(lw);
    }
}
