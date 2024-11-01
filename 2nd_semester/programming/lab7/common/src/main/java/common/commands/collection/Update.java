package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.Objects;
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
    public Response execute(String[] args) {
        return null;
    }

    @Override
    public Response execute(String[] args, LabWork lab) {
        ArrayDeque<LabWork> collection = this.getCollectionHandler().getCollection();

        try {
            long targetId = Integer.parseInt(args[0]);

            Optional<LabWork> labWork = collection.stream()
                    .filter(labb -> Objects.equals(this.getUser().getUsername(), labb.getUsername()))
                    .filter(l -> l.getId() == targetId)
                    .findFirst();

            if (labWork.isPresent()) {
                collection.remove(labWork.get());
                lab.setId(targetId);
                collection.add(lab);
                this.getCollectionHandler().setCollection(collection);
                return new Response(null, "Updated");
            }

            return new Response(null, "Element with provided id not found in collection");

        } catch (NumberFormatException e) {
            return new Response(null, "Invalid id provided!");
        }
    }
}
