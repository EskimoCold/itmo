package common.commands.collection;

import common.collections.LabWork;
import common.commands.Command;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

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
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        ArrayDeque<LabWork> newCollection = collection.stream()
                .filter(lab -> this.getUser().getUsername() != lab.getUsername())
                .collect(Collectors.toCollection(ArrayDeque::new));

        collectionHandler.setCollection(newCollection);

        return new Response(null, "Cleared");
    }
}
