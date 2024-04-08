package common.commands;

import common.network.Response;
import lombok.SneakyThrows;
import common.collections.LabWork;
import common.handlers.CollectionHandler;
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
    public Response execute(CollectionHandler collectionHandler) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        try {
            long targetId = Integer.parseInt(args[0]);

            Optional<LabWork> labWork = collection.stream()
                    .filter(l -> l.getId() == targetId)
                    .findFirst();

            if(labWork.isPresent()) {
                collection.remove(labWork.get());
                this.lab.setId(labWork.get().getId());
                collection.add(this.lab);
                return new Response("Updated");
            }

            return new Response("Element with provided id not found in collection");

        } catch (NumberFormatException e) {
            return new Response("Invalid id provided");
        }
    }

    public void executeFromFile(CollectionHandler collectionHandler, LabWork lw, String[] args) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        ArrayDeque<LabWork> newCollection = new ArrayDeque<LabWork>();

        try {
            long targetId = Integer.parseInt(args[0]);

            for (LabWork lab : collection) {
                if (lab.getId() == targetId) {
                    LabWork.removeId(targetId);
                    newCollection.add(lw);
                } else {
                    newCollection.add(lab);
                }
            }

            collectionHandler.setCollection(newCollection);
        } catch (Exception e) {
            IOHandler.println("Invalid id provided");
        }
    }
}
