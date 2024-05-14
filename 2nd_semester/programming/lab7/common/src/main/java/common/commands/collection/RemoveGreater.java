package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.Objects;
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
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        return null;
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, LabWork lab) {
        ArrayDeque<LabWork> updatedCollection = collectionHandler.getCollection().stream()
                .filter(labWork -> Objects.equals(this.getUser().getUsername(), labWork.getUsername()))
                .filter(labWork -> lab.compareTo(labWork) <= 0)
                .collect(Collectors.toCollection(ArrayDeque::new));

        collectionHandler.setCollection(updatedCollection);

        return new Response(null, "Removed");
    }
}
