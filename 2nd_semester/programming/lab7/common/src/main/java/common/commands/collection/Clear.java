package common.commands.collection;

import common.collections.LabWork;
import common.commands.Command;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.Collections;

public class Clear extends CollectionCommand {
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                           - clear the collection\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        collectionHandler.setCollection(new ArrayDeque<LabWork>());
        return new Response(null, "Cleared");
    }
}
