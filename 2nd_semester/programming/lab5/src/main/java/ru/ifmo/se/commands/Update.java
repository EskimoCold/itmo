package ru.ifmo.se.commands;

import lombok.SneakyThrows;
import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.IOHandler;

import java.util.ArrayDeque;

public class Update implements Command{
    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return this.getName() + " id {element}             - update collection element id\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        ArrayDeque<LabWork> newCollection = new ArrayDeque<LabWork>();

        try {
            long targetId = Integer.parseInt(args[1]);

            for (LabWork lw : collection) {
                if (lw.getId() == targetId) {
                    LabWork.removeId(targetId);
                    LabWork newLab = new LabWork(targetId);
                    newCollection.add(newLab);
                } else {
                    newCollection.add(lw);
                }
            }

            collectionHandler.setCollection(newCollection);
        } catch (Exception e) {
            IOHandler.println("Invalid id provided");
        }
    }
}
