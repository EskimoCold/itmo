package common.commands.collection;

import common.collections.LabWork;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.network.Response;

import java.util.ArrayDeque;

public class AddIfMin extends CommandWithElement {
    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescription() {
        return this.getName() + " {element}            - add if minimalPoint greater than minimum element\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler) {
        return null;
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, LabWork lab) {
        ArrayDeque<LabWork> collection = collectionHandler.getCollection();

        boolean shouldAdd = collection.stream()
                .anyMatch(lw -> lab.getAveragePoint() > lw.getAveragePoint());

        if (shouldAdd) {
            LabWork labWork = DBHandler.createLab(lab, this.getUser(), true);

            if (labWork != null) {
                collectionHandler.add(labWork);
            }

            return  new Response(null, "Added");
        } else {
            return new Response(null, "Not added");
        }
    }
}
