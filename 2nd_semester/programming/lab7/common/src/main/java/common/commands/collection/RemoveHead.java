package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.Objects;

public class RemoveHead extends CollectionCommand {
    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                    - remove first element from collection\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        collection.stream()
                .filter(lab -> Objects.equals(this.getUser().getUsername(), lab.getUsername()))
                .findFirst()
                .ifPresent(collection::remove);

        return new Response(null, "Removed");
    }
}
