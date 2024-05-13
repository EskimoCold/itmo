package common.commands.collection;

import common.collections.LabWork;
import common.commands.Command;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;

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
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        ArrayDeque<LabWork> newCollection = collectionHandler.getCollection();
        newCollection.removeFirst();
        collectionHandler.setCollection(newCollection);
        return new Response(null, "Removed");
    }
}
