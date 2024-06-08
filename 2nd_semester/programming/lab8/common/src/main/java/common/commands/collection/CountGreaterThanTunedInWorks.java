package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class CountGreaterThanTunedInWorks extends CollectionCommand {
    @Override
    public String getName() {
        return "greater_tuned2work";
    }

    @Override
    public String getDescription() {
        return getName() + "              - print count of elements that have a greater tuned in works\n";
    }

    @Override
    public Response execute(String[] args) {
        ArrayDeque<LabWork> collection = this.getCollectionHandler().getCollection();
        long count = collection.stream()
                .filter(lw -> lw.getTunedInWorks() > Integer.parseInt(args[0]))
                .count();

        return new Response(null, count);
    }
}
