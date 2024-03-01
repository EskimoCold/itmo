package ru.ifmo.se.commands;

import ru.ifmo.se.collections.LabWork;
import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.IOHandler;

import java.util.ArrayDeque;

public class RemoveById implements Command{
    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return this.getName() + " id                 - remove element by id\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        ArrayDeque<LabWork> newCollection = new ArrayDeque<LabWork>();

        try {
            long targetId = Integer.parseInt(args[1]);

            for (LabWork lw : collection) {
                if (lw.getId() != targetId) {
                    newCollection.add(lw);
                } else {
                    LabWork.removeId(lw.getId());
                }
            }

            collectionHandler.setCollection(newCollection);

        } catch (Exception e) {
            IOHandler.println("Invalid id provided");
        }

    }
}
