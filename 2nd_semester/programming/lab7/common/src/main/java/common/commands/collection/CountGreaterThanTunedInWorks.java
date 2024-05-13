package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class CountGreaterThanTunedInWorks extends CommandWithElement {
    @Override
    public String getName() {
        return "greater_tuned2work";
    }

    @Override
    public String getDescription() {
        return getName() + "              - print count of elements that have a greater tuned in works\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        return null;
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, LabWork lab) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        long count = collection.stream()
                .filter(lw -> lab.getTunedInWorks() > lw.getTunedInWorks())
                .count();

        return new Response(null, "Count: " + count);
    }
}
