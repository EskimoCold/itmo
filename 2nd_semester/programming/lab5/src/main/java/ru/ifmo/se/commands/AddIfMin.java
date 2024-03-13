package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;

public class AddIfMin implements CommandWithElement {
    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {element}            - add if minimalPoint greater than minimum element";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        LabWork lab = new LabWork(Boolean.FALSE);

        for (LabWork lw: collectionHandler.getCollection()) {
            if (lab.getAveragePoint() > lw.getAveragePoint()) {
                collectionHandler.add(lab);
                break;
            }
        }
    }

    @Override
    public void executeFromFile(CollectionHandler collectionHandler, LabWork lw, String[] args) {
        for (LabWork lab: collectionHandler.getCollection()) {
            if (lw.getAveragePoint() > lab.getAveragePoint()) {
                collectionHandler.add(lw);
                break;
            }
        }
    }
}
