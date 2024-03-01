package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;

import java.util.ArrayDeque;

public class RemoveGreater implements Command{
    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {element}        - remove all elements from collection that is greater than given\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        ArrayDeque<LabWork> newCollection = new ArrayDeque<LabWork>();

        LabWork labToCompare = new LabWork(Boolean.FALSE);

        for (LabWork lw: collection){
            if (lw.compareTo(labToCompare) > 0){
                newCollection.add(lw);
            }
            else {
                LabWork.removeId(lw.getId());
            }
        }

        collectionHandler.setCollection(newCollection);
    }
}
