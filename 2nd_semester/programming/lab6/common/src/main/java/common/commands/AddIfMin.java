package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class AddIfMin extends CommandWithElement {
    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {element}            - add if minimalPoint greater than minimum element\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        return null;
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, LabWork lab) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        boolean shouldAdd = collection.stream()
                .anyMatch(lw -> lab.getAveragePoint() > lw.getAveragePoint());

        if (shouldAdd) {
            lab.setId(LabWork.generateId());
            collectionHandler.add(lab);
            return  new Response("Added");
        } else {
            return new Response("Not added");
        }
    }
}
