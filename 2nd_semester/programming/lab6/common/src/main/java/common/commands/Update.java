package common.commands;

import common.handlers.CollectionHandler;
import common.network.Response;
import common.collections.LabWork;
import common.handlers.IOHandler;

import java.util.ArrayDeque;
import java.util.Optional;

public class Update extends CommandWithElement{
    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return this.getName() + " id {element}             - update collection element id\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        return null;
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, LabWork lab) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        try {
            long targetId = Integer.parseInt(args[0]);

            Optional<LabWork> labWork = collection.stream()
                    .filter(l -> l.getId() == targetId)
                    .findFirst();

            if (labWork.isPresent()) {
                collection.remove(labWork.get());
                lab.setId(targetId);
                collection.add(lab);
                collectionHandler.setCollection(collection);
                return new Response("Updated");
            }

            return new Response("Element with provided id not found in collection");

        } catch (NumberFormatException e) {
            return new Response("Invalid id provided!");
        }
    }
}
