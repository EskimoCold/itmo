package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class Show extends CollectionCommand {
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - show all elements of collection\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();
        StringBuilder output = new StringBuilder();

        if (collection.isEmpty()) {
            output.append("Collection is empty!");
        }

        for (LabWork lab: collection) {
            output.append(lab.toString()).append("\n----------------------\n");
        }

        return new Response(null, output.toString());
    }
}
