package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class RemoveGreater extends CommandWithElement{
    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {element}        - remove all elements from collection that is greater than given\n";
    }

    @Override
    public Response execute(CollectionHandler collectionHandler) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        ArrayDeque<LabWork> updatedCollection = collection.stream()
                .filter(labWork -> this.lab.compareTo(labWork) <= 0)
                .collect(Collectors.toCollection(ArrayDeque::new));

        collectionHandler.setCollection(updatedCollection);

        return new Response("Removed");
    }
}
