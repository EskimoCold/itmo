package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

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
    public Response execute(CollectionHandler collectionHandler) {
        boolean shouldAdd = collectionHandler.getCollection().stream()
                .anyMatch(lw -> this.lab.getAveragePoint() > lw.getAveragePoint());

        if (shouldAdd) {
            collectionHandler.add(this.lab);
            return new Response("Added");
        }

        return new Response("Not added");
    }

    public void executeFromFile(CollectionHandler collectionHandler, LabWork lw, String[] args) {
        for (LabWork lab: collectionHandler.getCollection()) {
            if (lw.getAveragePoint() > lab.getAveragePoint()) {
                collectionHandler.add(lw);
                break;
            }
        }
    }
}
