package common.commands;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class Clear extends Command{
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                           - clear the collection\n";
    }

    @Override
    public Response execute(CollectionHandler collectionHandler) {
        collectionHandler.setCollection(new ArrayDeque<LabWork>());
        return new Response("Cleared");
    }
}
